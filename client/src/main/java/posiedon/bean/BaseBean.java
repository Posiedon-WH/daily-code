package posiedon.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class BaseBean {

    private String userId;

    private String pkg;

    private String deviceId;

    private ActionEnum action;

    private Integer age;
}
