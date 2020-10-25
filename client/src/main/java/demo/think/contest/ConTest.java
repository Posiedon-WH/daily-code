package demo.think.contest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;

/**
 *
 * 关于并发的一些测试
 * @Author: Posiedon.wh
 * @Date: 2020/10/4 10:53
 *
 */
@Slf4j(topic = "contest")
public class ConTest {
    private final static Logger logger=LoggerFactory.getLogger(ConTest.class);

    @Test
    public void test1() throws InterruptedException {
        MyThreadFactory threadFactory = new MyThreadFactory("wh");
        threadFactory.newThread(() -> {
            System.out.println(Thread.currentThread().getName());
            while (true) {

            }
        }).start();

        threadFactory.newThread(() -> {
            System.out.println(Thread.currentThread().getName());
            while (true) {

            }
        }).start();
        threadFactory.newThread(() -> {
            System.out.println(Thread.currentThread().getName());
            while (true) {

            }
        }).start();
        TimeUnit.SECONDS.sleep(3);
    }

    @Test
    public void test2(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1,1,60,TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>());
        ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(10);
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();

        int nextInt = ThreadLocalRandom.current().nextInt();

        LongAdder longAdder = new LongAdder();
        longAdder.increment();
        int i = longAdder.intValue();
    }

    @Test
    public void test3(){
        log.info("switch Methods");
        SwitchMethod(null);//空指针异常

    }

    public void SwitchMethod(String param){
        logger.info(param);
        logger.debug("test {} class param is {}",this.getClass().getName(),param);

        System.out.println(logger.isDebugEnabled());
        System.out.println(logger.isInfoEnabled());
        System.out.println(logger.isErrorEnabled());
        System.out.println(logger.isTraceEnabled());
        if(param==null)return;
        switch (param){
            case "sth":
                System.out.println("it is sth");
//                return;
                break;
            case "null":
                System.out.println("it is null");
                break;
//                return;
            default:
                System.out.println("default ");
        }
    }
}
