package demo.think.currenttest;

/**
 * @Author: Posiedon.wh
 * @Date: 2020/8/31 15:23
 */
public class InnerThread2 {
    private int countDown=5;
    private Thread thread;

    public InnerThread2(String name) {
        thread=new Thread(name){
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
                return getName()+": "+countDown;
            }
        };
        thread.start();
    }
}
