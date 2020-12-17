package com.chenyang.rxjava;



import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import io.reactivex.rxjava3.observables.GroupedObservable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @Package: com.chenyang.rxjava
 * @Author: chenyang
 * @Date: 2020/12/13
 * @Version: 1.0
 */
public class Launcher {

    public static void main(String[] args) {


//        single1();
    connectSingle3();
    }

    static void single1() {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
        Observable<GroupedObservable<Integer, String>> byLengths = source.groupBy(s -> s.length());
        byLengths.flatMapSingle(grp -> grp.toList()).subscribe(System.out::println);

        System.out.println("seperate line ----------------------");
        byLengths.flatMapSingle(grp -> grp.reduce("", (x, y) -> "".equals(x) ? y : x + " ," + y).map(s -> grp.getKey() + " :" + s)).subscribe(System.out::println);
    }

    static void connectSingle2() {
        Observable<String> source =
                Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
        ConnectableObservable<GroupedObservable<Integer, String>> byLengths = source.groupBy(s -> s.length()).publish();
        byLengths.flatMapSingle(grp -> grp.reduce("。", (x, y) -> "".equals(x) ? y : x + " ," + y).map(s -> grp.getKey() + " :" + s)).subscribe(System.out::println,e -> System.out.println(e.getMessage()));
        byLengths.flatMapSingle(grp -> grp.toList()).subscribe(System.out::println);

        byLengths.connect();
    }

    static void connectSingle3() {
        long begin = System.currentTimeMillis();
        Observable<String> source = Observable.fromIterable(generateList());

        System.out.println(System.currentTimeMillis() - begin);

    }


    static List<String> generateList(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Arrays.asList("a","c","s");
    }

//    static Future<String> generateFuture(){
//
//    }


}
