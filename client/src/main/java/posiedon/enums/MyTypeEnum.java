package posiedon.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Getter;

/**
 * @author weihai
 * @desc description
 * @date 2020/11/9
 */
public enum MyTypeEnum {
    TYPE_ONE(1, "one"),
    TYPE_TWO(2, "two");
    @Getter
    private int code;
    @Getter
    private String desc;

    MyTypeEnum(int i, String desc) {
        this.code = i;
        this.desc = desc;
    }

    private static final class Holder {
        private static Map<Integer, MyTypeEnum> mapByCode =
                Arrays.stream(values()).collect(Collectors.toMap(MyTypeEnum::getCode, o -> o));
    }
    public static MyTypeEnum fromCode(Integer code) {
        return MyTypeEnum.Holder.mapByCode.get(code);
    }
}
