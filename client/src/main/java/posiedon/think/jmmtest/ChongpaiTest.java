package posiedon.think.jmmtest;

/**
 * @Author: Posiedon.wh
 * @Date: 2020/10/11 21:06
 *
 * 单例模式双重检验
 */
public class ChongpaiTest {
    private static volatile ChongpaiTest INSTANCE;

    private ChongpaiTest(){}

    private static ChongpaiTest getInstance(){
        if(INSTANCE==null){
            synchronized (ChongpaiTest.class){
                //双重检验提升了性能，增加了并发量
                if(INSTANCE==null){
                    // 发生指令重排,正常顺序是1-2-3，但如果发生指令重排1-3-2；
                    // 所以需要加入volatile关键字
                    /**
                     * 1.申请内存
                     *
                     * 2.初始化
                     *
                     * 3.将内存地址赋值给引用INSTANCE
                     */
                    INSTANCE=new ChongpaiTest();
                }
            }
        }
        return INSTANCE;
    }
}
