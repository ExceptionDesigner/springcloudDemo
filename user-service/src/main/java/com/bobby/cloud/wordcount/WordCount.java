package com.bobby.cloud.wordcount;

import java.util.*;

/**
 * @author: Bobby
 * @create: 2022-03-18 15:12
 * @description:
 **/
public class WordCount {

    public static void main(String[] args) {
        String string = "His creator has said that he has always conceived and thought of the life of his hero and of the book as a river So far as the book has a plan that is its plan";

        HashMap map = new HashMap<String, Integer>();
        String[] strArr = string.split(" ");
        if (strArr.length > 0) {
            Arrays.stream(strArr).forEach(s -> {
                if (map.containsKey(s)) {
                    Integer count = (Integer) map.get(s);
                    map.put(s, count.intValue() + 1);
                } else {
                    map.put(s, 1);
                }
            });
        }

        ArrayList<Map.Entry<String, Integer>> entries = sortMap(map);
        for (int i = 0; i < entries.size(); i++) {
            System.out.println(entries.get(i).getKey() + ":" + entries.get(i).getValue());
        }
    }


    public static ArrayList<Map.Entry<String, Integer>> sortMap(Map map) {
        List<Map.Entry<String, Integer>> entries = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> obj1, Map.Entry<String, Integer> obj2) {
                return obj2.getValue() - obj1.getValue();
            }
        });
        return (ArrayList<Map.Entry<String, Integer>>) entries;
    }
}