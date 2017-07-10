package com.nyelito.beersnob.beersearch.Presenter;


import android.util.Log;

import com.nyelito.beersnob.BeerSnobApplication;
import com.nyelito.beersnob.Presenter;
import com.nyelito.beersnob.beersearch.View.MainMvpView;
import com.nyelito.beersnob.beersearch.model.Beer;
import com.nyelito.beersnob.beerDB.BeerDBService;
import com.nyelito.beersnob.beersearch.model.BeerSearchData;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements Presenter<MainMvpView> {
    public static final String API_KEY = "8118938a83520185a420e40072449d19";
    public static String TAG = "MainPresenter";

    private MainMvpView mainMvpView;
    private BeerSearchData beerResults;

    @Override
    public void attachView(MainMvpView view) {
        this.mainMvpView = view;
    }

    @Override
    public void detachView() {
        this.mainMvpView = null;
    }

    public void searchForBeer(final String searchText) {
        BeerSnobApplication application = BeerSnobApplication.get(mainMvpView.getContext());
        BeerDBService beerDBService = application.getBeerDBService();

        Observable<BeerSearchData> searchObservable = beerDBService.searchForBeer(searchText, API_KEY, "beer");

        mainMvpView.showProgressIndicator();

        searchObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BeerSearchData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BeerSearchData value) {
                        beerResults = value;
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "Error", e);
                    }

                    @Override
                    public void onComplete() {
                        if (mainMvpView != null) {
                            List<Beer> verifiedBeers = new ArrayList<>();
                            if(beerResults != null && beerResults.getData() != null) {
                                for (Beer b : beerResults.getData()) {
                                    if (b.getStatus().equalsIgnoreCase("verified")) {
                                        verifiedBeers.add(b);
                                    }
                                }
                            }
                            mainMvpView.showSearchResults(verifiedBeers);
                        }
                    }
                });
    }

}