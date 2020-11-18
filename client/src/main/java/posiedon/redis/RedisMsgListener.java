package posiedon.redis;

import posiedon.scope.RefreshConfig;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;


public class RedisMsgListener implements MessageListener {
    private final RefreshConfig refreshConfig;

    public RedisMsgListener(RefreshConfig refreshConfig) {
        this.refreshConfig = refreshConfig;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println("===refresh config from redis===");
        refreshConfig.refresh();
    }
}
