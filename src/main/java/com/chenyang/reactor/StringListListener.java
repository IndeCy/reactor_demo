package com.chenyang.reactor;

import java.util.Arrays;
import java.util.List;

/**
 * @Package: com.chenyang.reactor
 * @Author: chenyang
 * @Date: 2020/12/4
 * @Version: 1.0
 */
public class StringListListener<T> implements MyEventListener<T>{

    public StringListListener(List<T> chunk) {
        this.chunk = chunk;
    }

    List<T> chunk;

    @Override
    public void onDataChunk(List<T> chunk) {
        chunk = this.chunk;
    }

    @Override
    public void processComplete() {

    }
}
