package posiedon.think.enumtest;

/**
 * @Author: Posiedon.wh
 * @Date: 2020/9/2 14:04
 */
public interface Food {
    enum Meal implements Food{
        PIG,CHICK,BEEF
    }

    enum Water implements Food{
        FRESH,COCO,MOMO
    }
}
