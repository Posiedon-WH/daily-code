package posiedon.scope;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

//@Component
public class RefreshConfig implements ApplicationContextAware {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    Environment environment;

    private ConcurrentHashMap map = new ConcurrentHashMap();

    private static String redisSourceName = "redisSource";

    private static String scopeName = "RefreshConfig";

    private static String redisConfigKey = "";

    @Value("${redis.config.enable:false}")
    private boolean enable;

    private static ConfigurableApplicationContext applicationContext;

    private BeanDefinitionRegistry beanDefinitionRegistry;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        RefreshConfig.applicationContext = (ConfigurableApplicationContext) applicationContext;
    }


    @PostConstruct
    public void init() {
        if (!enable) return;

        RefreshConfigScopeRegistry refreshConfigScopeRegister = (RefreshConfigScopeRegistry) applicationContext.getBean("refreshConfigScopeRegistry");
        beanDefinitionRegistry = refreshConfigScopeRegister.getBeanDefinitionRegistry();

        RefreshConfig.redisConfigKey = getRedisConfigKey();

        refreshEnv();
    }

    //开始更新配置指令
    public void refresh() {
        this.refreshEnv();
    }

    //更新environment 属性值
    private void refreshEnv() {
        if (!enable) return;

        //创建sourceProperty
        if (!checkExistRedisSpringProperty()) {
            createRedisSpringProperty();
        }

//        Map properties = redisTemplate.opsForHash().entries(redisConfigKey);
        Map properties = redisTemplate.opsForHash().entries("helloclient");

        if (properties.isEmpty()) return;

        MutablePropertySources propertySources = applicationContext.getEnvironment().getPropertySources();
        PropertySource<?> source = propertySources.get(redisSourceName);
        ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap) source.getSource();
        concurrentHashMap.clear();
        Set<Object> keys = properties.keySet();
        for (Object key : keys) {
            concurrentHashMap.put(key, properties.get(key));
        }

        refreshBean();

    }

    //创建redis-propertySources 环境资源
    private void createRedisSpringProperty() {
        MutablePropertySources propertySources = applicationContext.getEnvironment().getPropertySources();
        OriginTrackedMapPropertySource redisSource = new OriginTrackedMapPropertySource(redisSourceName, map);
        propertySources.addFirst(redisSource);
    }

    //判断redis-propertySources是否已创建
    private boolean checkExistRedisSpringProperty() {
        MutablePropertySources propertySources = applicationContext.getEnvironment().getPropertySources();
        if (propertySources.contains(redisSourceName)) {
            return true;
        } else {
            return false;
        }
    }

    //更新@Value 相关属性值
    private void refreshBean() {
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            BeanDefinition beanDefinition = beanDefinitionRegistry.getBeanDefinition(name);
            if (scopeName.equals(beanDefinition.getScope())) {
                //销毁bean 思考，如果这时候删除了bean，有没有问题？
                applicationContext.getBeanFactory().destroyScopedBean(name);
                //重新实例化
                applicationContext.getBean(name);
            }
        }

    }

    //获得redis 中配置key
    private String getRedisConfigKey() {
        String appName = environment.getProperty("spring.application.name");
        String profile = environment.getProperty("spring.profiles.active");
        String key = null;
        if (appName == null || "".equals(appName)) {
            key = "application";
            if (profile != null && !"".equals(profile)) {
                key += profile;
            }
        } else {
            key = appName.toLowerCase();
            if (profile != null && !"".equals(profile)) {
                key += profile.toLowerCase();
            }
        }

        return key;
    }
}
