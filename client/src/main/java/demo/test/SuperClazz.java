package demo.test;

public class SuperClazz {
    public static final String value="常量";
    private String name;
    public static int val=121;
    public static final int valref=val;
    String one,two;
    public int a=11;
    public double d=2.0;
    public SuperClazz(String one1,String two1){
        one=one1;
        two=two1;
    }
    public SuperClazz() {
        System.out.println("Sup clazz ctor");
    }

    public void f1(){
        System.out.println("ddd");
    }
    public void f1(int d){
        System.out.println("dd");
    }
    public void f2(int s){
        System.out.println("sss");
    }

    public int f3(int s){
        return s;
    }
    public void show(){
        System.out.println("superclass：a"+a+"d="+d);
    }

    public void print(){
        System.out.println("parant:"+one);
    }
}
