package com.chenyang.rxjava.flow;


import com.chenyang.rxjava.parallelization.Launcher;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @Package: com.chenyang.rxjava.flow
 * @Author: chenyang
 * @Date: 2020/12/17
 * @Version: 1.0
 */
public class CreateFlowable {

    public static void main(String[] args) {
//        createObservable();

        createFlowable();
    }

    private static void createObservable(){

        @NonNull Observable<Object> source = Observable.create(emitter -> {

            for (int i = 0; i < 1000; i++) {
                if (emitter.isDisposed()) {
                    return;
                }
                emitter.onNext(i);
            }
            emitter.onComplete();
        });
        @NonNull Disposable subscribe = source.observeOn(Schedulers.computation())
                .subscribe(i -> System.out.println("i: " + i + " Thread: " + Thread.currentThread().getName()));
        Launcher.sleep(2);
        subscribe.dispose();
        Launcher.sleep(1000);
    }

    private static void createFlowable(){
        @NonNull Flowable<Object> source = Flowable.create(emitter -> {

            for (int i = 0; i < 1000; i++) {
                if (emitter.isCancelled()) {
                    return;
                }
                emitter.onNext(i);
            }
            emitter.onComplete();
        }, BackpressureStrategy.BUFFER);
        @NonNull Disposable subscribe = source.observeOn(Schedulers.computation())
                .subscribe(i -> System.out.println("i: " + i + " Thread: " + Thread.currentThread().getName()));
        Launcher.sleep(2);
        subscribe.dispose();
        Launcher.sleep(1000);
    }
}
