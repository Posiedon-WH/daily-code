package demo.test;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.Stack;


public class ConcurrentTest {
    private static final Log logger = LogFactory.getLog(ConcurrentTest.class);

    @Test
    public void test1() {
        MyAbstract myAbstract = new MyAbstract() {
            @Override
            public void findClass() {

            }
        };
        myAbstract.getName();

        MyAbstractSub wh = new MyAbstractSub("wh");
        wh.getName();
    }

    @Test
    public void createClassTest() throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        MyBean myBean = new MyBean("posiedon", 20);
        logger.info(myBean.toString());

        Class<?> aClass = Class.forName("demo.test.MyBean");

        Constructor<?>[] constructors = aClass.getConstructors();

        Object o = constructors[0].newInstance();
        MyBean myBean1 = (MyBean) o;
        logger.info(myBean1.toString());


        Class<?>[] parameterTypes = constructors[1].getParameterTypes();
        Object o1 = constructors[1].newInstance(new Object[]{"haha", 11});
        MyBean myBean2 = (MyBean) o1;
        logger.info(myBean2.toString());

        MyBean myBean3 = BeanUtils.instantiateClass(MyBean.class);
        logger.info(myBean3);
    }

    @Test
    public void ConstantTest() {
        String a = "posiedon";
        String b = new String("posiedon");

        logger.info(b == a);
        logger.info(b.equals(a));
        logger.info(b.intern() == a);
    }

    @Test
    public void StackOFTest() {

        //递归死循环
       /* class A {

            public void fun() {
                fun();
            }
        }

        A a = new A();
        a.fun();*/

        //持续建立线程
        while (true){
            Thread thread = new Thread();
            thread.start();
        }

    }

    //-Xms20M -Xmx20M -Xmn10M -XX:PrintGC -XX:+PrintGCDetails
    @Test
    public void OOMTest() {
        //堆溢出
        /*ArrayList<Object> list = new ArrayList<>();
        while (true){
            list.add(new MyBean());
        }*/

        MyBean ss = new MyBean("ss", 11);
//        SoftReference<MyBean> softReference = new SoftReference<>(ss);
        WeakReference<MyBean> softReference = new WeakReference<>(ss);
        ss = null;//取消强引用，确保只有软引用持有对象
        logger.info("====" + softReference.get());
        System.gc();
        logger.info("====" + softReference.get());
        LinkedList<byte[]> list = new LinkedList<>();
        try {
            for (int i = 0; i < 100; i++) {
                logger.info("====" + softReference.get());
                list.add(new byte[1024 * 1024]);
            }
        } catch (Exception e) {
            logger.info("===exception===" + softReference.get());
        }
    }

    @Test
    public void initTest() {
        //只会触发父类的初始化
        logger.info(SubClazz.value);
        logger.error("=====");
        //不会触发类的初始化
        SuperClazz[] superClazzes = new SuperClazz[10];
        logger.error("=====");
        //先初始化父类，在初始化子类
        SubClazz subClazz = new SubClazz();

        logger.error("=====");
        logger.info(SuperClazz.value);

        logger.error("=====");
        System.out.println(SuperClazz.valref);
    }

    @Test
    public void encodeTest(){
        String s = "30[a]2[bc]";
        String s1 = decodeString(s);
        System.out.println(s1);

    }


    public String decodeString(String s) {
        Stack<Character> stack=new Stack<>();

        int len=s.length();
        for (int i = 0; i < len; i++) {
            char cur=s.charAt(i);
            if(cur==']'){
                //向前遍历寻找【 和数字
                String temp=new String();
                int num=1;
                while (true){
                    char pop = stack.pop();
                    if(pop=='['){
                        StringBuffer nums=new StringBuffer();
                        while (true){
                            if(stack.isEmpty()){
                                break;
                            }
                            Character peek = stack.peek();
                            if(!Character.isDigit(peek)){
                                break;
                            }else {
                                Character pop1 = stack.pop();
                                nums.append(pop1);
                            }

                        }

                        num=Integer.parseInt(nums.reverse().toString());
                        break;
                    }
                    temp+=pop;

                }

                for (int i1 = 0; i1 < num; i1++) {
                    char[] chars1 = temp.toCharArray();
                    int length = chars1.length;
                    for (int length1 = length-1; length1 >= 0; length1--) {
                        stack.push(chars1[length1]);
                    }
                }
            }else{
                stack.push(cur);
            }

        }
        String re=new String();
        while (!stack.isEmpty()){
            char pop = stack.pop();
            re=re+pop;
        }

        char[] charArray = re.toCharArray();
        String result = new String();
        for (int i = charArray.length-1; i >=0; i--) {
            result=result+charArray[i];
        }
        return result;
    }

    @Test
    public void climbTest(){
//        logger.info(climbStairs(4));

    }

    public int climbStairs(int n) {

        int[] fn=new int[n+1];
        fn[0]=1;
        fn[1]=1;
        for (int i = 2; i <= n; i++) {
            fn[i]=fn[i-1]+fn[i-2];
        }

        return fn[n];
    }

    @Test
    public void equalTest(){
        String a = new String("ab"); // a 为一个引用
        String b = new String("ab"); // b 为另一个引用,对象的内容一样
        String aa = "ab"; // 放在常量池中
        String bb = "ab"; // 从常量池中查找
        if (aa == bb) // true
            System.out.println("aa==bb");
        if (a == b) // false，非同一对象
            System.out.println("a==b");
        if (a.equals(b)) // true
            System.out.println("aEQb");
        if (42 == 42.0) { // true
            System.out.println("true");
        }
        try {
            System.out.println("1");
            throw new Exception();
        } catch (Exception e) {
            System.out.println("2");
        }finally {
            System.out.println("3");
        }
    }

    @Test
    public void myTest(){
        int a=0;int c=0;
        do{
            c--;
            a=a-1;
        }while(a>0);
        System.out.println("a="+a+"c="+c);

        int n=1;
        System.out.println(n++);
        System.out.println(++n);
        System.out.println(Character.MAX_VALUE);//2^16 2个字节
        System.out.println(Byte.MIN_VALUE);//2^8 8位 1个字节
        System.out.println(Short.MAX_VALUE);//2^16 2个字节
        System.out.println(Integer.MAX_VALUE);//2^32 4个字节
        System.out.println(Long.MAX_VALUE);//2^64 8个字节
        System.out.println(Float.MAX_VALUE);//2^32 4个字节
        System.out.println(Double.MAX_VALUE);//2^64 8个字节
        System.out.println(Boolean.TRUE);//1 一位
    }

    @Test
    public void testRunable(){
        class Foo implements Runnable{
            @Override
            public void run() {
                System.out.println("running");
            }
        }

        new Thread(new Foo()).start();

        StringBuffer a=new StringBuffer("A");
        StringBuffer b=new StringBuffer("B");
        op(a,b);
        System.out.println(a+","+b);

        SuperClazz superClazz = new SuperClazz("p1", "p2");
        superClazz.print();

        SuperClazz subClazz = new SubClazz("c1", "c2");
        subClazz.print();
    }

    static void op (StringBuffer x,StringBuffer y){
        x.append(y);
        y=x;
    }

}
