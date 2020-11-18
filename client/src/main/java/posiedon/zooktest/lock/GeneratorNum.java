package posiedon.zooktest.lock;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: Posiedon.wh
 * @Date: 2020/9/9 21:44
 */
public class GeneratorNum {
    static int count=0;
    public String getNum(){
        LocalDateTime localDateTime = LocalDateTime.now();
        SimpleDateFormat simpt = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        return simpt.format(new Date()) + "-" + ++count;
    }
}
