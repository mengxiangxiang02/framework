package com.example.demo.javainaction;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.*;

public class StreamProcess {
    public static void main(String[] args) throws Exception {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH) );



        Map<Dish.Type, List<String>> dishNamesByType =
                menu.stream()
                        .collect(groupingBy(Dish::getType,
                                mapping(Dish::getName, toList())));
        System.out.println(dishNamesByType);

        Set<Map.Entry<Dish.Type, List<String>>> entries = dishNamesByType.entrySet();
        Map<Dish.Type, List<String>> collect = entries.stream().filter(entry -> Dish.Type.FISH.equals(entry.getKey())).collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println(collect);

    }
}
