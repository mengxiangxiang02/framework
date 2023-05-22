package com.example.demo.javainaction;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class DateProcess {
    public static void  main(String args[])
    {
        LocalDate today = LocalDate.now();
        System.out.println(today.getDayOfWeek());
        System.out.println(today.getMonth());
        System.out.println(today.getDayOfMonth());
        System.out.println(today.getDayOfYear());

        LocalDate localDate = LocalDate.of(2023, 2, 3);
        System.out.println(localDate);

        int i = today.get(ChronoField.DAY_OF_WEEK);
        System.out.println(i);

        LocalTime now = LocalTime.now();
        System.out.println(now.getHour());
        System.out.println(now.getMinute());
        System.out.println(now.getSecond());
        System.out.println(now.getNano());

        LocalTime defineTime=LocalTime.of(11,24,00);
        Duration between = Duration.between(defineTime, now);
        System.out.println(between.getSeconds());

        LocalDateTime localDateTime1 = LocalDateTime.now();
        System.out.println(localDateTime1);
        Instant day = Instant.now();
        Instant instant = Instant.ofEpochSecond(20);
        System.out.println(day.getEpochSecond());
        System.out.println(day.getNano());
        System.out.println(instant.getEpochSecond());

        today.with(ChronoField.DAY_OF_WEEK,2);
        today.plus(6, ChronoUnit.DAYS);
        LocalDateTime localDateTime = LocalDateTime.now();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = localDateTime.format(dateTimeFormatter);
        System.out.println(format);
        LocalDateTime parse = LocalDateTime.parse(format,dateTimeFormatter);
        System.out.println(parse);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = LocalDate.now().format(formatter);
        System.out.println(date);

    }
}
