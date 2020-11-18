package posiedon.think.currenttest.sych;

/**
 * @Author: Posiedon.wh
 * @Date: 2020/9/2 17:08
 */
public class SychStatic {
    public static synchronized void method1(){
        System.out.println("sych static method1 start");
        try {
            System.out.println("sych static method1 running");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sych static method1 end");
    }

    public static synchronized void method2(){
        System.out.println("sych static method2 start");
        try {
            System.out.println("sych static method2 running");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sych static method2 end");
    }
}
