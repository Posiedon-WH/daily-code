package posiedon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Scope("RefreshConfig")
@RestController
public class ConfigController {

    @Value("${xx.name:wh}")
    private String name;

    @Autowired
    Environment environment;

    @RequestMapping("config")
    public String config(){
        String re="====redis.name.@Value=="+name+" @environment==="+environment.getProperty("xx.name");
        return re;
    }
}
