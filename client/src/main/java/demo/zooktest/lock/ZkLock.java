package demo.zooktest.lock;

import org.I0Itec.zkclient.IZkDataListener;

import java.util.concurrent.CountDownLatch;

/**
 *
 * 会产生羊群效应，唤醒时把所有等待的任务都唤
 *
 * @Author: Posiedon.wh
 * @Date: 2020/9/9 20:18
 *
 */
public class ZkLock extends ZkAbstrackLock{
    private CountDownLatch countDownLatch=null;
    private static final String path="/zkLock1";
    @Override
    public boolean tryLock() {
        try {
            zkClient.createEphemeral(path);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    @Override
    public void waitLock() {
        IZkDataListener dataListener = new IZkDataListener() {

            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                //唤醒等待线程
                if(countDownLatch!=null){
                    countDownLatch.countDown();
                }
            }
        };

        //注册监听事件
        zkClient.subscribeDataChanges(path,dataListener);

        //如果节点存在
        if(zkClient.exists(path)){
            countDownLatch=new CountDownLatch(1);
            try {
                //等待，直到收到事件通知
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //删除监听事件
        zkClient.unsubscribeDataChanges(path,dataListener);

    }

    @Override
    public void unLock() {
        if(zkClient!=null){
            zkClient.delete(path);
            zkClient.close();
            System.out.println("释放锁资源");
        }

    }
}
