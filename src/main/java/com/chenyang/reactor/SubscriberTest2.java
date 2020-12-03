package com.chenyang.reactor;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

/**
 * @Package: com.chenyang.reactor
 * @Author: chenyang
 * @Date: 2020/12/3
 * @Version: 1.0
 */
public class SubscriberTest2<T> extends BaseSubscriber<T> {

    @Override
    protected void hookOnSubscribe(Subscription subscription) {
        System.out.println("hookOnSubscribe");
        request(10);
    }

    @Override
    protected void hookOnNext(T value) {
        System.out.println(value);
        request(10);
    }

    @Override
    protected void hookOnError(Throwable throwable) {
        System.out.println(throwable.getMessage());
    }

}
