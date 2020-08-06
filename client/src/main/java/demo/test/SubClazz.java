package demo.test;

public class SubClazz extends SuperClazz{

    public float a=3.0f;
    public String d="Java";

    public SubClazz(String one,String two){
        super(one,two);
    }
    public SubClazz() {
        System.out.println("sub ctor");
    }

//    public int f1(){
//        return 2;
//    }

    public void f2(){

    }

    public String f3(int s,int sd){
        return "sss";
    }
    public void show(){
        System.out.println("subclass：a"+a+"d="+d);
    }

    public String getName(){
        //name 为私有 报错
//        return name;
        return "dd";
    }

    public void print(){
        System.out.println("child:"+one+" to "+two);
    }
}
