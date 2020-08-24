package demo.test;

public class MyStatic {

    private static int a;
    private int b=10;

    private static void hanlder(){
        a=23;
        //非静态方法或变量不能再静态方法中调用
//        b=3;

    }
    public void setA(){
        a=23;
    }
}
