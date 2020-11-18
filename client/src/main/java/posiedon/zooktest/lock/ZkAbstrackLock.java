package posiedon.zooktest.lock;

import org.I0Itec.zkclient.ZkClient;

/**
 * @Author: Posiedon.wh
 * @Date: 2020/9/9 20:12
 */
public abstract class ZkAbstrackLock extends AbstractLock{
    private static final String CONNECT_URL="centos106:2181,centos107:2181,centos108:2181";

    protected ZkClient zkClient=new ZkClient(CONNECT_URL);

}
