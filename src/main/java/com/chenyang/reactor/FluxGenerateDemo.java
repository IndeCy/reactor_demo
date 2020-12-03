package com.chenyang.reactor;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

/**
 * @Package: com.chenyang.reactor
 * @Author: chenyang
 * @Date: 2020/12/3
 * @Version: 1.0
 */
public class FluxGenerateDemo {

    public static void main(String[] args) {
        test();
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
}
