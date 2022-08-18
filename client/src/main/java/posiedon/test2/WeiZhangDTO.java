package posiedon.test2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author weihai
 * @desc description
 * @date 2021/8/11
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeiZhangDTO {

    //region 极速
    /**
     * 0:ok
     */
    @JsonProperty("status")
    private Integer status;

    @JsonProperty("msg")
    private String msg;

    @JsonProperty("result")
    private ResultBody result;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ResultBody {
        private String lsprefix;

        private String lsnum;

        private String carorg;

        private String usercarid;

        private Integer count;

        private Integer totalprice;

        private Integer totalscore;

        private List<Child> list;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Child {
        @JsonDeserialize(using = JacksonConfigDeserializer.JacksonLocalDateTimeToStringDeserializer.class)
        private LocalDateTime time;

        private String address;

        /**
         * 违章内容
         */
        private String content;

        /**
         * 违章代码
         */
        private String legalnum;

        /**
         * 罚款金额
         */
        private String price;

        /**
         * 扣分
         */
        private String score;

        private String city;
    }
    //endregion

    //region 车教授 返回体
    @JsonIgnoreProperties(ignoreUnknown = true)
    private List<DataItem> data;

    /**
     * code==1查询成功
     * 0	失败	其他错误
     * 1	成功
     * -1	接口错误	具体错误由msg字段描述
     * 502	系统错误
     * 40001	缺少参数	缺少的参数名在错误描述中
     * 40007	请求接口不存在
     * 60001	查询失败，违章查询接口出现异常，请稍后重试	违章查询时交管网返回错误
     * 60002	报价时无法匹配到城市
     * 60003	查询违章时车架号或者发动机号错误
     */
    private Integer code;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DataItem{

        @JsonProperty("CarNumber")
        private String carLicense;

        @JsonProperty("Time")
        @JsonDeserialize(using = JacksonConfigDeserializer.JacksonLocalDateTimeToStringDeserializer.class)
        private LocalDateTime time;

        @JsonProperty("Reason")
        private String reason;

        @JsonProperty("count")
        private String money;

        @JsonProperty("Degree")
        private Integer degree;

        @JsonProperty("Code")
        private String code;

        @JsonProperty("department")
        private String department;

        @JsonProperty("Location")
        private String address;

        @JsonProperty("LocationName")
        private String cityName;
    }
    //

}
