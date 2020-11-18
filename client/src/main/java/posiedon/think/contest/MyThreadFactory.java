package posiedon.think.contest;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Posiedon.wh
 * @Date: 2020/10/4 11:12
 */
public class MyThreadFactory implements ThreadFactory {

    private final String namePrefix;
    private final AtomicInteger nextId=new AtomicInteger(1);

    public MyThreadFactory(String threadName) {
        this.namePrefix = "from myThreadFactory's "+threadName+" -worker-";
    }

    @Override
    public Thread newThread(Runnable r) {
        String name=this.namePrefix+nextId.getAndIncrement();
        Thread thread = new Thread( r, name);
        return thread;
    }
}
