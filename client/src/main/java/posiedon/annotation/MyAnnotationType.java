package posiedon.annotation;

/**
 * @author weihai
 * @desc description
 * @date 2020/11/12
 */
@MyType(value = "hello",required = false)
public class MyAnnotationType {
    private String name;

    @MyType(value = "md1")
    private void testMethod(){
        this.name="posiedon";
    }

    @MyType(value = "md2")
    private void test2(){

    }

    public static class MyAnnotationTypeChild extends MyAnnotationType{

    }
}
