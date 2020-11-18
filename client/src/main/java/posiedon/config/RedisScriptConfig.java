package posiedon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scripting.ScriptSource;
import org.springframework.scripting.support.ResourceScriptSource;

import java.io.IOException;

@Configuration
public class RedisScriptConfig {

    @Bean
    public RedisScript<Boolean> lockScript(){
        RedisScript<Boolean> redisScript=null;
        try {
            ScriptSource scriptSource=new ResourceScriptSource(new ClassPathResource("demo\\script\\lock.lua"));
            redisScript = RedisScript.of(scriptSource.getScriptAsString(), Boolean.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return redisScript;
    }

    @Bean
    public RedisScript<Long> unlockScript(){
        RedisScript<Long> redisScript=null;
        try {
            ScriptSource scriptSource = new ResourceScriptSource(new ClassPathResource("demo\\script\\unlock.lua"));
            redisScript=RedisScript.of(scriptSource.getScriptAsString(),Long.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return redisScript;
    }
}
