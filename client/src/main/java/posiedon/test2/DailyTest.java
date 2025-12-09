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
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.zip.GZIPOutputStream;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.net.URLDecoder;
import cn.hutool.core.util.*;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.digest.MD5;
import cn.hutool.crypto.symmetric.DES;
import cn.hutool.http.HtmlUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Supplier;
import com.google.common.base.Utf8;
import com.google.common.collect.Lists;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import lombok.Data;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.function.Function;
import net.objecthunter.exp4j.tokenizer.UnknownFunctionOrVariableException;
import org.apache.commons.codec.Decoder;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.apache.zookeeper.Op;
import org.junit.Assert;
import org.junit.Test;

import com.google.common.base.Charsets;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import posiedon.bean.ActionEnum;
import posiedon.bean.BaseBean;
import posiedon.bean.ChildBean;
import posiedon.enums.MyTypeEnum;
import posiedon.func.SelfFunc;
import posiedon.test.MyBean;

@Slf4j
public class DailyTest {
    public static void main(String[] args) {
        ArrayList<MyBean> myBeans = new ArrayList<>();
        myBeans.add(new MyBean("1", 1));
        myBeans.add(new MyBean("2", 2));
        myBeans.add(new MyBean("3", 3));
        myBeans.add(new MyBean("5", 3));
        myBeans.add(new MyBean("6", 3));
        myBeans.add(new MyBean("4", 4));
        myBeans.add(new MyBean("7", 4));
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
    public void test() {
        ArrayList<MyBean> myBeans = new ArrayList<>();
        myBeans.add(new MyBean("1", 1));
        myBeans.add(new MyBean("2", 2));
        myBeans.add(new MyBean("3", 3));
        myBeans.add(new MyBean("5", 3));
        myBeans.add(new MyBean("6", 3));
        myBeans.add(new MyBean("4", 4));
        myBeans.add(new MyBean("7", 4));
        myBeans.stream()
                .peek(myBean -> myBean.setAge(myBean.getAge() + 1))
                .collect(Collectors.toList());
        myBeans.forEach(System.out::println);
    }

    @Test
    public void test1() {
        Object o = null;
        log.info("optional return value:{}", Optional.ofNullable(o).orElse(true));

        BigDecimal a = new BigDecimal(1);
        BigDecimal b = new BigDecimal(3);
        BigDecimal divide = a.divide(b, 2, BigDecimal.ROUND_HALF_UP);
        log.info("half up is {}", divide.toPlainString());

        log.info("aiandda isBlank {}", StringUtils.isBlank("aiadad"));
        log.info("sdsd asas dsds is blank {}", StringUtils.isBlank("sdsd asas dsds"));
        log.info("null is blank {}", StringUtils.isBlank(null));
        log.info("'' is blank {}", StringUtils.isBlank(""));

        MyBean myBean = null;
        if (Objects.isNull(myBean)) {
            myBean = new MyBean();
            myBean.setAge(2);
        }

        log.info("myBean is {}", myBean);

        MyTypeEnum myTypeEnum = MyTypeEnum.fromCode(null);

        log.info("是否相等：{}", MyTypeEnum.TYPE_TWO.equals(myTypeEnum));

        if (Objects.isNull(myTypeEnum)) {
            log.info("enum 3 ");
        }
        log.info("invalid enum {}", myTypeEnum);
    }

    @Test
    public void test2() {
        MyBean myBean = new MyBean();
        Integer age = myBean.getAge();
//        Assert.assertNull(age);
        age = 1;
        switch (age) {
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
        String datacenter = "DATACENTER";
        String hostName = "HOSTNAME";
        String getenv = System.getenv(datacenter);
        log.info("datacenter is [{}]", getenv);
        String host = System.getenv(hostName);
        log.info("host name is [{}]", host);
        String hostName1 = InetAddress.getLocalHost().getHostName();
        log.info("host1 name is [{}]", hostName1);

    }

    @Test
    public void test4() {
        List<MyBean> emlist = Collections.emptyList();
        List<Integer> collect = emlist.stream().map(MyBean::getAge).collect(Collectors.toList());
        log.info("==emlist=={}===collect=={}", emlist, collect);
        Map<Integer, List<MyBean>> collect1 = emlist.stream().collect(Collectors.groupingBy(MyBean::getAge));
        log.info("===em map=={}==get0=={}", collect1, collect1.get(0));
        List<MyBean> collect2 = emlist.stream().filter(item -> Objects.equals(item.getName(), "123")).collect(Collectors.toList());
        log.info("collect2 :[{}]", collect2);
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
    public void test5() {
        Long maxTime = (-1L << 16);
        log.info(maxTime.toString());
        log.info(String.valueOf((~maxTime)));


    }

    @Test
    public void test6() {
//        MyBean myBean = new MyBean();
//        myBean=null;
//        ArrayList<MyBean> myBeans = new ArrayList<>();
//        MyBean myBean1 = myBeans.stream().findFirst().orElse(new MyBean("1",4));
////        log.info(myBean1.toString());
////        log.info( Optional.ofNullable(null).orElse(""));
//
//        Map<Integer,Boolean> maps=new HashMap<>();
//        maps.put(1,true);
//        maps.put(2,false);
//        maps.put(3,true);
//
//        long count = maps.values().stream().filter(Boolean::booleanValue).count();
//        log.info("right count[{}]",count);

        BaseBean exist = new BaseBean();
        exist.setDeviceId("124");
        exist.setPkg("pkg");

        MyBean newBean = new MyBean();
        newBean.setAge(11);
        BeanUtil.copyProperties(newBean, exist);
        log.info("exist bean is {}", exist);

    }

    @Test
    public void test7() {
        Map<String, String> map = new HashMap<>();
        map.put("fileName", "www");
        map.put("fileUrl", "ooo");
        log.info(JSON.toJSONString(map));
    }

    @Test
    public void test8() throws IOException {
        String ctx = "{\"c\":\"log_tag_network:networkType : 4G -> [logg7 request]--> END POST (0-byte body)\",\"f\":2,\"l\":1618905878924,\"n\":\"RxCachedThreadScheduler-4\",\"i\":7510,\"m\":false}";
        String filePath = "/Users/bjhl/workDir/tmp/2021-05-7-1";
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < 60000; i++) {
                bufferedWriter.append(ctx);
            }
        }
    }

    @Test
    public void zip() throws IOException {
        byte[] buffer = new byte[1024];
        try {
            GZIPOutputStream gos = new GZIPOutputStream(new FileOutputStream("/Users/bjhl/workDir/tmp/2021-05-10-2"));
            FileInputStream fis = new FileInputStream("/Users/bjhl/workDir/tmp/2021-05-10-1");
            int length;
            // fis.read(buffer)， 结果时Buffer有了内容，同时返回读取内容的长度，读到文件末尾时读取内容的长度变为-1
            while ((length = fis.read(buffer)) > 0) {
                gos.write(buffer, 0, length);
            }
            fis.close();
            gos.finish();
            gos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

    @Test
    public void listTest() {
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
        log.info("max : {}", aLong);

        Optional.ofNullable(null).ifPresent(it -> {
            log.info("entry ");
        });
    }

    @Test
    public void testWhile() {
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
        log.info("str {}", format);
    }


    @Test
    public void testHttpImage() throws InterruptedException, ExecutionException, TimeoutException {
//        String url="https://pptr.gaotu100.com/?url=https%3A%2F%2Ftest-m.gaotu100.com%2FcodingActivity%2FstudyReport%2F%3Fuid%3D20424833568258%26clazzNumber%3D7473642503798912%26subclazzNumber%3D7473642504126592%26allowCmt%3D1%26type%3D1%26clazzLessonNumbers%3D7473648114073728%2C7473642503929987";
        String url = "https://pptr.gaotu100.com/";
        Future<HttpResponse<InputStream>> responseFuture = Unirest.get(url)
                .queryString("url", "https%3A%2F%2Ftest-m.gaotu100.com%2FcodingActivity%2FstudyReport%2F%3Fuid%3D20424833568258%26clazzNumber%3D7473642503798912%26subclazzNumber%3D7473642504126592%26allowCmt%3D1%26type%3D1%26clazzLessonNumbers%3D7473648114073728%2C7473642503929987")
                .asBinaryAsync();
        HttpResponse<InputStream> httpResponse = responseFuture.get(20, TimeUnit.SECONDS);
        if (httpResponse.getStatus() == HttpStatus.SC_OK) {
            InputStream body = httpResponse.getBody();
            log.info("调用 success {}", body);
        } else {
            log.error("调用 fail ");
        }
    }

    @Test
    public void base64Encode() {
        String jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOjEzNzU5NSwiYXZhdGFyIjoiaHR0cHM6Ly9wLmdzeGNkbi5jb20vMTU2NjUxMjY2N19qMzJqZGI0bC5qcGciLCJtYWlsIjoibGlhaXhpYUBiYWlqaWEuY29tIiwiY29tcGFueSI6IuWMl-S6rOmrmOmAlOS6kembhuaVmeiCsuenkeaKgOaciemZkOWFrOWPuCIsImRlcGFydG1lbnQiOiLpq5jpgJTpm4blm6It5Lia5Yqh5LqU6YOoLee8lueoi-e0oOWFu-WtpumDqC3mlZnnoJTpg6gtQeS6p-WTgeaVmeeglOe7hCIsInVzZXJuYW1lIjoibGlhaXhpYSIsInJvbGUiOjAsIm5hbWUiOiLmnY7niLHpnJ4iLCJpYXQiOjE2NTMwMzY5NTMsImV4cCI6MTY1MzkwMDk2M30.l4ZAfLC0unp4E2VmZD9UVz0EUmUhj5MQtu4x3P6lAqk";
        String[] tokens = jwt.split("\\.");
        String body = tokens[1];
        Base64 base64 = new Base64(true);
        String str = new String(base64.decode(body));
        log.error("jwt content {}", str);
    }

    @Test
    public void testTime() {
//        LocalDateTime start = LocalDateTime.of(2021, 12, 8, 12, 0, 0);
//        LocalDateTime end = LocalDateTime.of(2020, 12, 8, 12, 0, 0);
//        long between = ChronoUnit.SECONDS.between(start, end);
//        log.info("时长 {}", between);
//        log.info("时间 {}", start.format(DateTimeFormatter.ISO_LOCAL_DATE));
//        log.info("时间 {}", start.format(DateTimeFormatter.ofPattern("yyyy_MM_dd")));

        LocalDateTime now = LocalDateTime.now();
         now = now.plusDays(0);
        log.info("now {}", now);

    }

    @Test
    public void testEncode() throws UnsupportedEncodingException {
        String str = "沪LT2301";
        log.info(URLEncoder.encode(str, "UTF-8"));
        log.info(str.substring(0, 1));
    }

    @Test
    public void testTime1() {
        int a=6;
        int v = (a / 4) + 1;
        log.info("v is {}", v);

        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        List<Integer> origin = new ArrayList<>(list1);
        list1.removeAll(List.of(1));
        log.info("origin {} , after {}",origin,list1);

//        LocalDateTime parse = LocalDateTime.parse("2023-10-12");
//
//        LocalDateTime now = LocalDateTime.now().plusSeconds(1);
//        LocalDateTime start = now.minusHours(1);
//        log.info("start {}",start);
//        log.info("now {}",now);
//         String REDUCTION_TIPS = "The system has not received confirmation from the ad platform, and the reward of <color=#ED1765>%s</color> coins will be deducted. If you have any questions, please contact customer support.";
//
//        String format = String.format(REDUCTION_TIPS, 500L);
//        log.info("format {}",format);
//
//        long a=12;
//        long b=11;
//        log.info("a/b={}",(float)b/a);
//        if((float) b/a>0.8f){
//            log.info("大于");
//        }

//        int radio=33;
//        int click=90;
//        log.info("result:{}",click*radio/100);
//
//        for (int i = 0; i < 10; i++) {
//                log.info("i is {}",i);
//            if(i==5){
//                break;
//            }
//        }
//        log.info("end......");
//        long a=5;
//        long b=1;
//       if((double) b/a>0.2){
//           log.info("111");
//       }

//        List<String> list=new ArrayList<>();
//        list.add("1");
//        list.add("2");
//        int i = list.indexOf("2");
//        log.info("i :{}",i);
        Pair<Integer, Integer> pair = Pair.of(1234, 456);
    }


    @Test
    public void test111() {
        String originalData = "{\"params\":\"mnc=&pkg=com.techque.hello.words&model=iPhone14,5&tz=Asia%2FShanghai&soc=Optional(1.0)&os=iOS&ver=1.0.1&vpn=false&jbf=281500746514432&lang=zh&osv=18.3.1&rr=390.0*844.0&idfv=6BAF6F4E-AC6D-401F-A556-D7145481BD11&tk=com.techque.hello.words7A6069C9-AF03-4274-8831-ACF8C56B987B&mcc=null&adid=null&jb=false\",\"method\":\"POST\",\"body\":\"{\\\"pkg\\\":\\\"com.techque.hello.words\\\",\\\"deviceId\\\":\\\"com.techque.hello.words7A6069C9-AF03-4274-8831-ACF8C56B987B\\\"}\",\"headers\":{\"Content-Type\":\"application\\/json\"},\"path\":\"\\/auth\\/email\\/login\\/anonymous\"}";
        String key = "6f8ccf5b38c3e1f1d00364135d890a53";

        String md5 = MD5.create().digestHex(key).toUpperCase();
        DES des = new DES(Mode.ECB, Padding.PKCS5Padding, md5.getBytes(StandardCharsets.UTF_8));
        String encryptHex = des.encryptHex(originalData);
        System.out.println(encryptHex);
//        encryptHex="6cHSIdjqz5O1AA8C57XTJRIM1AUHa1wxx8jg1dtSdYC6f7H5Iw0Q3ClOaej/H9Jg+AX3BPKlGUYaCnzll/2ytiZr6bsHv3GNrTGWK88ScbVZy7MWTy6EFVxjQq0R66nDsknUZa0We5HRJtW23I7IDdZy2O/79FTaBdRnFlxZqADz8qpCwkF6xvpRAqMMpLCtznnBHNsiUsJqF4hGiH64o6x6aN5lHstOxOLoulHL5+vHnM/84la80fGgqCok+ZyQbyMP3W+VN05ZPUa0gl+0XjbmjkUqxkrsNEaxedciKWP9NDUNV5gOp1/bWgDFGht+jO+DfZM7bSHDJOHqCGRuvwqEJcrZGqZfhL6FLL3EUkWbPZWGYU7IWqSjk1wH4oWARNEyTxIVjSwIbCf+Wxu6jp+DGshg7UMqKb6D+cAItRQp08ZCalunRwsgUQYhKXugXHQs7yAc3I5ywmeorCB4V8fI4NXbUnWAun+x+SMNENxva4uZrAoV6XMIRCBGM8VNaQ7GxBx98ZBj0dE+Mrk1R+A434bEYgzV00r8407D3OQsnB/k9rWaZ/GbuDBzZjKHU459ZVBR8lMqGjXjtoFx6R/gK9vxWZq+RXTnshUHiai/PTyH34ULhfpxm6zUkj3dcJgeQpUOcTN4k8wW4NPc6NaPm1o6f0SEfloMSM/Z4JwIbCxYL62TxHhNo2cAlKNrDVfs9DSywpHnPmuaTgUAaA==";
        String decryptStr = des.decryptStr(encryptHex);
        System.out.println(decryptStr);
    }

    @Test
    public void test23() {
//        ChildBean baseBean = new ChildBean();
//        baseBean.setAction(ActionEnum.END);
//        baseBean.setUserId("124");
//        baseBean.setDeviceId("556");
//        baseBean.setName("654");
//
//        String jsonStr = JSONUtil.toJsonStr(baseBean);
//        log.info("json str {}",jsonStr);
//
//        ActionEnum action = JSONUtil.parseObj(jsonStr).getEnum(ActionEnum.class, "action");
//        log.info( "action {}",action);
//        BaseBean bean = JSONUtil.toBean(jsonStr, ChildBean.class, false);
//
//        log.info( "bean {}",bean.getUserId());
//        log.info( "bean {}",bean);

        Double v = Double.valueOf(7.64021E-4);
        log.info("v {}", v);

        Double a = 0.0079323809523809546;
        Long l = test1(a);
        Double b = 0.00793238;
        Long l1 = test1(b);
        log.info("l {}", l);
        log.info("l1 {}", l1);

        double ad = NumberUtil.round(a, 8, RoundingMode.DOWN).doubleValue();
        double bd = NumberUtil.round(b, 8, RoundingMode.DOWN).doubleValue();
        log.info("ad {}", ad);
        log.info("bd {}", bd);

    }

    private Long test1(Double revenue) {
        Optional<Double> coinsOptional = Optional.ofNullable(revenue)
                .filter(r -> r > 0)
                .map(r -> r * 1_000_000)
                .or(() -> Optional.of(0D));
        long value = (long) (33 * coinsOptional.get() / 100);
        return value;
    }




    @Test
    public void testVer() {
        String ver = "1.1.12";
        String verStr = "1.0.1-1.2.1";
        boolean b = VersionUtil.matchEl(ver, verStr);
        log.info("ver is {}", b);
        String ver2 = "1.2.0";
        boolean b1 = VersionUtil.matchEl(ver2, verStr);
        log.info("ver2 is {}", b1);
        String ver3 = "1.2.1";
        boolean b2 = VersionUtil.matchEl(ver3, verStr);
        log.info("ver3 is {}", b2);
        String ver4 = "1.2.2";
        boolean b3 = VersionUtil.matchEl(ver4, verStr);
        log.info("ver4 is {}", b3);
        float v = 3 / 2f;
        log.info("float:{}", v);
    }

    @Test
    public void testRe() {
        log.info("-1:{}", ReUtil.isMatch("^(10|11|12|201|202|203|200)$", "22"));
//        String[] split = "ssd".split(",");
//        log.info("ss:{}",split[0]);
//
//
//        boolean match = ReUtil.isMatch("^\\d+,\\d+,\\d+(;\\d+,\\d+,\\d+)*;?$", "1,10,80;10,30,20;");
//        log.info( "match {}",match);
//
//        boolean match1 = ReUtil.isMatch("^\\d+(\\.\\d+)?,\\d+(\\.\\d+)?$", "2.3,3");
//        log.info( "match1 {}",match1);
//        log.info( "match1 {}",ReUtil.isMatch("^\\d+(\\.\\d+)?,\\d+(\\.\\d+)?$", "2.3,3.1"));
//        log.info( "match1 {}",ReUtil.isMatch("^\\d+(\\.\\d+)?,\\d+(\\.\\d+)?$", "3,3.1"));
//        log.info( "match1 {}",ReUtil.isMatch("^\\d+(\\.\\d+)?,\\d+(\\.\\d+)?$", "3,5,"));

//        List<Integer> list = IntStream.rangeClosed(2, 2).boxed().collect(Collectors.toList());
//        log.info("list {}",list);
//
//
//       log.info("int :{}",NumberUtil.isInteger("-1"));

//       log.info("int2 :{}",ReUtil.isMatch("^[0-9]\\d*$","-1"));
//        log.info("int3 :{}",ReUtil.isMatch("^[0-9]\\d*$","-1.0"));
//        log.info("int4 :{}",ReUtil.isMatch("^[0-9]\\d*$","0.0"));
//        log.info("int4 :{}",ReUtil.isMatch("^[0-9]\\d*$","0"));
//        log.info("int4 :{}",ReUtil.isMatch("^[0-9]\\d*$","122"));
//        log.info("int4 :{}",ReUtil.isMatch("^(100|[1-9]?[0-9])$","122"));
//        log.info("int4 :{}",ReUtil.isMatch("^(100|[1-9]?[0-9])$","0"));
//        log.info("int4 :{}",ReUtil.isMatch("^(100|[1-9]?[0-9])$","-10"));
//        log.info("int4 :{}",ReUtil.isMatch("^(100|[1-9]?[0-9])$","90"));
//        log.info("int4 :{}",ReUtil.isMatch("^(100|[1-9]?[0-9])$","100"));
//        log.info("int4 :{}",ReUtil.isMatch("^(100|[1-9]?[0-9])$","10.3"));

//        log.info("1:{}",ReUtil.isMatch("^(101|102|301|302)(,(101|102|301|302))*$", "102"));
//        log.info("2:{}",ReUtil.isMatch("^(101|102|301|302)(,(101|102|301|302))*$", "102,"));
//        log.info("2:{}",ReUtil.isMatch("^(101|102|301|302)(,(101|102|301|302))*$", "10"));
//        log.info("2:{}",ReUtil.isMatch("^(101|102|301|302)(,(101|102|301|302))*$", "101,102,301"));
//        log.info("2:{}",ReUtil.isMatch("^(101|102|301|302)(,(101|102|301|302))*$", "302,102,301"));
//        log.info("2:{}",ReUtil.isMatch("^(101|102|301|302)(,(101|102|301|302))*$", "302,102,301"));
//        log.info("2:{}",ReUtil.isMatch("^(101|102|301|302)(,(101|102|301|302))*$", "302,102,301,"));

        log.info("-1:{}", ReUtil.isMatch("^(1|2|3|4)$", "23"));

        log.info("num:{}", BigDecimal.valueOf(3.136768271E+10).toPlainString());
        HashMap<String, Object> map = new HashMap<>();
        map.put("a", 1);
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("orderId", "1234567890");
        map.putAll(paramMap);
        log.info("map:{}", JSONUtil.toJsonStr(map));
    }

    @Test
    public void testGs() {

        // 自定义 max 函数（两个参数）
        Function max = new Function("max", 2) {
            @Override
            public double apply(double... args) {
                return Math.max(args[0], args[1]);
            }
        };

        // 自定义 min 函数（两个参数）
        Function min = new Function("min", 2) {
            @Override
            public double apply(double... args) {
                return Math.min(args[0], args[1]);
            }
        };

        // random 函数
        Function random = new Function("random", 2) {
            @Override
            public double apply(double... args) {
                double min = args[0];
                double max = args[1];
                if (max < min) {
                    throw new IllegalArgumentException("Max must be greater than or equal to Min");
                }
                return Math.floor(min + Math.random() * (max - min + 1));
            }
        };
        Map<String, Object> variableMap = new HashMap<>();
        variableMap.put("currentEcpm", 1000000d);
        variableMap.put("currentRatio", 1);
        variableMap.put("c", 2d);
        variableMap.put("d", 8d);
        variableMap.put("e", 4d);
        variableMap.put("g", 1);
        variableMap.put("h", 9);
//        Expression exp = new ExpressionBuilder("max(a, b) + min(c, d) * e")
        Expression exp = new ExpressionBuilder("currentEcpm*currentRatio*0.95+10")
                .variables("currentEcpm", "currentRatio", "c", "d", "e", "g", "h")
                .function(max)
                .function(min)
                .function(random)
                .build();
//                .setVariable("a", 5)
//                .setVariable("b", 3)
//                .setVariable("c", 2)
//                .setVariable("d", 8)
//                .setVariable("e", 4)
//                .setVariable("g", 0);
        for (Map.Entry<String, Object> entry : variableMap.entrySet()) {
            exp.setVariable(entry.getKey(), ((Number) entry.getValue()).doubleValue());
        }
        double evaluate = exp.evaluate();
        log.info("re:{}", evaluate);

//        try {
////            Expression expression = new ExpressionBuilder("max(a, b) + min(c, d) * e")
//            Expression expression = new ExpressionBuilder("1000+random(g,h)")
//                    .variables("b", "c", "d", "e", "g", "h")
//                    .function(max)
//                    .function(min)
//                    .function(random)
//                    .build();
//            log.info("result:{}", true);
//        } catch (UnknownFunctionOrVariableException e) {
//            System.out.println("未知变量或函数: " + e.getMessage());
//        } catch (IllegalArgumentException e) {
//            System.out.println("表达式语法错误: " + e.getMessage());
//        } catch (Exception e) {
//            System.out.println("其他错误: " + e.getMessage());
//        }
    }

    @Test
    public void testAtom() {
        AtomicLong atomicLong = new AtomicLong(10);
        long l = atomicLong.addAndGet(-1);
        log.info("l {}", l);
        long l1 = atomicLong.addAndGet(-4);
        log.info("l1 {}", l1);

        HashMap<String, String> maps = new HashMap<>();
        maps.put("1", "2");
        maps.put("2", "2");
        maps.put("3", "2");
        maps.put("4", "2");
        maps.forEach((key, val) -> {
            if (key == "3") {
                return;
            }
            log.info("key:{}", key);
        });

        log.info("end");

        List<String> list = new ArrayList<>();
        Map<String, String> map = new LinkedHashMap<>();
        list.add("302");
        list.add("102");
        list.add("101");
        list.add("301");
        list.stream()
//                .sorted()
                .forEach(it -> {
                    log.info("it {}", it);
                    map.put(it, it);
                });

        map.forEach((key, val) -> {
            log.info("key:{}", key);
        });
    }

    @Test
    public void testNum() {
        log.info("sss:{}", Integer.MAX_VALUE);
//        Integer i = Integer.valueOf("10000000000");
        Integer j = Integer.valueOf("2147483647");

        long ceil = (long) Math.ceil(10000L * 20d / 100);
        log.info("ceil:{}", ceil);

        boolean b = StringUtils.contains("ocean shooter test 字母分组,ocean shooter test 数字分组2", "ocean shooter test 数字分组");
        log.info("contains any:{}", b);
        ArrayList<String> list = new ArrayList<>();
        boolean contains = list.contains("33");
        log.info("contains:{}", contains);
    }

    @Test
    public void test9() throws JsonProcessingException {
        String errorMsg = "Paypal error: {\"name\":\"USER_BUSINESS_ERROR\",\"message\":\"User business error.\",\"debug_id\":\"f43cbbcdc88bd\",\"information_link\":\"https://developer.paypal.com/docs/api/payments.payouts-batch/#errors\",\"details\":[{\"field\":\"SENDER_BATCH_ID\",\"location\":\"body\",\"issue\":\"Batch with given sender_batch_id already exists\",\"link\":[{\"href\":\"https://api.paypal.com/v1/payments/payouts/H3NESG8JA8UVL/12s/\",\"rel\":\"self\",\"method\":\"GET\",\"encType\":\"application/json\"}]}],\"links\":[]}";
        errorMsg = errorMsg.replace("Paypal error: ", "");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode details = mapper.readTree(errorMsg).path("details");
        JsonNode link = details.get(0).path("link");
        String href = link.get(0).path("href").asText();
        log.info("href: {} ", href);
        // https://api.paypal.com/v1/payments/payouts/H3NESG8JA8UVL
        String payoutId = href.replaceAll(".*payouts/([^/]+).*", "$1");
        log.info("payoutId: {}", payoutId);

    }

    @Test
    public void test10() {

        LocalDateTime now = LocalDateTime.now(ZoneId.of("Etc/GMT+11"));
        log.info("time:{}", now);

        String tz = URLDecoder.decode("Etc/GMT+4", StandardCharsets.UTF_8);
        log.info("tz :{}", tz);
        String tz1 = "Asia%2FShanghai";
        tz1 = tz1.replace("%2F", "/");
        log.info("tz1 :{}", tz1);

        ZoneId zoneId = ZoneId.of("Etc/GMT+4");
        ZonedDateTime zonedDateTime = Instant.ofEpochMilli(System.currentTimeMillis()).atZone(zoneId);
        log.info("zonedDateTime:{}", zonedDateTime);
    }

    @Test
    public void test11() {
//        String resp1 = HttpUtil.get("https://storage.googleapis.com/play_public/supported_devices.html");
//        log.info( "resp1:{}", resp1);
        String resp = "<table class=\"nice-table\">\n" +
                "  <tr>\n" +
                "    <th>Retail Branding</th>\n" +
                "    <th>Marketing Name</th>\n" +
                "    <th>Device</th>\n" +
                "    <th>Model</th>\n" +
                "  </tr>\n" +
                "\n" +
                "  <tr>\n" +
                "    <td></td>\n" +
                "    <td></td>\n" +
                "    <td>AD681H</td>\n" +
                "    <td>Smartfren Andromax AD681H</td>\n" +
                "  </tr>\n" +
                "\n" +
                "  <tr>\n" +
                "    <td></td>\n" +
                "    <td></td>\n" +
                "    <td>FJL21</td>\n" +
                "    <td>FJL21</td>\n" +
                "  </tr>\n" +
                "\n" +
                "  <tr>\n" +
                "    <td></td>\n" +
                "    <td></td>\n" +
                "    <td>hws7721g</td>\n" +
                "    <td>MediaPad 7 Youth 2</td>\n" +
                "  </tr>\n" +
                "\n" +
                "  <tr>\n" +
                "    <td>1&amp;1</td>\n" +
                "    <td>1&amp;1 Puck</td>\n" +
                "    <td>diw362_1u1</td>\n" +
                "    <td>DIW362P 1U1</td>\n" +
                "  </tr>\n" +
                "\n" +
                "  <tr>\n" +
                "    <td>1&amp;1</td>\n" +
                "    <td>1&amp;1 TV BOX</td>\n" +
                "    <td>DIW377-1u1</td>\n" +
                "    <td>DIW377 1u1</td>\n" +
                "  </tr>\n" +
                "\n" +
                "  <tr>\n" +
                "    <td>1&amp;1</td>\n" +
                "    <td>1&amp;1 TV Box</td>\n" +
                "    <td>diw387_1u1</td>\n" +
                "    <td>DIW387 1U1</td>\n" +
                "  </tr>\n" +
                "\n" +
                "  <tr>\n" +
                "    <td>1&amp;1</td>\n" +
                "    <td>AEOP525</td>\n" +
                "    <td>AEOP525</td>\n" +
                "    <td>AEOP525</td>\n" +
                "  </tr>\n" +
                "\n" +
                "  <tr>\n" +
                "    <td>1&amp;1</td>\n" +
                "    <td>DIW387-1&amp;1</td>\n" +
                "    <td>diw387_1u1</td>\n" +
                "    <td>DIW387 1U1</td>\n" +
                "  </tr>\n" +
                "\n" +
                "  <tr>\n" +
                "    <td>10.or</td>\n" +
                "    <td>10or_G2</td>\n" +
                "    <td>G2</td>\n" +
                "    <td>10or G2</td>\n" +
                "  </tr>\n" +
                "\n" +
                "  <tr>\n" +
                "    <td>10.or</td>\n" +
                "    <td>D</td>\n" +
                "    <td>10or_D</td>\n" +
                "    <td>D</td>\n" +
                "  </tr>\n" +
                "\n" +
                "  <tr>\n" +
                "    <td>10.or</td>\n" +
                "    <td>E</td>\n" +
                "    <td>E</td>\n" +
                "    <td>E</td>\n" +
                "  </tr>\n" +
                "\n" +
                "  <tr>\n" +
                "    <td>10.or</td>\n" +
                "    <td>G</td>\n" +
                "    <td>G</td>\n" +
                "    <td>G</td>\n" +
                "  </tr>\n" +
                "\n" +
                "  <tr>\n" +
                "    <td>10.or</td>\n" +
                "    <td>G2</td>\n" +
                "    <td>G2</td>\n" +
                "    <td>G2</td>\n" +
                "  </tr>\n" +
                "\n" +
                "  <tr>\n" +
                "    <td>10.or</td>\n" +
                "    <td>TCL 60 XE NXTPAPER 5G</td>\n" +
                "    <td>Sunrise_CAN</td>\n" +
                "    <td>T705J</td>\n" +
                "  </tr>\n" +
                "\n" +
                "  <tr>\n" +
                "    <td>2E (ERC)</td>\n" +
                "    <td>2E 2K Google TV</td>\n" +
                "    <td>capitolhill</td>\n" +
                "    <td>2E 2K Google TV</td>\n" +
                "  </tr>\n" +
                "\n" +
                "  <tr>\n" +
                "    <td>2E (ERC)</td>\n" +
                "    <td>2E 4K Google TV</td>\n" +
                "    <td>keoneae</td>\n" +
                "    <td>2E 4K Google TV</td>\n" +
                "  </tr>\n" +
                "\n" +
                "  <tr>\n" +
                "    <td>3222222 Satelital</td>\n" +
                "    <td>G706</td>\n" +
                "    <td>G706</td>\n" +
                "    <td>G706</td>\n" +
                "  </tr>\n" +
                "\n" +
                "  <tr>\n" +
                "    <td>3BBTV</td>\n" +
                "    <td>3BBTV</td>\n" +
                "    <td>S3000</td>\n" +
                "    <td>S3000-T0C08</td>\n" +
                "  </tr>\n" +
                "\n" +
                "  <tr>\n" +
                "    <td>3BBTV</td>\n" +
                "    <td>TBBTV01</td>\n" +
                "    <td>TBBTV01</td>\n" +
                "    <td>TBBTV01</td>\n" +
                "  </tr>\n" +
                "\n" +
                "  <tr>\n" +
                "    <td>3Go</td>\n" +
                "    <td>GT10K3IPS</td>\n" +
                "    <td>GT10K3IPS</td>\n" +
                "    <td>GT10K3IPS</td>\n" +
                "  </tr>\n" +
                "\n" +
                "  <tr>\n" +
                "    <td>3Go</td>\n" +
                "    <td>GT70053G</td>\n" +
                "    <td>GT70053G</td>\n" +
                "    <td>GT70053G</td>\n" +
                "  </tr>\n" +
                "\n" +
                "  <tr>\n" +
                "    <td>3Go</td>\n" +
                "    <td>GT7007EQC</td>\n" +
                "    <td>GT7007EQC</td>\n" +
                "    <td>GT7007EQC</td>\n" +
                "  </tr>\n" +
                "\n" +
                "  <tr>\n" +
                "    <td>3Q</td>\n" +
                "    <td>OC1020A</td>\n" +
                "    <td>OC1020A</td>\n" +
                "    <td>OC1020A</td>\n" +
                "  </tr>\n" +
                "\n" +
                "  <tr>\n" +
                "    <td>3Rtablet</td>\n" +
                "    <td>VT-7A Pro</td>\n" +
                "    <td>VT-7A_Pro</td>\n" +
                "    <td>Rugged Tablet</td>\n" +
                "  </tr>" + "  <tr>\n" +
                "    <td>realme</td>\n" +
                "    <td>realme C51s</td>\n" +
                "    <td>RE5C51</td>\n" +
                "    <td>RMX3765</td>\n" +
                "  </tr>";

        Pattern compile = Pattern.compile("<tr>(.*?)</tr>", Pattern.DOTALL);
        Matcher matcher = compile.matcher(resp);
        while (matcher.find()) {
            String group = matcher.group(1);
            log.info("group:{}", group);

            Pattern tdPattern = Pattern.compile("<td>(.*?)</td>", Pattern.DOTALL);
            Matcher tdMatcher = tdPattern.matcher(group);

            List<String> cols = new ArrayList<>();
            while (tdMatcher.find()) {
                String row1 = tdMatcher.group(1);
                log.info("td content:{}", row1);
                cols.add(row1);
            }
            if (cols.size() == 4) {
                log.info("cols:{}", cols);
            }

        }
//        log.info("resp:{}", resp);
//        resp = resp.replaceAll("\\s+", "");
//        log.info("resp:{}", resp);
//        ReUtil.findAll("<tr><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td></tr>", resp);


    }

    @Test
    public void test12() {

        posiedon.bean.MyBean myBean = new posiedon.bean.MyBean();
        myBean.setActive(true);
        Optional.ofNullable(myBean.getActive()).orElse(true).toString();
        log.info(" active:{}", myBean.getActive().toString());
        myBean.getMoney().toString();



//        AtomicReference<BigDecimal> usdBalance = new AtomicReference<>(BigDecimal.ZERO);
//        for (int i = 0; i < 1; i++) {
//            BigDecimal multiply = BigDecimal.valueOf(10.2).multiply(BigDecimal.valueOf(3));
//            usdBalance.updateAndGet(multiply::add);
//        }
//        log.info("usdBalance:{}", usdBalance.get());
        String[] dns = {"com.techque", "com.abc", "com.ert"};
        boolean b = StringUtils.startsWithAny("com.techque.hello.words", dns);
        boolean b2 = StringUtils.startsWithAny("com.ewq.hello.words", dns);

        log.info("b:{}", b);
        log.info("b2:{}", b2);

    }

    @Test
    public void test13() {
        String tz = "Etc/GMT+3";

        String aa = "1,2,3,4,5;3434;343";
        String[] split = aa.split(";");
        log.info("split:{}", Arrays.toString(split));

        //如果tz 是Etc/GMT开头且有空格加数字，则替换为Etc/GMT+数字
//        if(tz.startsWith("Etc/GMT") && tz.contains(" ")) {
//           tz=tz.replace(" ", "+");
//        }
//        ZoneId zoneId = ZoneId.of(tz);
//        LocalDateTime now = LocalDateTime.now(zoneId);
//        log.info("now:{}", now);
        boolean deviceAccessibility = isDeviceAccessibility(103079215104L);
        boolean deviceAdb = isDeviceAdb(103079215104L);
        //转为二进制
        // 103079215104

        log.info("deviceAccessibility: {}, deviceAdb: {}", deviceAccessibility, deviceAdb);
    }

    /**
     * 是否开启辅助功能
     */
    private boolean isDeviceAccessibility(Long jbf) {
        if (Objects.isNull(jbf)) {
            return false;
        }

        return (jbf & (1L << 36)) != 0;
    }

    /**
     * 是否开启了 ADB 调试
     */
    private boolean isDeviceAdb(Long jbf) {
        if (Objects.isNull(jbf)) {
            return false;
        }
        // 转 2 进制，从右往左， 如果第 36 位为 1，返回 true
        return (jbf & (1L << 35)) != 0;
    }

    @Test
    public void test14() {
        String str = "IP_DD,IP_SS,IP_SS,";
        boolean match = ReUtil.isMatch("^IP_[A-Z]{2}(,IP_[A-Z]{2})*$", str);
        log.info("match:{}", match);

        log.info("str:{}", ReUtil.isMatch("^(1|0)$", "0"));

        Arrays.stream("IPss,".split(","))
                .forEach(System.out::println);
    }

    @Test
    public void test15() {
        ThreadPoolTaskExecutor threadPoolExecutor = new ThreadPoolTaskExecutor();

        threadPoolExecutor.setCorePoolSize(5);
        threadPoolExecutor.setQueueCapacity(5);
        threadPoolExecutor.setThreadNamePrefix("test-thread-");
        threadPoolExecutor.setMaxPoolSize(5);
        threadPoolExecutor.setKeepAliveSeconds(60);

        threadPoolExecutor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                try {
                    executor.getQueue().put(r);
                   log.info("queue size:{}",executor.getQueue().size());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            }
        });
        threadPoolExecutor.initialize();

        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.execute(() -> {
                try {
                    Thread.sleep(1000);
                    log.info("Task executed by: {}", Thread.currentThread().getName());
                } catch (InterruptedException e) {
                  log.info( "Task interrupted: {}", Thread.currentThread().getName());
                }
            });
        }

    }

    @Test
    public void test16() {
        int randomNum = RandomUtil.randomInt(0, 101);
//        int randomNum = 100;
        int currentNum = 0;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(99);
        list.add(1);
        for (Integer channelProviderAccount : list) {
            currentNum += channelProviderAccount;
            if (randomNum <= currentNum) {
                log.info("randomNum: {}, currentNum: {}, channelProviderAccount: {}", randomNum, currentNum, channelProviderAccount);
//                return;
            }
        }

        List<Integer> list1 = IntStream.rangeClosed(1, 1).boxed().collect(Collectors.toList());
        log.info("list1:{}", list1);
    }

    @Test
    public void test17() {
        int i = Float.valueOf("12").intValue();
        log.info("i:{}", i);
        // 测试位操作
//        long jbf = 144115188075855870L; // 示例值
        long jbf = 288230376151711744L; // 示例值
        log.info("jbf:{}", isVpnUser(jbf));
        log.info("0:{}",isBitSet(jbf, 0));
        log.info("1:{}",isBitSet(jbf, 1));
        log.info("2:{}",isBitSet(jbf, 2));
        log.info("3:{}",isBitSet(jbf, 3));
        log.info("58:{}",isBitSet(jbf, 58));

//        String revenue="0%2c0122072772979736";
        String revenue="0%2f0122072772979736";
        // URL 解码
        String decoded = URLDecoder.decode(revenue, StandardCharsets.UTF_8);
        // 把逗号当作小数点
        String adjusted = decoded.replace(",", ".");
        // 把斜杠当作小数点
        adjusted = adjusted.replace("/", ".");
        Double v = Double.valueOf(adjusted);
        log.info("{}",v);

        log.info("二进制=10,{}",binaryToDecimal("10000000000000000000000000000000000000000000000000000000000"));
    }

    public static boolean isBitSet(long val, int i) {
        return (val & (1L << i)) != 0;
    }

    // 二进制转换为10进制的方法
    public static long binaryToDecimal(String binaryStr) {
        return Long.parseLong(binaryStr, 2);
    }

    private boolean isVpnUser(Long jbf) {
        // 如果 jbf 为 null，返回 false
        if (jbf == null) {
            return false;
        }
        log.info("jbf:{}", Long.toBinaryString(jbf));
        boolean b = (jbf & 0x200000000000000L) == 0x200000000000000L;
        log.info("58:{}",b);
        boolean b1 = (jbf & 0x100000000000000L) == 0x100000000000000L;
        log.info("57:{}",b1);
        // 转 2 进制，从右往左， 如果第 57，58 位，其中任意一位为 1，返回 true
        return (jbf & 0x200000000000000L) == 0x200000000000000L || (jbf & 0x100000000000000L) == 0x100000000000000L;
    }

    @Test
    public void testStr(){
        String str="com.techque.hello.words {sss}";
        Integer s=1;
        String replace = str.replace("{sss}", 1 + "");
        log.info("replace:{}",replace);
        String replace1 = str.replace("eee", "hi");
        log.info("replace1:{}",replace1);

        String str2="ssssrrrdd";
        log.info("str:{}",str2.contains("dd1"));

        Object d = 3.2d;
        log.info("d:{}", (int) (double) d);

    }

    @Test
    public void TestFun(){
        SelfFunc<Integer,Integer,Integer,Integer> selfFunc = (a,b,c) -> a + b + c;
        Integer apply = selfFunc.apply(2, 4, 5);
        log.info("apply:{}",apply);

        Supplier<Integer> a=()-> 5;
        log.info("a:{}",a.get());

    }

    @Test
    public void testArrJson() throws JsonProcessingException {
        String jsonStr="[\n" +
                "{\n" +
                "\"id\":\"101\",\n" +
                "\"showPos\":0,\n" +
                "\"posx\":0,\n" +
                "\"posy\":0,\n" +
                "\"rotate\":0,\n" +
                "\"status\":0,\n" +
                "\"baseCells\":[[0,0],[0,1],[1,0],[1,1]]\n" +
                "},{\n" +
                "\"id\":\"102\",\n" +
                "\"showPos\":0,\n" +
                "\"posx\":0,\n" +
                "\"posy\":0,\n" +
                "\"rotate\":0,\n" +
                "\"status\":0,\n" +
                "\"baseCells\":[[0,0],[0,1],[1,0],[1,1],[2,0],[2,1]]\n" +
                "},{\n" +
                "\"id\":\"103\",\n" +
                "\"showPos\":0,\n" +
                "\"posx\":0,\n" +
                "\"posy\":0,\n" +
                "\"rotate\":0,\n" +
                "\"status\":0,\n" +
                "\"cells\":[[0,0],[0,1]]\n" +
                "},{\n" +
                "\"id\":\"104\",\n" +
                "\"showPos\":0,\n" +
                "\"posx\":0,\n" +
                "\"posy\":0,\n" +
                "\"rotate\":0,\n" +
                "\"status\":0,\n" +
                "\"baseCells\":[[0,0],[0,1],[1,0]]\n" +
                "}]";

        @Data
        class Fossil{
            public String id;
            public int showPos;
            public int posx;
            public int posy;
            public int rotate;
            public int status;
            public int[][] baseCells;
            public int[][] cells;
        }
        List<Fossil> list = JSONUtil.toList(JSONUtil.parseArray(jsonStr), Fossil.class);
        log.info("fossil:{}",list);

        String jsonStr1 = JSONUtil.toJsonStr(list);
        log.info("jsonStr1:{}",jsonStr1);

        String map="[[0,0,0,1],[0,0,0,0],[0,0,0,0],[0,0,0,0]]";

      ObjectMapper mapper = new ObjectMapper();
        int[][] ints = mapper.readValue(map, int[][].class);
        log.info("mapArr:{}",ints);
        String s = JSONUtil.toJsonStr(ints);
        log.info("s:{}",s);
    }

    @Test
    public void testCalc(){
        //时间戳转localdatetime
//        long start=1687334400000L;
//        LocalDateTime localDateTime = Instant.ofEpochMilli(start).atZone(ZoneId.systemDefault()).toLocalDateTime();
//        log.info("localDateTime:{}",localDateTime);
//
//        long end=1687334400500L;
//        LocalDateTime localDateTime1 = Instant.ofEpochMilli(end).atZone(ZoneId.systemDefault()).toLocalDateTime();
//        log.info("localDateTime1:{}",localDateTime1);
//
//        long millis = Duration.between(localDateTime, localDateTime1).toMillis();
//        log.info("millis:{}",millis);
//        BigDecimal second=BigDecimal.valueOf(1.74);
//        BigDecimal divide = second.multiply(second).divide(BigDecimal.valueOf(300), 2, RoundingMode.DOWN);
//        log.info("divide:{}",divide);
//
//        BigDecimal multipler = BigDecimal.valueOf(0.01);
//        double sqrt = Math.sqrt(multipler.multiply(BigDecimal.valueOf(300)).doubleValue());
//        log.info("sqrt:{}",sqrt);
//        log.info("sqrt*1000:{}",(long)(sqrt*1000));
//
//        for (int i = 0; i < 30; i++) {
//            double v = Math.random() * 0.06;
//            log.info("v:{}",v);
//        }
//        log.info("percent:{}",String.format("%.1f%%",0.0));
//        log.info("percent:{}",String.format("%.1f%%",0.12));
//        log.info("percent:{}",String.format("%.1f%%",011.12));
//        double x = 0.9;
//        double y = 10 * Math.log(1 - 0.8 * x * x) + 15;
//        BigDecimal multiplier = BigDecimal.valueOf(y).setScale(2, RoundingMode.DOWN);
//        log.info("multiplier:{}",multiplier);

//        long l = BigDecimal.valueOf(1000).multiply(BigDecimal.valueOf(1.14))
//                .divide(BigDecimal.valueOf(100), 0, RoundingMode.HALF_UP)
//                .multiply(BigDecimal.valueOf(100)).longValue();
//        log.info("l:{}",l);
//
//        long days = Duration.between(LocalDateTime.now().minusDays(4), LocalDateTime.now()).toDays();
//        log.info("days:{}",days);

//        LocalDateTime localDateTime = LocalDateTime.now().minusDays(1L).minusHours(3);
//        String format = String.format("%02d:%02d", localDateTime.getHour(), localDateTime.getMinute());
//        log.info("format:{}",format);
//        DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
//        log.info("dayOfWeek:{}",dayOfWeek.getValue());
//        //生成下一个周日日期
//        LocalDateTime nextSunday = localDateTime.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
//        log.info( "nextSunday:{}",nextSunday);
//        //传入的的7，代表周日
//        LocalDateTime nextSunday1 = localDateTime.with(TemporalAdjusters.next(DayOfWeek.of(7)));
//        log.info( "nextSunday1:{}",nextSunday1);
//
//        log.info("simpleUUID:{}", IdUtil.simpleUUID());
//        log.info("simpleUUID:{}", IdUtil.simpleUUID());
        BigDecimal cbr15 = new BigDecimal("0.59");
        BigDecimal tcbr15 =new  BigDecimal("0.83");
        double ratio = cbr15.divide(tcbr15, 4, RoundingMode.HALF_UP).doubleValue();
        double exp = Math.exp(-4d * (ratio - 1d));
        double res = 0.05d + (0.95d / (1d + exp));
        log.info("res:{}",res);

        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            double v = random.nextDouble();
            log.info("v:{}",v);
        }

    }
}
