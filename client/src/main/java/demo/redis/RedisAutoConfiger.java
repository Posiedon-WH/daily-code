package demo.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

//@Configuration
public class RedisAutoConfiger {

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Autowired
    RedisMsgListener redisMsgListener;

    @Autowired
    Environment environment;

    @Bean
    RedisMessageListenerContainer redisMessageListenerContainer() {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        container.addMessageListener(redisMsgListener, topicList());
        return container;
    }

    List<PatternTopic> topicList() {
        List<PatternTopic> topics = new ArrayList<>();
        String appName = environment.getProperty("spring.application.name");
        String profile = environment.getProperty("spring.profiles.active");
        if (appName == null || "".equals(appName)) {
            topics.add(new PatternTopic("application*"));
            topics.add(new PatternTopic("bootstrap*"));
        } else {
            if (profile == null || "".equals(profile)) {
                topics.add(new PatternTopic(appName + "*"));
                topics.add(new PatternTopic(appName.toLowerCase() + "*"));
            } else {
                topics.add(new PatternTopic(appName + "-" + profile + "*"));
                topics.add(new PatternTopic(appName.toLowerCase() + "-" + profile + "*"));
            }
        }
        return topics;
    }
}
