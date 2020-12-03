package com.chenyang.reactor;

import reactor.core.publisher.Flux;

import java.util.List;

public class FluxCreateDemo {

    static MyEventListenerProcessor<String> processor = new MyEventListenerProcessor<>();

    public static void main(String[] args) {
        test();
    }

    static void test() {
        Flux<String> flux = Flux.create(sink -> processor.register(new MyEventListener<String>() {
            @Override
            public void onDataChunk(List<String> chunk) {
                for (String s : chunk) {
                    sink.next(s);
                }
            }

            @Override
            public void processComplete() {
                sink.complete();
            }
        }));
        flux.subscribe();
    }
}
