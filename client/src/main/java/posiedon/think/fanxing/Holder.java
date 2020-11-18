package posiedon.think.fanxing;

/**
 * @author: Posiedon.wh
 * @date: 2020/10/25 16:56
 */
public class Holder<T> {
    private T a;
    private T b;
    private T c;

    public Holder() {
    }

    public Holder(T a, T b, T c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public T getA() {
        return a;
    }

    public void setA(T a) {
        this.a = a;
    }

    public T getB() {
        return b;
    }

    public void setB(T b) {
        this.b = b;
    }

    public T getC() {
        return c;
    }

    public void setC(T c) {
        this.c = c;
    }
}
