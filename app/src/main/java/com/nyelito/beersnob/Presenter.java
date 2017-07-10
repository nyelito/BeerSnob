package com.nyelito.beersnob;

public interface Presenter<V> {

    void attachView(V view);

    void detachView();
}
