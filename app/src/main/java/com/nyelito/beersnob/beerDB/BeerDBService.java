package com.nyelito.beersnob.beerDB;


import com.nyelito.beersnob.beerDetails.model.DetailedBeerResult;
import com.nyelito.beersnob.beersearch.model.BeerSearchData;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by User on 7/9/2017.
 */

public interface BeerDBService {

    @GET("search/")
    Observable<BeerSearchData> searchForBeer(@Query("q") String keyword, @Query("key") String apiKey, @Query("type") String searchType);

    @GET("beer/{id}/")
    Observable<DetailedBeerResult> getBeerDetails(@Path("id") String id, @Query("key") String apiKey);



    class Factory{
        public static BeerDBService create(){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://api.brewerydb.com/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            return retrofit.create(BeerDBService.class);
        }
    }


}
