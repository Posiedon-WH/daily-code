package posiedon.objectpool;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * @author weihai
 * @desc description
 * @date 2021/5/11
 */
public abstract class AbstractObjectPool<T> {

    private long expirationTime;

    private Integer maxSize;

    private Hashtable<T, Long> locked, unLocked;

    public AbstractObjectPool() {
        expirationTime = 90000;
        maxSize = 100;
        locked = new Hashtable<T, Long>();
        unLocked = new Hashtable<T, Long>();
    }

    /**
     * 创建Object
     *
     * @return
     */
    protected abstract T create();

    /**
     * 重置
     *
     * @param o
     */
    protected abstract void reset(T o);

    /**
     * 获取对象
     *
     * @return
     */
    public synchronized T required() {
        long now = System.currentTimeMillis();
        T t;
        if (unLocked.size() > 0) {
            Enumeration<T> enumeration = unLocked.keys();
            if (enumeration.hasMoreElements()) {
                t = enumeration.nextElement();
                unLocked.remove(t);
                locked.put(t, now);
                return t;
            }
        }

        t = create();
        locked.put(t, now);
        return t;
    }

    /**
     * 释放对象
     *
     * @param o
     */
    public synchronized void release(T o) {
        locked.remove(o);
        reset(o);
        //限制池子大小
        if(unLocked.size()<=maxSize){
            unLocked.put(o, System.currentTimeMillis());
        }else {
            //释放内存
            o=null;
        }

        //删除过期的未释放的对象
        if (locked.size() > 0) {
            long now = System.currentTimeMillis();
            Enumeration<T> enumeration = locked.keys();
            while (enumeration.hasMoreElements()) {
                T t = enumeration.nextElement();
                if ((now - locked.get(t)) > expirationTime) {
                    locked.remove(t);
                    //释放内存
                    t = null;
                }
            }
        }
    }

}
