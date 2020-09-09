package demo.zooktest.lock;

/**
 * @Author: Posiedon.wh
 * @Date: 2020/9/9 21:54
 */
public class OrderTask implements Runnable {
    private GeneratorNum generatorNum=new GeneratorNum();
    private Lock lock=new ZKPefectLock();
    @Override
    public void run() {
        getOrderNum();
    }

    private void getOrderNum() {
        try {
            lock.getLock();
            String num = generatorNum.getNum();
            System.out.println(Thread.currentThread().getName()+" ,订单号="+num);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unLock();
        }
    }

}
