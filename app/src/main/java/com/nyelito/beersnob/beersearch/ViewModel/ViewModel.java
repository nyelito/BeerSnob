package com.nyelito.beersnob.beersearch.ViewModel;

/**
 * Created by User on 7/9/2017.
 */

/**
 * Interface that every ViewModel must implement
 */
public interface ViewModel {

    void destroy();

    public void setSearchText(String searchText);
}