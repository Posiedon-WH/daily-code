package posiedon.think.currenttest.sych;

/**
 *
 *
 * @Author: Posiedon.wh
 * @Date: 2020/9/10 20:39
 */
public class GuarantedTimeOutObject {
    private Object response;

    private Object lock=new Object();

    public Object getResponse(){
       return getResponse(0);
    }

    public Object getResponse(long millis){
        synchronized (lock){
            long begin = System.currentTimeMillis();
            long passed=0;
            if(millis<0){
                throw new IllegalArgumentException();
            }
            while (response==null){
                long waitTime=millis-passed;
                try {
                    if(waitTime<0){
                        break;
                    }
                    lock.wait(waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                passed=System.currentTimeMillis()-begin;
            }
        }
        return response;
    }

    public void setResponse(Object result){
        synchronized (lock){
            response=result;
            lock.notifyAll();
        }
    }
}
