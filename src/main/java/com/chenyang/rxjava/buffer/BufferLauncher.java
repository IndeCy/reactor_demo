package com.chenyang.rxjava.buffer;

import com.chenyang.rxjava.parallelization.Launcher;
import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

public class BufferLauncher {

    public static void main(String[] args) {
        window();

    }

    public static void normalBuffer(){

        //如果skip < count 则每次跳过上一次buffer的skip个元素并从下一个元素开始往当前buffer里排
        //如果skip > count 则每次跳过分组的第skip个元素
        Observable.range(1,10)
                .buffer(4,6)
                .subscribe(System.out::println);
    }

    public static void timeBasedBuffer(){

        Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(i -> (i+1) * 300)
                .buffer(Observable.interval(1,TimeUnit.SECONDS))
                .subscribe(System.out::println);

        Launcher.sleep(3000);
    }

    public static void window(){

        Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(i -> (i+1) * 300)
//                .doOnNext(System.out::println)
                .window(Observable.interval(1,TimeUnit.SECONDS))
                .flatMapSingle(ele -> ele.reduce(1L,(total,next) -> {
                    System.out.println("total:" + total +" ,next:" + next);
                    return total + next;
                }))
                .subscribe(System.out::println);

        Launcher.sleep(3000);
    }
}
