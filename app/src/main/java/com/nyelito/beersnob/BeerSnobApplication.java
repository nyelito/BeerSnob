package com.nyelito.beersnob;

import android.app.Application;
import android.content.Context;

import com.nyelito.beersnob.BeerDB.BeerDBService;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by User on 7/9/2017.
 */

public class BeerSnobApplication extends Application {

    private BeerDBService beerDBService;
    private Scheduler defaultSubscribeScheduler;

    public static BeerSnobApplication get(Context context) {
        return (BeerSnobApplication) context.getApplicationContext();
    }

    public BeerDBService getBeerDBService() {
        if (beerDBService == null) {
            beerDBService = BeerDBService.Factory.create();
        }
        return beerDBService;
    }

    //For setting mocks during testing
    public void setBeerDBService(BeerDBService beerDBService) {
        this.beerDBService = beerDBService;
    }

    public Scheduler defaultSubscribeScheduler() {
        if (defaultSubscribeScheduler == null) {
            defaultSubscribeScheduler = Schedulers.io();
        }
        return defaultSubscribeScheduler;
    }

    //User to change scheduler from tests
    public void setDefaultSubscribeScheduler(Scheduler scheduler) {
        this.defaultSubscribeScheduler = scheduler;
    }
}
