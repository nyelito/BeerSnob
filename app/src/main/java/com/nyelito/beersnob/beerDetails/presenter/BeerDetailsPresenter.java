package com.nyelito.beersnob.beerDetails.presenter;

import android.util.Log;

import com.nyelito.beersnob.BeerSnobApplication;
import com.nyelito.beersnob.Presenter;
import com.nyelito.beersnob.beerDetails.model.DetailedBeerResult;
import com.nyelito.beersnob.beerDetails.view.DetailsMvpView;
import com.nyelito.beersnob.beerDB.BeerDBService;
import com.nyelito.beersnob.beersearch.model.Beer;
import com.nyelito.beersnob.beersearch.model.BeerSearchData;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.nyelito.beersnob.beersearch.Presenter.MainPresenter.API_KEY;

/**
 * Created by User on 7/9/2017.
 */

public class BeerDetailsPresenter implements Presenter<DetailsMvpView> {

    private DetailsMvpView detailsMvpView;
    private Beer theBeer;

    @Override
    public void attachView(DetailsMvpView view) {
        detailsMvpView = view;
    }

    @Override
    public void detachView() {
        detailsMvpView = null;
    }

    public void searchForBeerDetails(String id){
        BeerSnobApplication application = BeerSnobApplication.get(detailsMvpView.getContext());
        BeerDBService beerDBService = application.getBeerDBService();

        Observable<DetailedBeerResult> detailsObservable = beerDBService.getBeerDetails("id", API_KEY);

        detailsObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DetailedBeerResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DetailedBeerResult value) {

                        theBeer = value.getDetailedBeer();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("DetailedPresenter", "Error", e);
                    }

                    @Override
                    public void onComplete() {
                        if (detailsMvpView != null) {
                            detailsMvpView.showBeerDetails(theBeer);
                        }
                    }
                });
    }
}
