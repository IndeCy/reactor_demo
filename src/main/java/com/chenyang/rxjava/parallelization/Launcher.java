package com.chenyang.rxjava.parallelization;


import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.time.LocalTime;
import java.util.concurrent.ThreadLocalRandom;

public class Launcher {


    public static void main(String[] args) {

//        singleThread();

        parallel2();
    }

    public static void singleThread(){
        Observable.range(1,10)
                .map(Launcher::intenseCalculation)
                .subscribe(i -> System.out.println("received " + i + " "+ LocalTime.now()));
    }

    //这样写没有达到并行的效果 还是只使用了一个线程来emit
    public static void parallel1(){
        Observable.range(1,10)
                .subscribeOn(Schedulers.computation())
                .map(Launcher::intenseCalculation)
                .subscribe(i -> System.out.println("Received " + i + " " + LocalTime.now() + " on thread " + Thread.currentThread().getName()));
        sleep(20000);
    }
    //真正实现了并行的写法
    public static void parallel2(){
        Observable.range(1,10)
                //每个元素都开启一个Observable会导致内存占用过高
                .flatMap(i -> Observable.just(i)
                        .subscribeOn(Schedulers.computation())
                        .map(Launcher::intenseCalculation))
                //fixme 为什么用blockingSubscribe就全是打印的主线程？
                .subscribe(i -> System.out.println("Received " + i + " " + LocalTime.now() + " on thread " + Thread.currentThread().getName()));
        sleep(20000);
    }

    public static <T> T intenseCalculation(T value){
        sleep(ThreadLocalRandom.current().nextInt(3000));
        return value;
    }

    public static void sleep(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
