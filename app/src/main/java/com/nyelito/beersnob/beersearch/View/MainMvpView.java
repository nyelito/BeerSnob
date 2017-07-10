package com.nyelito.beersnob.beersearch.View;

import com.nyelito.beersnob.MvpView;
import com.nyelito.beersnob.beersearch.model.Beer;

import java.util.List;

public interface MainMvpView extends MvpView {

    void showSearchResults(List<Beer> results);

    void showMessage(int stringId);

    void showProgressIndicator();
}