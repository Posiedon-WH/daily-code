package posiedon.test;

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
        this.SFun();
    }

    public void CFun(){
        System.out.println("普通方法");
    }

    public static void SFun(){
        System.out.println("类方法");
    }
    public static void SFun2(){
//        this.SFun();  //不能在类方法中调用this来调用类方法

        SuperClazz.SFun();//调用本类类方法可直接调用
        new SuperClazz().CFun();//调用实例方法
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
