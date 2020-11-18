package posiedon.think.listtest;

import org.junit.Test;

import java.util.*;

/**
 * @Author: Posiedon.wh
 * @Date: 2020/10/3 10:59
 */
public class ArrayListTest {

    @Test
    public void test1(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        List<Integer> subList = list.subList(1, 3);
        // subList不能强转ArrayList，会报异常ClassCastException，subList是ArrayList的一个内部子类
//        ArrayList subList1 = (ArrayList) subList;

        // 对sublist的修改会反映在原来的ArrayList中
        list.forEach(System.out::println);
        System.out.println("sublist:");
        subList.forEach(System.out::println);
        System.out.println("============");
        subList.remove(0);
        list.forEach(System.out::println);
        System.out.println("sublist");
        subList.forEach(System.out::println);

        // 修改原集合,报异常ConcurrentModificationException
//        list.add(2,9);
//
//        list.forEach(System.out::println);
//        System.out.println("sublist");
//        subList.forEach(System.out::println);
    }

    @Test
    public void test2(){
        Map<String, Integer> map = new HashMap<>();
        map.put("k1",1);
        map.put("k2",2);
        map.put("k3",3);
        map.put(null,4);

        map.forEach((k,v)->{
            System.out.println(k);
            System.out.println(v);
        });

        Set<String> keys = map.keySet();
        // 删除操作正常
//        keys.remove("k3");
        // 添加操作报异常
//        keys.add("key4");
        System.out.println("keys:");
        keys.forEach(System.out::println);

        Collection<Integer> values = map.values();
        System.out.println("values:");
        values.forEach(System.out::println);

        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        System.out.println("entrySet:");
        entries.forEach((it)->{
            System.out.println("key="+it.getKey());
            System.out.println("value="+it.getValue());
        });
    }

    @Test
    public void tes3(){
        ArrayList<String> list = new ArrayList<>();
        list.add("2");
        list.add("2");
        list.add("2");
        // 传入参数size=0，动态创建于list大小相等的数组，性能最好
        // size<list,重新创建一样大小的数组，增加GC
        // size=list,高并发情况下，发生扩容，和上一个类似
        // size>list,空间浪费，填充null，存在NPE
        String[] array = list.toArray(new String[0]);
        for (String s : array) {
            System.out.println(s);
        }
    }

    @Test
    public void test4(){
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
//        list.removeIf("2"::equals);

        // 在循环删除时，当删除元素是最后一个是会报异常ConcurrentModificationException
        // 建议使用迭代器，或removeIf
        for (String s : list) {
            if("3".equals(s)){
                list.remove(s);
            }
        }

        list.forEach(System.out::println);
    }

    @Test
    public void test5(){
        //hashtable key和value都不能为null,线程安全
        /*Hashtable<String, String> hashtable = new Hashtable<>();
        hashtable.put(null,"1");
        hashtable.put("1",null);*/

        // ConcurrentHashMap key和value都不能为null,线程安全
        /*ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put(null,"1");
        concurrentHashMap.put("1",null);*/

        // treeMap key不能为null,value可以为null,线程不安全
        TreeMap<String, String> treeMap = new TreeMap<>();
//        treeMap.put(null,"1");//key不能为null
        treeMap.put("1",null);

    }
}
