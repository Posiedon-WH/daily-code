package posiedon.test2;

import java.nio.ByteBuffer;

import org.junit.Before;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author weihai
 * @desc description
 * @date 2020/11/12
 */
@Slf4j
public class ByteBufferTest {

    private ByteBuffer buffer;
    private byte[] result;

    @Before
    public void before(){
        buffer = ByteBuffer.allocate(36);
    }


    @Test
    public void writeTest(){

//        log.info("before==========");
//        logInfo();
//
//        log.info("write begin=====");
//        buffer.put((byte) 1);
//        logInfo();
//        log.info("put int======");
//        buffer.putInt(23);
//        logInfo();
//        log.info("======flip====");
//        buffer.flip();
//        logInfo();
//        result=new byte[2];
//        int rlen=2;
        String s="hello world";
        String s2="java new";

        buffer.put(s.getBytes());
        logInfo();
        buffer.put(s2.getBytes());
        logInfo();
        buffer.flip();
        byte[] bytes = new byte[buffer.limit()];
        logInfo();
        buffer.get(bytes);
        log.info("读取数据：【{}】",new String(bytes));
        logInfo();

        if(buffer.hasRemaining()){
            buffer.compact();
        }else {
            buffer.clear();
        }
        logInfo();

        buffer.put("世界真美好".getBytes());

        logInfo();

//        byte b = buffer.get();
//        while (buffer.hasRemaining()){
//            if(buffer.remaining()>rlen){
//
//            }
//        }
//        buffer.get(result,0,2);
//        logInfo();

       /* log.info("read=====");
        int len=buffer.limit();
        result=new byte[len];

        buffer.putInt(11);
        logInfo();
        buffer.flip();
        logInfo();
        log.info("len is {}",len);
        buffer.get(result,0,9);
        log.info("result is {}",result);
        logInfo();*/
    }

    public void logInfo(){
        log.info(" position is {}",buffer.position());
        log.info(" limit is {}",buffer.limit());
        log.info(" capacity is {}",buffer.capacity());
    }
}
