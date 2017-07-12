package com.nyelito.beersnob.BeerSearch.View;

import com.nyelito.beersnob.MvpView;
import com.nyelito.beersnob.BeerSearch.Model.Beer;

import java.util.List;

public interface MainMvpView extends MvpView {

    void showSearchResults(List<Beer> results);


    void showProgressIndicator();
}