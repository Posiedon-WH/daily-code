package demo.zooktest;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;
import org.junit.Test;

import java.util.List;

/**
 * @Author: Posiedon.wh
 * @Date: 2020/8/2 11:48
 */
public class ZkclientTest {

    private static final String SERVER="192.168.1.106:2181,192.168.1.107:2181";
    private final int SESSION_TIMEOUT=50000;

    @Test
    public void test1() throws InterruptedException {
        ZkClient zkClient = new ZkClient(SERVER, SESSION_TIMEOUT);
//        zkClient.createPersistent("/zkclient/test",true);
//        zkClient.createPersistent("/zkclient1","sss");
        zkClient.subscribeDataChanges("/zkclient1", new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("改变"+s+","+o);
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("删除"+s);
            }
        });


//        zkClient.writeData("/zkclient1","data");
//        zkClient.delete("/zkclient1");
        Thread.sleep(6000);
    }

    @Test
    public void childListenTest() throws InterruptedException {
        ZkClient zkClient = new ZkClient(SERVER, SESSION_TIMEOUT);
        //监听子节点变化
        zkClient.subscribeChildChanges("/parent", new IZkChildListener() {
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {
                System.out.println("父节点path:"+s);
                System.out.println("子节点:"+list);
            }
        });
        boolean exists = zkClient.exists("/parent");
        System.out.println("节点是否存在"+exists);

        Thread.sleep(3000);
        //创建父节点
        zkClient.createPersistent("/parent");

        Thread.sleep(1000);
        zkClient.createPersistent("/parent/ch1","c1 data");

        Thread.sleep(1000);
        zkClient.createPersistent("/parent/ch2","c2 data");

        Thread.sleep(1000);
        zkClient.delete("/parent/ch1");

        Thread.sleep(1000);
        zkClient.deleteRecursive("/parent");

        Thread.sleep(Integer.MAX_VALUE);

    }

    @Test
    public void test2(){
        ZkClient zkClient = new ZkClient(SERVER, SESSION_TIMEOUT);
        List<String> children = zkClient.getChildren("/n3");
        children.forEach(System.out::println);
        String path = zkClient.createEphemeralSequential("/n3/", 1);
        System.out.println("path===  "+path);
    }
}
