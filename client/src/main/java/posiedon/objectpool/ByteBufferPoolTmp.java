package posiedon.objectpool;

import java.nio.ByteBuffer;

/**
 * @author weihai
 * @desc description
 * @date 2021/5/11
 */
public class ByteBufferPoolTmp extends AbstractObjectPool<ByteBuffer> {

    private volatile static ByteBufferPoolTmp byteBufferPool;

    public static ByteBufferPoolTmp getInstance(){
        if(byteBufferPool==null){
            synchronized (ByteBufferPoolTmp.class){
                if(byteBufferPool==null){
                    byteBufferPool=new ByteBufferPoolTmp();
                }
            }
        }
        return byteBufferPool;
    }

    public ByteBufferPoolTmp() {
        super();
    }

    @Override
    protected ByteBuffer create() {
        return ByteBuffer.allocate(102400);
    }

    @Override
    protected void reset(ByteBuffer o) {
        o.clear();
    }
}
