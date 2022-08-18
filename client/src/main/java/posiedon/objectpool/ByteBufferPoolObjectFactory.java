package posiedon.objectpool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import java.nio.ByteBuffer;

/**
 * @author weihai
 * @desc description
 * @date 2021/5/12
 */
public class ByteBufferPoolObjectFactory extends BasePooledObjectFactory<ByteBuffer> {

    private final Integer bufferSize;

    public ByteBufferPoolObjectFactory(Integer bufferSize) {
        this.bufferSize = bufferSize;
    }

    @Override
    public ByteBuffer create() throws Exception {
        return ByteBuffer.allocate(bufferSize);
    }

    @Override
    public PooledObject<ByteBuffer> wrap(ByteBuffer byteBuffer) {
        return new DefaultPooledObject<>(byteBuffer);
    }

}
