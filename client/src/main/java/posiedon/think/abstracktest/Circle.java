package posiedon.think.abstracktest;

/**
 * @Author: Posiedon.wh
 * @Date: 2020/8/24 9:33
 */
public class Circle extends Shape{

    public Circle(String name) {
        this.name=name;
    }

    @Override
    void area() {
        System.out.println("circle area PI*R^2");
    }
}
