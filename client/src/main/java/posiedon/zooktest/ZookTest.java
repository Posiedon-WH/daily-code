package posiedon.zooktest;

import org.apache.zookeeper.*;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: Posiedon.wh
 * @Date: 2020/8/2 10:00
 */
public class ZookTest {


    private static final String SERVER="192.168.1.106:2181,192.168.1.107:2181";
    private final int SESSION_TIMEOUT=10000;

    @Test
    public void testSession() throws IOException, KeeperException, InterruptedException {
        ZooKeeper zk = new ZooKeeper(SERVER,SESSION_TIMEOUT,null);
        System.out.println(zk);
        System.out.println(zk.getState());

        zk.create("/test/client3","client3".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    @Test
    public void session2Test() throws InterruptedException, IOException {
        CountDownLatch countDown = new CountDownLatch(1);
        ZooKeeper zk = new ZooKeeper(SERVER, SESSION_TIMEOUT, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println(watchedEvent.getState());
                if(watchedEvent.getState()==Event.KeeperState.SyncConnected){
                    System.out.println("连接成功");
                    countDown.countDown();
                }
            }
        });
        countDown.await();
        System.out.println(zk.getState());
    }



}
