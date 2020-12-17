package com.chenyang.reactor;

import reactor.core.publisher.Flux;

/**
 * @Package: com.chenyang.reactor
 * @Author: chenyang
 * @Date: 2020/12/4
 * @Version: 1.0
 */
public class FluxHandleDemo {

    public static void main(String[] args) {
        test();
    }

    static void test(){
        Flux<String> flux = Flux.just(-1, 30, 13, 9, 20, 50).handle(((integer, synchronousSink) -> {
            String alphabet = alphabet(integer);
            if (alphabet != null) synchronousSink.next(alphabet);
        }));
        flux.subscribe(System.out::println);
    }

    static String alphabet(int i){
        if(i < 0 || i > 26){
            return null;
        }
        return (char)('A' + i - 1) + "";
    }
}
