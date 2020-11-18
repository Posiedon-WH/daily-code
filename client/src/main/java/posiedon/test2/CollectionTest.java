package posiedon.test2;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import posiedon.bean.MyBean;
import posiedon.enums.ResultEnum;

/**
 * @author weihai
 * @desc description
 * @date 2020/11/4
 */

public class CollectionTest {
    List<MyBean> list = new ArrayList<>(16);

    @Before
    public void before() {

        Stream.generate(new Random()::nextInt)
                .limit(10)
                .forEach(n -> {
                    list.add(new MyBean("posiedon" + n, n % 3, n % 4));
                });
    }

    @Test
    public void test3() {
        list.forEach(System.out::println);
        Map<Integer, List<Integer>> collect = list.stream()
                .collect(Collectors
                        .groupingBy(MyBean::getAge, Collectors.mapping(MyBean::getIncome, Collectors.toList())));
        collect.forEach((integer, integers) -> {
            System.out.println("key:"+integer);
            System.out.println("values:");
            integers.forEach(System.out::println);
        });

    }

    @Test
    public void test1() {
        ArrayList<MyBean> all = new ArrayList<>();
        ArrayList<MyBean> part = new ArrayList<>();
        MyBean bean1 = new MyBean("1", 1);
        MyBean bean2 = new MyBean("2", 2);
        MyBean bean3 = new MyBean("3", 3);
        MyBean bean4 = new MyBean("4", 4);
        MyBean bean5 = new MyBean("5", 5);

        all.add(bean1);
        all.add(bean2);
        all.add(bean3);
        all.add(bean4);
        part.add(bean1);
        part.add(bean2);

        all.forEach(System.out::println);
        System.out.println("====clear()===");
        all.clear();
        all.forEach(System.out::println);
        System.out.println("===add===");
        all.add(bean5);
        all.forEach(System.out::println);

        /*System.out.println("before=========");
        System.out.println("all:");
        all.forEach(System.out::println);
        System.out.println("part:");
        part.forEach(System.out::println);*/

        /*System.out.println("remove===");
        all.removeAll(part);
        System.out.println("all:");
        all.forEach(System.out::println);
        System.out.println("part:");
        part.forEach(System.out::println);*/

        /*System.out.println("retain===");
        all.retainAll(part);
        System.out.println("all:");
        all.forEach(System.out::println);
        System.out.println("part:");
        part.forEach(System.out::println);*/


    }

    @Test
    public void test2() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime day = now.plusDays(1).withHour(1).withMinute(0);
        LocalDateTime with = now.with(ChronoField.HOUR_OF_DAY, 12);
        System.out.println(now);
        System.out.println("======");
        System.out.println(day);
        System.out.println("=======");
        System.out.println(with);
    }

    @Test
    public void test4(){
        System.out.println(ResultEnum.ERROR_NOT_SUPPORT.name());
        System.out.println(ResultEnum.ERROR_NOT_SUPPORT.toString());
    }

    @Test
    public void test5() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(list);
        System.out.println(s);
        List<MyBean> myBeans = objectMapper.readValue(s, new TypeReference<List<MyBean>>() {});
        System.out.println("end");
    }
}
