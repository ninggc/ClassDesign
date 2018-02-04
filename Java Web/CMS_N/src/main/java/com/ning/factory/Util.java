package com.ning.factory;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Util implements IGson {
    /**
     * @param arr 从小到大排序
     * @return
     */
    public static String handleSerialNumber(List<Integer> arr) {
        Collections.sort(arr, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println(gson.toJson(arr));
        StringBuilder sb = new StringBuilder();

        sb.append(arr.get(0) + "-");
        for (int i = 1; i < arr.size(); i++) {
            if (!((arr.get(i) - 1) == arr.get(i - 1))) {
                sb.append(" ");
            }
            sb.append(arr.get(i) + "-");
        }

        return sb.toString();
    }

    public static void main(String[] args) {

    }
}
