package demo.think.abstracktest;

/**
 * @Author: Posiedon.wh
 * @Date: 2020/8/24 9:37
 */
public class RedCircle extends Circle{
    public RedCircle(String name) {
        super(name);
    }

    @Override
    void area() {
        System.out.println(this.name+" area = PI*R^2");
    }
}
