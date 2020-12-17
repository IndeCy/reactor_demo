package com.chenyang.reactor;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.LongConsumer;

public class FluxCreateDemo {

    static MyEventListenerProcessor<String> processor = new MyEventListenerProcessor<>();

    public static void main(String[] args) throws InterruptedException {
        test();
    }

    static void test() throws InterruptedException {
        List<String> strings = Arrays.asList("a", "b", "c", "d");
        Flux<String> flux = Flux.create(sink -> processor.register(new StringListListener<String>(strings) {

            @Override
            public void onDataChunk(List<String> chunk) {
                chunk = strings;
                for (String s : chunk) {
                    sink.next(s);
                }
            }

            @Override
            public void processComplete() {
                sink.complete();
            }
        }));
        flux.subscribe(System.out::println);
        Thread.sleep(5000);
    }

    static void test2(){
        Mono<String> mono = Mono.create(sink -> {
            sink.onRequest(System.out::println);
            sink.success("success");
        });
        mono.subscribe(new SubscriberTest2<>());
    }
}
