package posiedon.zooktest.lock;

import org.I0Itec.zkclient.IZkDataListener;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: Posiedon.wh
 * @Date: 2020/9/9 20:39
 */
public class ZKPefectLock extends ZkAbstrackLock{
    private CountDownLatch countDownLatch=null;

    private static final String path="/zkLock2";

    private String beforePath;//前一个
    private String currentPath;//当前

    public ZKPefectLock() {
        //创建永久节点-/path,在/path/下创建临时有序节点
        if(!zkClient.exists(path)){
            zkClient.createPersistent(path);
        }
    }

    @Override
    public boolean tryLock() {
        if(currentPath==null||currentPath.length()<=0){
           currentPath = zkClient.createEphemeralSequential(path+"/","lock");
        }

        List<String> children = zkClient.getChildren(path);
        Collections.sort(children);
        System.out.println("排队个数:"+children.size());
        children.forEach(System.out::println);
        if(currentPath.equals(path+"/"+children.get(0))){
            return true;
        }else {
            int idx = Collections.binarySearch(children, currentPath.substring(9));
            beforePath=path+"/"+children.get(idx-1);
        }
        return false;
    }

    @Override
    public void waitLock() {
        IZkDataListener dataListener = new IZkDataListener() {

            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                if(countDownLatch!=null){
                    countDownLatch.countDown();
                }
            }
        };
        //注册监听事件
        zkClient.subscribeDataChanges(beforePath,dataListener);

        //等待阻塞
        if(zkClient.exists(beforePath)){
            countDownLatch=new CountDownLatch(1);
            try{
                countDownLatch.await();
                System.out.println("唤醒等待任务");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        zkClient.unsubscribeDataChanges(beforePath,dataListener);
    }

    @Override
    public void unLock() {
        if(zkClient!=null){
            zkClient.delete(currentPath);
            zkClient.close();
        }
    }
}
