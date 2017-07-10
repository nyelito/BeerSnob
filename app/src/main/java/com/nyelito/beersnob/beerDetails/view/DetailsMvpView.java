package com.nyelito.beersnob.beerDetails.view;

import com.nyelito.beersnob.MvpView;
import com.nyelito.beersnob.beersearch.model.Beer;

import java.util.List;

public interface DetailsMvpView extends MvpView {

    void showBeerDetails(Beer theBeer);

}