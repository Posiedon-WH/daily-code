package posiedon.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ChildBean extends BaseBean{

    private String name;

    private Long userCode;
}
