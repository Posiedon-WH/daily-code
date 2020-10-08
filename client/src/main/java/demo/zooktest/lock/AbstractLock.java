package demo.zooktest.lock;

/**
 * @Author: Posiedon.wh
 * @Date: 2020/9/9 19:27
 */
public abstract class AbstractLock implements Lock{

    @Override
    public void getLock() {

        //竞争锁
        if(tryLock()){
            System.out.println("获得资源-锁");
        }else {
            //阻塞锁
            waitLock();

            //重新获取资源锁
            getLock();
        }
    }

    public abstract boolean tryLock();

    public abstract void waitLock();

}
