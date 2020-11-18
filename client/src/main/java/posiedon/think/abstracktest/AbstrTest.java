package posiedon.think.abstracktest;

import org.junit.Test;

/**
 * @Author: Posiedon.wh
 * @Date: 2020/8/24 9:35
 */
public class AbstrTest {


    @Test
    public void test(){
        Shape oldCircle = new Circle("old circle");
        Shape redCircle = new RedCircle("red circle");

        oldCircle.draw();
        redCircle.draw();
        oldCircle.area();
        redCircle.area();
    }
}
