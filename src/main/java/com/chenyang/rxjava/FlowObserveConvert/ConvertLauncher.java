package com.chenyang.rxjava.FlowObserveConvert;

import com.chenyang.rxjava.parallelization.Launcher;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.Arrays;
import java.util.List;

/**
 * @Package: com.chenyang.rxjava.FlowObserveConvert
 * @Author: chenyang
 * @Date: 2020/12/17
 * @Version: 1.0
 */
public class ConvertLauncher {

    public static void main(String[] args) {

//        convert();
        equalsList();
    }


    private static void convert() {

        @NonNull Flowable<Integer> flowable = Flowable.range(1, 1000).subscribeOn(Schedulers.computation());

        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
            .flatMap(s -> flowable.map(i -> s + "-" + i).toObservable())
            .subscribe(System.out::println);

        Launcher.sleep(1000);
}

    private static void equalsList(){
        List<String> strings = Arrays.asList("aaa", "bbb");
        List<String> string2 = Arrays.asList("aaa", "bbb");

        System.out.println(string2 == strings);
    }
}
