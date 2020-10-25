package demo.think.fanxing;

/**
 * @Author: Posiedon.wh
 * @Date: 2020/10/25 23:49
 */
public class Customer {
    private static long counter = 1;
    private final long id = counter++;

    private Customer() {
    }

    public String toString() {
        return "customer " + id;
    }

    public static Generator<Customer> generator = new Generator<Customer>() {
        public Customer next() {
            return new Customer();
        }
    };

}
