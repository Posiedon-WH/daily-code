package posiedon.bean;

import org.springframework.context.annotation.Bean;

//@Configuration
public class Config {

    @Bean(initMethod = "init1")
    public A getA(){
        return new A();
    }
}
