package com.gouin.mediscreen.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DateTraitement {

//    public int calculeAge(String dateString) {
//        String[] split = dateString.split("-");
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
//        List<Integer> collect = Arrays.stream(split).map(x -> Integer.parseInt(x)).collect(Collectors.toList());
//
//        LocalDate ld = LocalDate.of(collect.get(0), collect.get(1), collect.get(2));
//        System.out.println("ld: " + ld);
//        LocalDate today = LocalDate.now();
//        System.out.println(today);
//        Period age2 = Period.between(ld, today);
//        System.out.println(age2);
//        int age = Period.between(ld, today).getYears();
//        return age;
//    }
//
//    public static void main(String[] args) {
//
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
//        LocalDate birthdate = LocalDate.of(1991, 02, 14);
//        LocalDate today = LocalDate.now();
//        System.out.println(birthdate);
//        System.out.println(birthdate.format(dtf));
//        System.out.println(today);
//        Period between = Period.between(birthdate, today);
//        int age = Period.between(birthdate, today).getYears();
//        System.out.println(between);
//        System.out.println(between.getYears());
//        System.out.println(between.getMonths());
//       int result = new DateTraitement().calculeAge("1991-02-14");
//        System.out.println(result);
//    }
}
