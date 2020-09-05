package demo.think.currenttest.sych;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Posiedon.wh
 * @Date: 2020/9/2 17:08
 */
public class SychObject {
    public void method1(){
        try {
            System.out.println("sych object method1 start");
            synchronized (this){
                System.out.println("sych object method1 running");
                Thread.sleep(3000);
            }
            System.out.println("sych object method1 end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void method2(){
        try {
            System.out.println("sych object method2 start");
            synchronized (this){
                System.out.println("sych object method2 running");
                Thread.sleep(1000);
            }
            System.out.println("sych object method2 end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
