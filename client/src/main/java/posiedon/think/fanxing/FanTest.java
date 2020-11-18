package posiedon.think.fanxing;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @Author: Posiedon.wh
 * @Date: 2020/10/25 16:59
 */

public class FanTest {
    @Test
    public void test1(){
        Holder<Integer> holder = new Holder<Integer>();
        holder.setA(1);
        holder.setB(2);
        holder.setC(3);
        System.out.println(holder.getA()+holder.getB().toString()+holder.getC().toString());
    }

    @Test
    public void test2(){
        TwoTuple<String, Integer> tuple = new TwoTuple<String, Integer>("wh",18);
        // final 保证了安全性
//        tuple.first="333";
        System.out.println(tuple.toString());
    }

    @Test
    public void test3(){
        LinkedStack<String> linkedStack = new LinkedStack<>();
        for (String s:"hello world !!".split(" ")){
            linkedStack.push(s);
        }
        String s;
        while ((s=linkedStack.pop())!=null){
            System.out.println(s);
        }
    }

    @Test
    public void test4(){
        Method[] methods = Object.class.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
        Class<?>[] interfaces = Object.class.getInterfaces();
        System.out.println("==================");
        for (Class<?> anInterface : interfaces) {
            System.out.println(anInterface);
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(Customer.generator.next());
        }
    }
}

