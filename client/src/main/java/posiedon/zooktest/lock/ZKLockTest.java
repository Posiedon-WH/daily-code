package posiedon.zooktest.lock;

import org.junit.Test;

/**
 * @Author: Posiedon.wh
 * @Date: 2020/9/9 21:40
 */
public class ZKLockTest {

    @Test
    public void testDisLock()  {
        for (int i = 0; i < 50; i++) {
            new Thread(new OrderTask()).start();
        }
    }
}
