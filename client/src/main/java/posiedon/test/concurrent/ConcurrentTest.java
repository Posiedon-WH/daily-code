package posiedon.test.concurrent;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.Stack;

import lombok.extern.slf4j.Slf4j;
import posiedon.test.MyAbstract;
import posiedon.test.MyAbstractSub;
import posiedon.test.MyBean;
import posiedon.test.SubClazz;
import posiedon.test.SuperClazz;

@Slf4j
public class ConcurrentTest {

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
        log.info(myBean.toString());

        Class<?> aClass = Class.forName("posiedon.test.MyBean");

        Constructor<?>[] constructors = aClass.getConstructors();

        Object o = constructors[0].newInstance();
        MyBean myBean1 = (MyBean) o;
        log.info(myBean1.toString());


        Class<?>[] parameterTypes = constructors[1].getParameterTypes();
        Object o1 = constructors[1].newInstance(new Object[]{"haha", 11});
        MyBean myBean2 = (MyBean) o1;
        log.info(myBean2.toString());

        MyBean myBean3 = BeanUtils.instantiateClass(MyBean.class);
        log.info(myBean3.toString());
    }

    @Test
    public void ConstantTest() {
        String a = "posiedon";
        String a1 = "posiedon";
        String b = new String("posiedon");

        log.info(String.valueOf(a==a1));
        log.info(String.valueOf(b == a));
        log.info(String.valueOf(b.equals(a)));
        log.info(String.valueOf(b.intern() == a));
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
        log.info("====" + softReference.get());
        System.gc();
        log.info("====" + softReference.get());
        LinkedList<byte[]> list = new LinkedList<>();
        try {
            for (int i = 0; i < 100; i++) {
                log.info("====" + softReference.get());
                list.add(new byte[1024 * 1024]);
            }
        } catch (Exception e) {
            log.info("===exception===" + softReference.get());
        }
    }

    @Test
    public void initTest() {
        //只会触发父类的初始化
        log.info(SubClazz.value);
        log.error("=====");
        //不会触发类的初始化
        SuperClazz[] superClazzes = new SuperClazz[10];
        log.error("=====");
        //先初始化父类，在初始化子类
        SubClazz subClazz = new SubClazz();

        log.error("=====");
        log.info(SuperClazz.value);

        log.error("=====");
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
    public void myReflect(){
        MyBean myBean = new MyBean();
        Class aClass = myBean.getClass();
        System.out.println(aClass.getName());
    }
}
