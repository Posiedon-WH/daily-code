package posiedon.test2;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.ContentType;
import org.junit.Assert;
import org.junit.Test;

import com.google.common.base.Charsets;

import lombok.extern.slf4j.Slf4j;
import posiedon.enums.MyTypeEnum;
import posiedon.test.MyBean;

@Slf4j
public class DailyTest {
    public static void main(String[] args) {
        ArrayList<MyBean> myBeans = new ArrayList<>();
        myBeans.add(new MyBean("1",1));
        myBeans.add(new MyBean("2",2));
        myBeans.add(new MyBean("3",3));
        myBeans.add(new MyBean("5",3));
        myBeans.add(new MyBean("6",3));
        myBeans.add(new MyBean("4",4));
        myBeans.add(new MyBean("7",4));
//        List<MyBean> beans = myBeans.stream()
//                .peek(myBean -> myBean.setAge(myBean.getAge() + 1))
//                .collect(Collectors.toList());
//        beans.forEach(System.out::println);
        Map<Integer, List<String>> collect = myBeans.stream().collect(Collectors.groupingBy(MyBean::getAge, Collectors.mapping(MyBean::getName, Collectors.toList())));

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

        MyBean myBean=null;
        if(Objects.isNull(myBean)){
            myBean=new MyBean();
            myBean.setAge(2);
        }

        log.info("myBean is {}",myBean);

        MyTypeEnum myTypeEnum = MyTypeEnum.fromCode(null);

        log.info("是否相等：{}",MyTypeEnum.TYPE_TWO.equals(myTypeEnum));

        if(Objects.isNull(myTypeEnum)){
            log.info("enum 3 ");
        }
        log.info("invalid enum {}",myTypeEnum);
    }

    @Test
    public void test2(){
        MyBean myBean = new MyBean();
        Integer age = myBean.getAge();
//        Assert.assertNull(age);
        age=1;
        switch (age){
            case 1:
            case 2:
                log.info("case 1 and 2");
                break;
            default:
                log.info("case default");
                break;

        }
        ArrayList<MyBean> myBeans = new ArrayList<>();
        myBeans.sort(Comparator.comparingInt(MyBean::getAge));

//        myBeans.add(new MyBean("1",1));
//        myBeans.add(new MyBean("2",2));
//        myBeans.add(new MyBean("3",3));
//        myBeans.add(new MyBean("5",3));
//        myBeans.add(new MyBean("6",3));
//        myBeans.add(new MyBean("4",4));
//        myBeans.add(new MyBean("7",4));
//        List<MyBean> beans = myBeans.stream()
//                .peek(myBean -> myBean.setAge(myBean.getAge() + 1))
//                .collect(Collectors.toList());
//        beans.forEach(System.out::println);
        Map<Integer, List<String>> collect = myBeans.stream().collect(Collectors.groupingBy(MyBean::getAge, Collectors.mapping(MyBean::getName, Collectors.toList())));

        ArrayList<Integer> integers = new ArrayList<>();

    }

    @Test
    public void test3() throws UnknownHostException {
        log.info(ContentType.TEXT_HTML.withCharset(Charsets.UTF_8).toString());
        log.info(Charsets.UTF_8.displayName());
        log.info(Charsets.UTF_8.name());
        log.info(Charsets.UTF_8.toString());
        String datacenter="DATACENTER";
        String hostName="HOSTNAME";
        String getenv = System.getenv(datacenter);
        log.info("datacenter is [{}]",getenv);
        String host = System.getenv(hostName);
        log.info("host name is [{}]",host);
        String hostName1 = InetAddress.getLocalHost().getHostName();
        log.info("host1 name is [{}]",hostName1);

    }

    @Test
    public void test4(){
        List<MyBean> emlist= Collections.emptyList();
        List<Integer> collect = emlist.stream().map(MyBean::getAge).collect(Collectors.toList());
        log.info("==emlist=={}===collect=={}",emlist,collect);
        Map<Integer, List<MyBean>> collect1 = emlist.stream().collect(Collectors.groupingBy(MyBean::getAge));
        log.info("===em map=={}==get0=={}",collect1,collect1.get(0));
        List<MyBean> collect2 = emlist.stream().filter(item -> Objects.equals(item.getName(), "123")).collect(Collectors.toList());
        log.info("collect2 :[{}]",collect2);
//        ArrayList<Long> longs = new ArrayList<>();
//        longs.add(12L);
//        longs.add(34L);
//        longs.add(0L);
//        longs.add(0L);
//        longs.add(0L);
//        log.info("origin === {}",longs);
//        longs.remove(0L);
//        log.info("after=={}",longs);

    }

    @Test
    public void test5(){
        Long maxTime=(-1L<<16);
        log.info(maxTime.toString());
        log.info(String.valueOf((~maxTime)));


    }

    @Test
    public void test6(){
        MyBean myBean = new MyBean();
        myBean=null;
        ArrayList<MyBean> myBeans = new ArrayList<>();
        MyBean myBean1 = myBeans.stream().findFirst().orElse(new MyBean("1",4));
//        log.info(myBean1.toString());
//        log.info( Optional.ofNullable(null).orElse(""));

        Map<Integer,Boolean> maps=new HashMap<>();
        maps.put(1,true);
        maps.put(2,false);
        maps.put(3,true);

        long count = maps.values().stream().filter(Boolean::booleanValue).count();
        log.info("right count[{}]",count);

    }
}
