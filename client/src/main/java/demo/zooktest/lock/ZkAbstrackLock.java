package demo.zooktest.lock;

import org.I0Itec.zkclient.ZkClient;

/**
 * @Author: Posiedon.wh
 * @Date: 2020/9/9 20:12
 */
public abstract class ZkAbstrackLock extends AbstractLock{
    private static final String CONNECT_URL="192.168.1.106:2181,192.168.1.107:2181";

    protected ZkClient zkClient=new ZkClient(CONNECT_URL);

}
