package demo.think.currenttest.sych;

/**
 * @Author: Posiedon.wh
 * @Date: 2020/9/2 17:07
 */
public class SychMethod {
    public synchronized void method1(){
        System.out.println("sych method1 start");
        try {
            System.out.println("sych method1 running");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sych method1 end");
    }

    public synchronized void method2(){
        System.out.println("sych method2 start");
        try {
            System.out.println("sych method2 running");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sych method2 end");
    }
}
