package demo.think.abstracktest;

/**
 * @Author: Posiedon.wh
 * @Date: 2020/8/24 9:31
 */
public abstract class Shape {
    protected String name;

    void draw() {
        System.out.println("my name is "+name);
    }

    abstract void area();
}
