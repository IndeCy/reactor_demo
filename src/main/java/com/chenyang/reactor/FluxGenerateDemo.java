package com.chenyang.reactor;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Package: com.chenyang.reactor
 * @Author: chenyang
 * @Date: 2020/12/3
 * @Version: 1.0
 */
public class FluxGenerateDemo {

    public static void main(String[] args) {
        test2();
    }

    static void test() {
        Flux<String> generate = Flux.generate(
                () -> 0,
                (state, sink) -> {
                    sink.next("3 x " + state + " = " + 3 * state);
                    if (state == 10) sink.complete();
                    return state + 1;
                },
                state -> System.out.println("final state: " + state));

        generate.subscribe(new BaseSubscriber<String>() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                System.out.println("subscribe");
                request(1);
            }

            @Override
            protected void hookOnNext(String value) {
                System.out.println(value);
                request(1);
            }
        });
    }

    static void test2(){
        Flux<String> flux = Flux.generate(AtomicInteger::new,
                (state, sink) -> {
                    int i = state.getAndIncrement();
                    sink.next("3 x " + i + " = " + 3 * i);
                    if (i == 10) sink.complete();
                    return state;
                }, state -> System.out.println("last state" + state.get()));
        flux.subscribe(new BaseSubscriber<String>() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                System.out.println("subscribe in ");
                request(1);
            }

            @Override
            protected void hookOnNext(String value) {
                System.out.println(value);
                request(1);
            }
        });
    }
}
