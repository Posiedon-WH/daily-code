package posiedon.think.currenttest;

/**
 * @Author: Posiedon.wh
 * @Date: 2020/8/31 15:29
 */
public class InnerRunnable1 {
    private int countDown=5;
    private Inner inner;

    public InnerRunnable1(String name) {
        inner=new Inner(name);
    }

    private class Inner implements Runnable{

        private Thread thread;

        Inner(String name){
            thread=new Thread(this,name);
            thread.start();
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
        @Override
        public String toString() {
            return thread.getName()+": "+countDown;
        }
    }
}
