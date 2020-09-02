package demo.think.currenttest;

/**
 * @Author: Posiedon.wh
 * @Date: 2020/8/31 15:10
 */
public class InnerThread1 {

    private int countDown=5;
    private Inner inner;

    //具名内部类线程
    private class Inner extends Thread{
        Inner(String name){
            super(name);
            start();
        }

        @Override
        public void run() {
            try {
                while (true){
                    System.out.println(this);
                    if(--countDown==0){
                        return;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public InnerThread1(String name){
        inner = new Inner(name);
    }
}
