package posiedon.think.currenttest;

/**
 * @Author: Posiedon.wh
 * @Date: 2020/8/31 15:39
 */
public class InnerRunnable2 {
    private int countDown=5;

    private Thread thread;

    public InnerRunnable2(String name) {
        thread=new Thread(new Runnable() {
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
        },name);
        thread.start();
    }
}
