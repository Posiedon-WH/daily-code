package demo.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class Config {

    @Bean(initMethod = "init1")
    public A getA(){
        return new A();
    }
}
