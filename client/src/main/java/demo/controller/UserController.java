package demo.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class UserController {

    private final Log logger= LogFactory.getLog(UserController.class);

    public UserController() {
        System.out.println("init-userctl");
    }

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @RequestMapping("f1")
    public void f1(){
//        Long add = redisTemplate.opsForSet().add("hello", "world");
        redisTemplate.opsForValue().increment("str1",1);
        redisTemplate.opsForValue().setIfPresent("str2","1",30,TimeUnit.DAYS);
        redisTemplate.opsForValue().setIfAbsent("str2","1",30,TimeUnit.DAYS);
//        return add.toString();
    }

    @RequestMapping("lock")
    public String lock(){
        String re="lock";
        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", "1",5, TimeUnit.SECONDS);

        if(!lock){
            re="lock fail";
            logger.info("lock fail");
        }else {
            logger.info("===do business===");
        }
        return re;
    }

    @RequestMapping("hash")
    public void testHash(){
        Map<Object, Object> map = redisTemplate.opsForHash().entries("helloclient");
        logger.info(map.get("xx.name"));
    }

    //限流
    @RequestMapping("limit")
    public boolean limit(){

        if(redisTemplate.hasKey("rate:1")){
            Long increment = redisTemplate.opsForValue().increment("rate:1");
            logger.info("访问次数"+increment);
            if(increment>10){
                return false;
            }
            logger.info("===do business===");
        }else {
            Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent("rate:1", "1", 20, TimeUnit.SECONDS);
            logger.info("====设置过期=="+aBoolean);
            logger.info("===do business===");
        }
        return true;
    }
}
