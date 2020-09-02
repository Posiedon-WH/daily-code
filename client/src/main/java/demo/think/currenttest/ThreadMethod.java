package demo.think.currenttest;

/**
 * @Author: Posiedon.wh
 * @Date: 2020/8/31 15:42
 */
public class ThreadMethod {
    private int countDown=5;
    private Thread thread;
    private String name;

    public ThreadMethod(String name) {
        this.name = name;
    }

    public void runTask(){
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
                return thread.getName()+": "+countDown;
            }
        };
        thread.start();
    }
}
