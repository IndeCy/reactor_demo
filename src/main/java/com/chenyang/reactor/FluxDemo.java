package com.chenyang.reactor;

import reactor.core.publisher.Flux;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @Package: com.chenyang.reactor
 * @Author: chenyang
 * @Date: 2020/12/3
 * @Version: 1.0
 */
public class FluxDemo {

    public static void main(String[] args) {
        test2();
    }

    static void test() {
        Flux<String> just = Flux.just("a", "b", "c", "d", "e", "f");
        just.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
    }

    static void test2(){
        SubscriberTest2<Integer> ss = new SubscriberTest2<>();
        Flux<Integer> range = Flux.range(1, 10);
        Flux<Integer> map = range.map(i -> {
            if (i <= 5) return i;
            throw new RuntimeException("larger than 5 exception");
        });
        map.subscribe(System.out::println,
                error -> System.out.println(error.getMessage()),
                () -> System.out.println("success complete"),
                s -> ss.requestUnbounded());
        map.subscribe(ss);
    }
}
