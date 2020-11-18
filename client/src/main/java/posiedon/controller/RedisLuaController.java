package posiedon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RedisLuaController {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    RedisScript<Boolean> lockScript;

    @Autowired
    RedisScript<Long> unlockScript;

    @RequestMapping("distrLock/{key}/{uuid}")
    public void Lock(@PathVariable String key,@PathVariable String uuid){
        try {
            Boolean flag = redisTemplate.execute(lockScript, Collections.singletonList(key), uuid, "50000");
            log.info("locked:{}",flag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("distrUnlock/{key}/{uuid}")
    public void unlock(@PathVariable String key,@PathVariable String uuid){
        try {
            Long unlocked = redisTemplate.execute(unlockScript, Collections.singletonList(key), uuid);
            log.info("unlock status {}",unlocked.toString());
        } catch (Exception e) {
            log.error("解锁失败 {}",e.getMessage());
        }
    }
}
