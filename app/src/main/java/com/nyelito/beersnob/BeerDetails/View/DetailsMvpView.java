package com.nyelito.beersnob.BeerDetails.View;

import com.nyelito.beersnob.MvpView;
import com.nyelito.beersnob.BeerSearch.Model.Beer;

public interface DetailsMvpView extends MvpView {

    void showBeerDetails(Beer theBeer);

    void showToast(String toastText);

    void setLiked(boolean isLiked);

}