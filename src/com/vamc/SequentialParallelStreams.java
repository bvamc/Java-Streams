package com.vamc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class SequentialParallelStreams {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println("Input List = " + list);

        List serialList = new ArrayList();
        Stream<Integer> serialStream = list.stream();
        serialStream.filter(item -> item %2==0).forEach(item -> {
            System.out.println("Thread = " + Thread.currentThread().getName());
            serialList.add(item);
        });
        System.out.println("Serial Stream = " + serialList);


        List parallelList = new ArrayList();
        Stream<Integer> parallelStream = list.parallelStream();
        parallelStream.filter(item -> item %2==0).forEach(item -> {
            System.out.println("Thread = " + Thread.currentThread().getName());
            parallelList.add(item);
        });

        System.out.println("Parallel Stream = " + parallelList);

        //BaseStream.isParallel() is used to check if a terminal operation were to be executed, would execute in parallel?
        System.out.println("is serialStream Parallel: " + serialStream.isParallel());
        System.out.println("is parallelStream Parallel: " + parallelStream.isParallel());
    }
}
