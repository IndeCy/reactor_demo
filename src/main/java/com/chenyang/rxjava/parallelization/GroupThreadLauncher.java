package com.chenyang.rxjava.parallelization;


import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

public class GroupThreadLauncher {

    public static void main(String[] args) {

        int cores= Runtime.getRuntime().availableProcessors();
        AtomicInteger assigner = new AtomicInteger(0);
        Observable.range(1,10)
                .groupBy(i -> i %  cores)
                .flatMap(grp -> grp.observeOn(Schedulers.io())
                .map(Launcher::intenseCalculation)
                )
                .subscribe(i -> System.out.println("Received " + i + " " + LocalTime.now() + " on thread " + Thread.currentThread().getName()));

        Launcher.sleep(20000);

    }
}
