package posiedon.think.currenttest;

import org.junit.Test;

/**
 * @Author: Posiedon.wh
 * @Date: 2020/8/31 14:46
 *  创建线程的几种变种方式
 */
public class ThreadTest {
    public static class SimpleThread extends Thread{
        private static  int count=0;

        public SimpleThread(){
            count++;
            System.out.println(count);
            start();
        }

        @Override
        public String toString() {
            return "SimpleThread count "+count+" "+Thread.currentThread().getName();
        }

        @Override
        public void run() {
            while (true){
                System.out.println(this);
                if(count>=5)
                    return;
            }
        }
    }

    @Test
    public void testRun() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
//            1.继承Thread
//            new SimpleThread();
            //2.具名内部类
//            new InnerThread1("inner1-"+i);

            //3.匿名内部类
//            new InnerThread2("inner2-"+i);

            //4.具名内部类Runnable
//            new InnerRunnable1("runnable2-"+i);

            //5.匿名内部类Runnable
//            new InnerRunnable2("runnable2-"+i);

            //6.独立方法去运行task
            new ThreadMethod("task"+i).runTask();
        }
        Thread.sleep(1000);
    }
}
