package posiedon.bean;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author weihai
 * @desc description
 * @date 2020/10/21
 */
@Data
@Accessors(chain = true)
public class Author {

    private String name;

    private Integer income;

    private List<Hobby> hobbies;

    @Data
    public static class Hobby {
        private Integer id;
        private String name;


    }


}
