package posiedon.objectpool;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.nio.ByteBuffer;

/**
 * @author weihai
 * @desc description
 * @date 2021/5/12
 */
@Slf4j
public class ByteBufferPool {

    private volatile static ByteBufferPool byteBufferPool;

    private static GenericObjectPool<ByteBuffer> genericObjectPool;

    public static ByteBufferPool getInstance() {
        if (byteBufferPool == null) {
            synchronized (ByteBufferPool.class) {
                if (byteBufferPool == null) {
                    byteBufferPool = new ByteBufferPool();
                }
            }
        }
        return byteBufferPool;
    }

    public ByteBufferPool() {
        if (genericObjectPool == null) {
            GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
            poolConfig.setMaxIdle(10);
            poolConfig.setMaxTotal(100);
            poolConfig.setMinIdle(10);
            poolConfig.setMaxWaitMillis(3000);
            genericObjectPool = new GenericObjectPool<ByteBuffer>(new ByteBufferPoolObjectFactory(102400), poolConfig);
        }
    }

    public ByteBuffer borrow() throws Exception {
        ByteBuffer buffer = null;
        try {
            buffer = genericObjectPool.borrowObject();
        } catch (Exception e) {
            log.error("byteBufferPool borrow object fail:", e);
            throw new Exception();
        }
        return buffer;
    }

    public void returnByteBuffer(ByteBuffer byteBuffer) {
        byteBuffer.clear();
        genericObjectPool.returnObject(byteBuffer);
    }

}
