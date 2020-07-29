package demo.controller;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

public class BloomFilterController {

    private static BloomFilter<Integer> bloomFilter=BloomFilter.create(Funnels.integerFunnel(),1000);

//    public static void main(String[] args){
//        for (int i = 0; i < 1000; i++) {
//            bloomFilter.put(i);
//        }
//
//        int j=1;
//        for (int i = 0; i < 1100; i++) {
//            if(!bloomFilter.mightContain(i)){
//                System.out.println("wrong"+j);
//                j++;
//            }
//        }
//    }
}
