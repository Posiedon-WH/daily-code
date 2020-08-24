package demo.test;

public class SuperClazz {
    public static final String value="常量";

    public static int val=121;
    public static final int valref=val;

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
}
