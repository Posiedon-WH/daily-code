package posiedon.test2;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import java.util.zip.GZIPOutputStream;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Utf8;
import com.google.common.collect.Lists;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.apache.zookeeper.Op;
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
       myBeans.stream()
                .peek(myBean -> myBean.setAge(myBean.getAge() + 1))
                .close();
        myBeans.forEach(System.out::println);
//        Map<Integer, List<String>> collect = myBeans.stream().collect(Collectors.groupingBy(MyBean::getAge, Collectors.mapping(MyBean::getName, Collectors.toList())));
//
//        ArrayList<Integer> integers = new ArrayList<>();
//        ArrayList<String> strings = new ArrayList<>();
//        System.out.println(integers.getClass());
//        System.out.println(strings.getClass());
//        System.out.println(integers.getClass()==strings.getClass());


    }

    @Test
    public void test(){
        ArrayList<MyBean> myBeans = new ArrayList<>();
        myBeans.add(new MyBean("1",1));
        myBeans.add(new MyBean("2",2));
        myBeans.add(new MyBean("3",3));
        myBeans.add(new MyBean("5",3));
        myBeans.add(new MyBean("6",3));
        myBeans.add(new MyBean("4",4));
        myBeans.add(new MyBean("7",4));
        myBeans.stream()
                .peek(myBean -> myBean.setAge(myBean.getAge() + 1))
                .collect(Collectors.toList());
        myBeans.forEach(System.out::println);
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

    @Test
    public void test7(){
        Map<String,String> map=new HashMap<>();
        map.put("fileName","www");
        map.put("fileUrl","ooo");
        log.info(JSON.toJSONString(map));
    }

    @Test
    public void test8() throws IOException {
        String ctx="{\"c\":\"log_tag_network:networkType : 4G -> [logg7 request]--> END POST (0-byte body)\",\"f\":2,\"l\":1618905878924,\"n\":\"RxCachedThreadScheduler-4\",\"i\":7510,\"m\":false}";
        String filePath="/Users/bjhl/workDir/tmp/2021-05-7-1";
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))){
            for (int i = 0; i < 60000; i++) {
                bufferedWriter.append(ctx);
            }
        }
    }

    @Test
    public void zip() throws IOException {
        byte[] buffer =  new byte[1024];
        try {
            GZIPOutputStream gos = new GZIPOutputStream(new FileOutputStream("/Users/bjhl/workDir/tmp/2021-05-10-2"));
            FileInputStream fis = new FileInputStream("/Users/bjhl/workDir/tmp/2021-05-10-1");
            int length;
            // fis.read(buffer)， 结果时Buffer有了内容，同时返回读取内容的长度，读到文件末尾时读取内容的长度变为-1
            while((length = fis.read(buffer)) > 0) {
                gos.write(buffer, 0, length);
            }
            fis.close();
            gos.finish();
            gos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }
    }

    @Test
    public void listTest(){
       /*Integer a=2;
       Integer b=7;
       log.info("a/b={}",(float)a/b);

        Map<Integer, String> map = new HashMap<>();
        String s = map.get(null);
        String orDefault = map.getOrDefault(null, "");
        log.info("s:{} , 2:{}",s,orDefault);

        String s2="239020382083qqqq";
        String substring = s2.substring(s2.length() - 4);
        log.info("sub :[{}]",substring);*/

       List<Long> list = new ArrayList<>();
       list.add(13L);
       list.add(8L);
       list.add(34L);
       list.add(10L);
        Long aLong = list.stream().max(Long::compareTo).orElse(0L);
        log.info("max : {}",aLong);

        Optional.ofNullable(null).ifPresent(it->{
            log.info("entry ");
        });
    }

    @Test
    public void testWhile(){
//        float a=3.2f; float b=4.3f;
//        int v = a - b>0?1:-1;
//        log.info("结果 {}",v);
//        ArrayList<String> strings = Lists.newArrayList("12", "34", "67");
//        log.info("list {}",strings);
//        List<String> versions=new ArrayList<>();
//        for (int i = 1; i <= strings.size(); i++) {
//            versions.add("V"+i);
//        }
//        log.info(" version {}",versions);

        /*List<Integer> status = new ArrayList<>();
        status.add(10);
        status.add(20);
        status.add(-1);
        log.info("before {}",status);
        int idx = status.indexOf(-2);
        if (idx > -1) {
            status.remove(idx);
            status.add(0);
        }

        log.info("result {}",status);*/

        String format = String.format("%s%s", 123, 653);
        log.info("str {}",format);
    }


    @Test
    public void testHttpImage() throws InterruptedException, ExecutionException, TimeoutException {
//        String url="https://pptr.gaotu100.com/?url=https%3A%2F%2Ftest-m.gaotu100.com%2FcodingActivity%2FstudyReport%2F%3Fuid%3D20424833568258%26clazzNumber%3D7473642503798912%26subclazzNumber%3D7473642504126592%26allowCmt%3D1%26type%3D1%26clazzLessonNumbers%3D7473648114073728%2C7473642503929987";
        String url="https://pptr.gaotu100.com/";
        Future<HttpResponse<InputStream>> responseFuture = Unirest.get(url)
                .queryString("url", "https%3A%2F%2Ftest-m.gaotu100.com%2FcodingActivity%2FstudyReport%2F%3Fuid%3D20424833568258%26clazzNumber%3D7473642503798912%26subclazzNumber%3D7473642504126592%26allowCmt%3D1%26type%3D1%26clazzLessonNumbers%3D7473648114073728%2C7473642503929987")
                .asBinaryAsync();
        HttpResponse<InputStream> httpResponse = responseFuture.get(20, TimeUnit.SECONDS);
        if(httpResponse.getStatus()== HttpStatus.SC_OK){
            InputStream body = httpResponse.getBody();
            log.info("调用 success {}",body);
        }else {
            log.error("调用 fail ");
        }
    }

    @Test
    public void base64Encode(){
        String jwt="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOjEzNzU5NSwiYXZhdGFyIjoiaHR0cHM6Ly9wLmdzeGNkbi5jb20vMTU2NjUxMjY2N19qMzJqZGI0bC5qcGciLCJtYWlsIjoibGlhaXhpYUBiYWlqaWEuY29tIiwiY29tcGFueSI6IuWMl-S6rOmrmOmAlOS6kembhuaVmeiCsuenkeaKgOaciemZkOWFrOWPuCIsImRlcGFydG1lbnQiOiLpq5jpgJTpm4blm6It5Lia5Yqh5LqU6YOoLee8lueoi-e0oOWFu-WtpumDqC3mlZnnoJTpg6gtQeS6p-WTgeaVmeeglOe7hCIsInVzZXJuYW1lIjoibGlhaXhpYSIsInJvbGUiOjAsIm5hbWUiOiLmnY7niLHpnJ4iLCJpYXQiOjE2NTMwMzY5NTMsImV4cCI6MTY1MzkwMDk2M30.l4ZAfLC0unp4E2VmZD9UVz0EUmUhj5MQtu4x3P6lAqk";
        String[] tokens = jwt.split("\\.");
        String body = tokens[1];
        Base64 base64 = new Base64(true);
        String str = new String(base64.decode(body));
        log.error("jwt content {}",str);
    }

    @Test
    public void testTime(){
        LocalDateTime start = LocalDateTime.of(2021, 12, 8, 12, 0, 0);
        LocalDateTime end = LocalDateTime.of(2020, 12, 8, 12, 0, 0);
        long between = ChronoUnit.SECONDS.between(start, end);
        log.info("时长 {}",between);
        log.info("时间 {}",start.format(DateTimeFormatter.ISO_LOCAL_DATE));
        log.info("时间 {}",start.format(DateTimeFormatter.ofPattern("yyyy_MM_dd")));

    }

    @Test
    public void testEncode() throws UnsupportedEncodingException {
        String str="沪LT2301";
        log.info(URLEncoder.encode(str, "UTF-8"));
        log.info(str.substring(0,1));
    }
}
