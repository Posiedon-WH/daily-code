package demo.think.currenttest.sych;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: Posiedon.wh
 * @Date: 2020/9/2 17:19
 */
public class SychTest {

    @Test
    public void test1() throws InterruptedException {
        //synchronized 修饰方法
        SychMethod sychMethod = new SychMethod();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Runnable() {
            @Override
            public void run() {
                sychMethod.method1();
            }
        });
        exec.execute(new Runnable() {
            @Override
            public void run() {
                sychMethod.method2();
            }
        });

        Thread.sleep(5000);

        //synchronized 修饰对象实例
        SychObject sychObject1 = new SychObject();

        exec.execute(new Runnable() {
            @Override
            public void run() {
                sychObject1.method1();
            }
        });
        exec.execute(new Runnable() {
            @Override
            public void run() {
                sychObject1.method2();
            }
        });
        Thread.sleep(5000);

        //synchronized 修饰静态方法

        exec.execute(new Runnable() {
            @Override
            public void run() {
                SychStatic.method1();
            }
        });
        exec.execute(new Runnable() {
            @Override
            public void run() {
                SychStatic.method2();
            }
        });
        Thread.sleep(5000);
    }
}
