package demo.bean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class A implements InitializingBean {

    @PostConstruct
    public void post(){
        System.out.println("===A.post====");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("===A.afterPropertySet===");
    }


    public void init1(){
        System.out.println("===A.initSelf====");
    }
}
