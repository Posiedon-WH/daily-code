package posiedon.test2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Before;
import org.junit.Test;


import com.alibaba.fastjson.JSON;

import posiedon.annotation.NALogItem;
import lombok.extern.slf4j.Slf4j;


/**
 * @author weihai
 *
 * @desc description
 * @date 2020/11/17
 */
@Slf4j
public class LogTest {
    private String rightStr="{\"c\":\"GTLog:======================== com.gaotu100.superclass: 4.8.1 "
            + "========================> ro.bootloader                : unknown\\r\\n> ro.build.id                  :"
            + " QKQ1.191014.012\\r\\n> ro.build.display.id          : ONEPLUS A5010_53_200908\\r\\n> ro.build.version"
            + ".incremental : 2009080001\\r\\n> ro.build.version.release     : 10\\r\\n> ro.build.version.sdk        "
            + " : 29\\r\\n> ro.build.version.codename    : REL\\r\\n> ro.build.type                : user\\r\\n> ro"
            + ".build.tags                : release-keys\\r\\n> ro.build.fingerprint         : "
            + "OnePlus/OnePlus5T/OnePlus5T:10/QKQ1.191014.012/2009080001:user/release-keys\\r\\n> ro.build.date.utc  "
            + "          : 1599495527000\\r\\n> ro.build.user                : jenkins\\r\\n> ro.build.host          "
            + "      : rd-build-107\\r\\n> ro.hardware                  : qcom\\r\\n> ro.product.board             : "
            + "msm8998\\r\\n> ro.product.brand             : OnePlus\\r\\n> ro.product.cpu.abi           : "
            + "armeabi-v7a\\r\\n> ro.product.cpu.abi2          : armeabi\\r\\n> ro.product.device            : "
            + "OnePlus5T\\r\\n> ro.product.manufacturer      : OnePlus\\r\\n> ro.product.model             : ONEPLUS "
            + "A5010\\r\\n> ro.product.name              : OnePlus5T\\r\\n> ro.serialno                  : "
            + "unknown\\r\\n> ro.versionName               : 4.8.1\\r\\n> ro.channel                   : "
            + "superhigh\\r\\n================================================================================\\r\\n"
            + "\",\"f\":2,\"l\":1605593426293,\"n\":\"main\",\"i\":2,\"m\":true}";
    private String wrongStr="{\"c\":\"ActivityLifeCycle:MainActivity "
            + "onActivity{\"c\":\"GTLog:======================== com.gaotu100.superclass: 4.8.1 "
            + "========================> ro.bootloader                : unknown\\r\\n> ro.build.id                  :"
            + " QKQ1.191014.012\\r\\n> ro.build.display.id          : ONEPLUS A5010_53_200908\\r\\n> ro.build.version"
            + ".incremental : 2009080001\\r\\n> ro.build.version.release     : 10\\r\\n> ro.build.version.sdk        "
            + " : 29\\r\\n> ro.build.version.codename    : REL\\r\\n> ro.build.type                : user\\r\\n> ro"
            + ".build.tags                : release-keys\\r\\n> ro.build.fingerprint         : "
            + "OnePlus/OnePlus5T/OnePlus5T:10/QKQ1.191014.012/2009080001:user/release-keys\\r\\n> ro.build.date.utc  "
            + "          : 1599495527000\\r\\n> ro.build.user                : jenkins\\r\\n> ro.build.host          "
            + "      : rd-build-107\\r\\n> ro.hardware                  : qcom\\r\\n> ro.product.board             : "
            + "msm8998\\r\\n> ro.product.brand             : OnePlus\\r\\n> ro.product.cpu.abi           : "
            + "armeabi-v7a\\r\\n> ro.product.cpu.abi2          : armeabi\\r\\n> ro.product.device            : "
            + "OnePlus5T\\r\\n> ro.product.manufacturer      : OnePlus\\r\\n> ro.product.model             : ONEPLUS "
            + "A5010\\r\\n> ro.product.name              : OnePlus5T\\r\\n> ro.serialno                  : "
            + "unknown\\r\\n> ro.versionName               : 4.8.1\\r\\n> ro.channel                   : "
            + "superhigh\\r\\n================================================================================\\r\\n"
            + "\",\"f\":2,\"l\":1605595374246,\"n\":\"main\",\"i\":2,\"m\":true}";
    private String rightStr2="{\"c\":\"Hubble:6030719470757888: {event_tag=, event_id=6030719470757888, "
            + "class=SplashActivity, splash_poster_type=2}\",\"f\":2,\"w\":1605600507883,\"n\":\"main\",\"i\":2,"
            + "\"m\":true}";

    private String otherStr="{\"dd\":{\"name\":\"wh\"}}";

    @Before
    public void before(){

    }


    @Test
    public void test1(){
        NALogItem naLogItem1 = JSON.parseObject(rightStr2, NALogItem.class);
        log.info("rightStr2 : {}",naLogItem1.toString());

        NALogItem naLogItem2 = JSON.parseObject(rightStr, NALogItem.class);
        log.info("right :{}",naLogItem2.toString());

        NALogItem naLogItem4 = JSON.parseObject(otherStr, NALogItem.class);
        log.info("other :{}",naLogItem4);

        try {
//            Object parse = JSON.parse(wrongStr);
            boolean valid = JSON.isValid(wrongStr);
            if(valid){
                log.info("is a JSONObject");
            }else {
                log.info("is not a JSONObject");
            }
            NALogItem naLogItem3 = JSON.parseObject(wrongStr, NALogItem.class);
        }catch (Exception e){
            log.error("解析出错：",e);
        }
    }

    @Test
    public void parseTest(){
        long startTime = System.currentTimeMillis();
        Path path = Paths.get("/Users/bjhl/workDir/tomcat/apache-tomcat-8.5.59/bin/logfile/1544382_androide4fef54e9c172029_2020-11-17_2173a68c-60e3-4f50-9b3e-4516b5120ecb.log");
        AtomicInteger count =new AtomicInteger();
        try (BufferedReader bufferedReader= Files.newBufferedReader(path)){
            String line;
            while ((line=bufferedReader.readLine())!=null){
                count.getAndIncrement();
                if(JSON.isValidObject(line)){
                    NALogItem naLogItem = JSON.parseObject(line, NALogItem.class);

                }else {
                    log.info("invalid json line :{}, content: {}",count.get(),line);
                }
            }
            log.info("total count {}",count.get());
            long endTime = System.currentTimeMillis();
            log.info("parse time :{}",endTime-startTime);

        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

}
