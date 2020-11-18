package posiedon.annotation;

import lombok.Data;

/**
 * @author weihai
 *
 * @desc description
 * @date 2020/11/17
 */
@Data
public class NALogItem {
    private String c;//content
    private String f;//logType
    private String l;//logTime
    private String n;//theadName
    private String i;//threadid
    private String m;//是否是主线程
}
