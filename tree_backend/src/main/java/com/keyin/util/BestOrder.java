package com.keyin.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BestOrder {
    public static List<Integer> getBestOrder(List<Integer> numbers){
        Collections.sort(numbers);
        ArrayList<Integer> bestOrder = new ArrayList<>();
        if(numbers.size() <= 2){
            return numbers;
        } else {
            int median = numbers.size()/2;
            bestOrder.add(numbers.get(median));
            bestOrder.addAll(getBestOrder(numbers.subList(0,median)));
            bestOrder.addAll(getBestOrder(numbers.subList(median+1,numbers.size())));
            return bestOrder;
        }
    }
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);

        System.out.println(list);

        List<Integer> bestList = getBestOrder(list);

        System.out.println(bestList);
    }
}
