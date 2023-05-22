package com.example.demo.javainaction;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TraderTransactionProcess {
    public static void main(String[] args) throws Exception {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        Stream<Transaction> sorted = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(transaction -> transaction.getValue()));
        System.out.println(sorted.collect(Collectors.toList()));

        Stream<String> distinct = transactions.stream().map(transaction -> transaction.getTrader()).distinct()
                .map(trader -> trader.getCity()).distinct();
        System.out.println(distinct.collect(Collectors.toList()));

        Stream<Trader> cambridge = transactions.stream().map(transaction -> transaction.getTrader()).filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName));
        System.out.println(cambridge.collect(Collectors.toList()));

        String reduce = transactions.stream().map(transaction -> transaction.getTrader().getName()).distinct()
                .sorted().reduce("", (a, b) -> a + b);
        System.out.println(reduce);

        boolean milan = transactions.stream().anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        transactions.stream().filter(transaction -> transaction.getTrader().getCity().equals("Cambridge")).map(transaction -> transaction.getValue())
                        .forEach(System.out::println);


        Optional<Integer> reduce1 = transactions.stream().map(transaction -> transaction.getValue()).reduce((a, b) -> a > b ? a : b);

        System.out.println(reduce1.get());

        Optional<Transaction> min = transactions.stream().min(Comparator.comparing(Transaction::getValue));

        System.out.println(min.get());

        OptionalInt max = transactions.stream().mapToInt(transaction -> transaction.getValue()).max();

        System.out.println(max.getAsInt());

        String password="zse2_9jPle";

    }
}
