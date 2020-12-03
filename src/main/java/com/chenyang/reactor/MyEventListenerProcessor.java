package com.chenyang.reactor;

import java.util.ArrayList;
import java.util.List;

public class MyEventListenerProcessor<T> {

    private final List<MyEventListener<T>> listeners = new ArrayList<>();

    public void register(MyEventListener<T> myEventListener){
        listeners.add(myEventListener);
    }
}
