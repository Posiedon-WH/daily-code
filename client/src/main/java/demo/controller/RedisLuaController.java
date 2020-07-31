package demo.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class RedisLuaController {
    private final Log logger= LogFactory.getLog(RedisLuaController.class);

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
            logger.info("locked:{}"+flag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("distrUnlock/{key}/{uuid}")
    public void unlock(@PathVariable String key,@PathVariable String uuid){
        try {
            Long unlocked = redisTemplate.execute(unlockScript, Collections.singletonList(key), uuid);
            logger.info("unlock status "+unlocked.toString());
        } catch (Exception e) {
            logger.error("解锁失败"+e.getMessage());
        }
    }
}
