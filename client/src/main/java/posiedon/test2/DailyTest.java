package posiedon.test2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DailyTest {
    public static void main(String[] args) {
//        ArrayList<MyBean> myBeans = new ArrayList<>();
//        myBeans.add(new MyBean("1",1));
//        myBeans.add(new MyBean("2",2));
//        myBeans.add(new MyBean("3",3));
//        myBeans.add(new MyBean("4",4));
//        List<MyBean> beans = myBeans.stream()
//                .peek(myBean -> myBean.setAge(myBean.getAge() + 1))
//                .collect(Collectors.toList());
//        beans.forEach(System.out::println);

        ArrayList<Integer> integers = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();
//        System.out.println(integers.getClass());
//        System.out.println(strings.getClass());
//        System.out.println(integers.getClass()==strings.getClass());


    }

    @Test
    public void test1(){
        Object o=null;
        log.info("optional return value:{}", Optional.ofNullable(o).orElse(true));

        BigDecimal a=new BigDecimal(1);
        BigDecimal b =new BigDecimal(3);
        BigDecimal divide = a.divide(b, 2, BigDecimal.ROUND_HALF_UP);
        log.info("half up is {}",divide.toPlainString());

        log.info("aiandda isBlank {}",StringUtils.isBlank("aiadad"));
        log.info("sdsd asas dsds is blank {}",StringUtils.isBlank("sdsd asas dsds"));
        log.info("null is blank {}",StringUtils.isBlank(null));
        log.info("'' is blank {}",StringUtils.isBlank(""));
    }
}
