package com.example.demo.javainaction;

import java.util.*;
import java.util.function.*;

public class lambda {
    public static void main(String[] args) {

        Predicate<String> preiStringPredicate = (String s) -> s.isEmpty();

        List<String> listOriginal = new ArrayList<>();
        Class<? extends List> aClass = listOriginal.getClass();

        listOriginal.add("1");
        filter(listOriginal, preiStringPredicate);
        listOriginal.removeIf((String e) -> e.isEmpty());
        Consumer<String> consumer = (String con) -> {
            System.out.println("test");
        };
        consumerTest("test", consumer);

        Function<String, Integer> function = (String origin) -> origin.length();
        functionlist(listOriginal, function);
        listOriginal.add("2");
        final int port = 120;
        Runnable runnable = () -> System.out.println(port);
        runnable.run();

        String[] a = {"1", "2", "3"};
        Arrays.sort(a, (String x, String b) -> x.compareToIgnoreCase(b));
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        };
        BinaryOperator.maxBy(comparator);
        UnaryOperator unaryOperator;


    }

    public static <T> List<T> filter(java.util.List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }


    public static <R, T> List<R> functionlist(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(f.apply(t));
        }
        return result;
    }


    public static void consumerTest(String test, Consumer<String> consumer) {
        consumer.accept(test);
    }


}
