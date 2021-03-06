package posiedon.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.concurrent.ConcurrentHashMap;


public class RefreshConfigScope implements Scope {

    //缓存实例
    private final ConcurrentHashMap<String,Object> caches=new ConcurrentHashMap<>();

    @Override
    public Object get(String s, ObjectFactory<?> objectFactory) {
        if(caches.containsKey(s)){
            return caches.get(s);
        }
        //getObject(),钩子方法创建实例
        Object object = objectFactory.getObject();
        caches.put(s,object);
        return object;
    }

    @Override
    public Object remove(String s) {
        return caches.remove(s);
    }

    @Override
    public void registerDestructionCallback(String s, Runnable runnable) {

    }

    @Override
    public Object resolveContextualObject(String s) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}
