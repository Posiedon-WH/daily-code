package posiedon.test2;

import java.text.Collator;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
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

@Slf4j
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

        ArrayList<String> list = new ArrayList<>();
        list.add("123");
        list.add("23");
        list.add("12");

        boolean b1 = list.contains("3");
        boolean b2 = list.contains("23");
        log.info("3 {} 23 {}",b1,b2);
    }

    @Test
    public void test1() {
         Comparator<Object> CHINA_COMPARE = Collator.getInstance(java.util.Locale.CHINA);

        ArrayList<MyBean> all = new ArrayList<>();
        Map<String, MyBean> myBeanMap = all.stream().collect(Collectors.toMap(MyBean::getName, Function.identity()));
        MyBean orDefault = myBeanMap.getOrDefault("11", null);

        log.info("empty 【{}】",myBeanMap);
        log.info("获取第0个{}",all.stream().findFirst().orElse(null));
        List<MyBean> part = new ArrayList<>();
        MyBean bean1 = new MyBean("周万维", 1);
        MyBean bean2 = new MyBean("到我饿", 1);
        MyBean bean3 = new MyBean("历史", 1);
        MyBean bean4 = new MyBean("万维", 1);
        MyBean bean6 = new MyBean("啊", 1);
        MyBean bean5 = new MyBean("课23", 0);
//        bean1.setAge(null);
        all.add(bean1);
        all.add(bean2);
        all.add(bean3);
        all.add(bean4);
        all.add(bean5);
        all.add(bean6);

        List<MyBean> sortList = all.stream().sorted(Comparator.comparing(MyBean::getName, CHINA_COMPARE)).collect(Collectors.toList());

        log.info("sort list {}",sortList);
        log.info("all list {}",all);

//        Collections.sort(all, CHINA_COMPARE);
        log.info("sort2 list {}",all);

//        ArrayList<MyBean> finalList = new ArrayList<>();
//        finalList.addAll(all);
//        all.clear();
//
//        all.add(bean3);
//        all.add(bean4);
//        log.info("final list {} ",finalList);
//        log.info("all list {} ",all);
//        double average = all.stream().filter(it-> Objects.nonNull(it.getAge())).collect(Collectors.summarizingInt(MyBean::getAge)).getAverage();
//
//
//        all.stream()
//                .sorted(Comparator.comparing(MyBean::getAge,Comparator.reverseOrder()))
//                .forEach(myBean -> {
//                    boolean is = part.contains(myBean);
//                    if(!is){
//                        part.add(myBean);
//                    }
//                });
//        log.info("all list {}",all);
//        log.info("part list {}",part);
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
//        System.out.println(ResultEnum.ERROR_NOT_SUPPORT.name());
//        System.out.println(ResultEnum.ERROR_NOT_SUPPORT.toString());
//
//        String str="cjso送ID呢参数|按此单丛|参数女弄额|冲奶粉哦";
//
//        String substring = str.substring(str.length()-4, str.length());
////        String[] r = str.split("\\|");
//        log.info("sdsd:{}",substring);
        List<MyBean> list1=Collections.emptyList();
        List<MyBean> list2= Collections.emptyList();

        List<MyBean> collect = Stream.of(list1, list2)
                .flatMap(Collection::stream)
                .filter(item -> Objects.isNull(item.getAge()))
                .collect(Collectors.toList());

        log.info("wert we {}",collect);

        ArrayList<MyBean> myBeans = new ArrayList<>();
        Map<String, MyBean> emptyMap = myBeans.stream().collect(Collectors.toMap(MyBean::getName, Function.identity(), (v1, v2) -> v2));
        MyBean orDefault = emptyMap.getOrDefault("11", null);

        if(Objects.isNull(orDefault)){
            log.info("空11");
        }else {
            log.info("12323 {}",orDefault);
        }
    }

    @Test
    public void test5() throws JsonProcessingException, JSONException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        String s = objectMapper.writeValueAsString(list);
//        System.out.println(s);
//        List<MyBean> myBeans = objectMapper.readValue(s, new TypeReference<List<MyBean>>() {});
//        System.out.println("end");
//        MyBean myBean = new MyBean();
//        myBean.setMoney(122220L);
//        Long a=myBean.getMoney();
//
//        Long b=a;
//        a=29810L;
//
//        log.info("a {} b {} money {}",a,b,myBean.getMoney());


//        String json="\"{\"status\":1,\"msg\":\"223\",\"result\":\"\"}\"";
        String json="{\"code\":1,\"msg\":\"成功\",\"kf\":\"0\",\"IsLock\":\"0\",\"DriverCount\":\"0\",\"LockErrorCode\":\"0\",\"LastProcessTime\":\"0001/1/1 0:00:00\",\"LockItems\":null ,\"data\":{}}";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_TRAILING_TOKENS, true);
//        JSONObject jsonObject1 = new JSONObject(json);
//        JSONObject jsonObject = objectMapper.readValue(json, JSONObject.class);
        log.info("1111");
        WeiZhangDTO weiZhangDTO = objectMapper.readValue(json, WeiZhangDTO.class);
        log.info("input {} result :{}",json,weiZhangDTO);
    }

    @Test
    public void test6() {
       /* Integer val = 0;

        if (val > -1) {
            log.info("进入");
        }
        HashSet<Long> longs = new HashSet<>();
        longs.addAll(Collections.emptySet());
        boolean contains = longs.contains(124L);
        log.info("ssss{}",contains);

        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3, 4, 5, 6);
        for (Integer integer : integers) {
            log.info("数字 {}",integer);
            if(integer==2){
                return;
            }
        }*/

      /*  ArrayList<MyBean> list = new ArrayList<>();
        list.add(new MyBean("name1",1));
        list.add(new MyBean("name2",2));
        list.add(new MyBean("name3",3));
        list.add(new MyBean("name4",4));

        MyBean name1 = list.get(0);
        String name = name1.getName();
        String change = name.replace("name", "123");
        MyBean myBean = new MyBean();
        myBean.setName(change);
        myBean.setAge(3333);

        log.info("结果：{}",list);
        log.info("change name1 {}",name1);
        log.info("my name1 {}",myBean);

        long aLong = Instant.now().toEpochMilli();
        log.info("时间戳{}",aLong);
        Instant instant = Instant.ofEpochMilli(aLong);
        log.info("转化为时间 {}",instant);*/

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tmp = LocalDateTime.of(2022, 4, 27, 1, 33, 0);
        Duration between1 = Duration.between(now, tmp);
        Duration between2 = Duration.between(tmp, now);
        log.info("bet1 {} bet2 {}",between1.getSeconds(),between2.getSeconds());

    }
}
