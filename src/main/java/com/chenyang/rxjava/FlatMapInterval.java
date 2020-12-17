package com.chenyang.rxjava;



import io.reactivex.rxjava3.core.Observable;
import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * @Package: com.chenyang.rxjava
 * @Author: chenyang
 * @Date: 2020/12/13
 * @Version: 1.0
 */
public class FlatMapInterval {

    @SneakyThrows
    public static void main(String[] args) {

//        Observable<Integer> intervalArguments = Observable.just(2, 3, 10, 7);
//        intervalArguments.flatMap(i -> Observable.interval(i, TimeUnit.SECONDS)
//                .map(i2 -> i + "s interval: " + ((i + 1) * i) + " seconds elapsed")
//        ).subscribe(System.out::println);

        Observable<Integer> just = Observable.just(2, 3, 10, 7);
        just.flatMap(i -> Observable.interval(i, TimeUnit.SECONDS)
                .map(i2 -> i + "s interval: " + ((i2 + 1) * i) + " seconds elapsed: " + (i2+1) + " times")
        ).subscribe(System.out::println);

        Thread.sleep(15000);

    }

}
