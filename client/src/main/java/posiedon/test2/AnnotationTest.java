package posiedon.test2;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

import org.junit.Test;


import posiedon.annotation.MyAnnotationType;
import posiedon.annotation.MyAnnotationType.MyAnnotationTypeChild;
import posiedon.annotation.MyType;
import lombok.extern.slf4j.Slf4j;

/**
 * @author weihai
 * @desc description
 * @date 2020/11/12
 */
@Slf4j
public class AnnotationTest {

    @Test
    public void test1() {
        AnnotatedType[] annotatedInterfaces = MyAnnotationType.class.getAnnotatedInterfaces();
        Annotation[] annotations = MyAnnotationType.class.getAnnotations();
        log.info("annotatedType:");
        Arrays.stream(annotatedInterfaces).forEach(System.out::println);
        log.info("annotations:");
        for (Annotation annotation : annotations) {
            log.info(annotation.toString());
            log.info(annotation.annotationType().getName());
            log.info(annotation.annotationType().getMethods()[0].getName());
            log.info(annotation.annotationType().getMethods()[1].getName());
        }
    }

    @Test
    public void test2() {
        MyType annotation = MyAnnotationType.class.getAnnotation(MyType.class);
        log.info("annotation value: {}", annotation.value());
        log.info("annotation required: {}", annotation.required());

        //
        MyType inheritedAnnotation = MyAnnotationTypeChild.class.getAnnotation(MyType.class);
        log.info("annotation child value: {}", inheritedAnnotation.value());
        log.info("annotation child required: {}", inheritedAnnotation.required());

        //只获得所有继承的公共方法
        Method[] methods1 = MyAnnotationType.class.getMethods();
        //只获得声明的方法
        Method[] methods = MyAnnotationType.class.getDeclaredMethods();
        for (Method method : methods) {
            MyType methodAnnotation = method.getAnnotation(MyType.class);
            if (Objects.nonNull(methodAnnotation)) {
                log.info("method {} ,value is {}, required is {}", method.getName(), methodAnnotation.value(),
                        methodAnnotation.required());
            }
        }

    }

}
