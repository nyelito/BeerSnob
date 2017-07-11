package com.nyelito.beersnob;

import com.nyelito.beersnob.beerDB.BeerDBService;
import com.nyelito.beersnob.beersearch.Presenter.MainPresenter;
import com.nyelito.beersnob.beersearch.View.MainMvpView;
import com.nyelito.beersnob.beersearch.model.Beer;
import com.nyelito.beersnob.beersearch.model.BeerSearchData;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by User on 7/10/2017.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainPresenterTest {

    MainPresenter mainPresenter;
    MainMvpView mainMvpView;
    BeerDBService beerDBService;

    @Before
    public void setup(){
        BeerSnobApplication application = (BeerSnobApplication) RuntimeEnvironment.application;
        beerDBService = mock(BeerDBService.class);

        application.setBeerDBService(beerDBService);

        application.setDefaultSubscribeScheduler(Schedulers.trampoline());
        mainPresenter = new MainPresenter();

        mainMvpView = mock(MainMvpView.class);

        when(mainMvpView.getContext()).thenReturn(application);
        mainPresenter.attachView(mainMvpView);

    }

    @After
    public void tearDown() {
        mainPresenter.detachView();
    }



    @Test
    public void searchForBeerCallShowResults() {
        String searchText = "Shandy";
        BeerSearchData beerSearchData = new MockCreator().newBeerSearchData(3);
        when(beerDBService.searchForBeer(searchText, MainPresenter.API_KEY, MainPresenter.SEARCH_TYPE))
                .thenReturn(Observable.just(beerSearchData));

        mainPresenter.searchForBeer(searchText);
        verify(mainMvpView).showProgressIndicator();
        verify(mainMvpView).showSearchResults(beerSearchData.getData());
    }



}
