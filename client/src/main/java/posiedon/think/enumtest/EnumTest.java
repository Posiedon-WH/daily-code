package posiedon.think.enumtest;

import org.junit.Test;

import java.util.EnumSet;
import static posiedon.think.enumtest.OzWitch.*;

import lombok.extern.slf4j.Slf4j;
import posiedon.enums.MyTypeEnum;

/**
 * @Author: Posiedon.wh
 * @Date: 2020/9/2 14:10
 */
@Slf4j
public class EnumTest {

    @Test
    public void test1(){
        Food food= Food.Meal.BEEF;
        System.out.println(food);

        log.info("enum code is {}",MyTypeEnum.TYPE_ONE.getCode());
        log.info("enum code is {}",MyTypeEnum.TYPE_TWO.getCode());

    }

    @Test
    public void test2(){
        //测试enumSet
        EnumSet<OzWitch> sets=EnumSet.noneOf(OzWitch.class);
        sets.add(EAST);
        System.out.println(sets);
        sets.addAll(EnumSet.of(WEST,SOUTH));
        System.out.println(sets);
        sets.removeAll(EnumSet.allOf(OzWitch.class));
        System.out.println(sets);
        sets.addAll(EnumSet.allOf(OzWitch.class));
        System.out.println(sets);

    }
}
