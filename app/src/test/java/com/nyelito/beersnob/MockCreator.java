package com.nyelito.beersnob;

import com.nyelito.beersnob.BeerSearch.Model.Beer;
import com.nyelito.beersnob.BeerSearch.Model.BeerSearchData;

import java.util.ArrayList;
import java.util.List;

public class MockCreator{
        public BeerSearchData newBeerSearchData(int n){

            List<Beer> list = newListOfBeers(n);

            BeerSearchData beerSearchData = new BeerSearchData();
            beerSearchData.setData(list);

            return beerSearchData;
        }
        private List<Beer> newListOfBeers(int n){
            List<Beer> beerList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                beerList.add(createBeer());
            }

            return beerList;
        }

        private Beer createBeer(){
            Beer beer = new Beer();
            beer.setNameDisplay("Shandy");
            beer.setName("Shandy");
            beer.setAbv("3.4");
            beer.setStatus("verified");
            return beer;
        }
    }