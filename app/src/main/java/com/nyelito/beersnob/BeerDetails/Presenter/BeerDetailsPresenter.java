package com.nyelito.beersnob.BeerDetails.Presenter;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.nyelito.beersnob.BeerSnobApplication;
import com.nyelito.beersnob.LikeDatabase.LikeDBHelper;
import com.nyelito.beersnob.LikeDatabase.LikeTableContract;
import com.nyelito.beersnob.Presenter;
import com.nyelito.beersnob.BeerDetails.Model.DetailedBeerResult;
import com.nyelito.beersnob.BeerDetails.View.DetailsMvpView;
import com.nyelito.beersnob.BeerDB.BeerDBService;
import com.nyelito.beersnob.BeerSearch.Model.Beer;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.nyelito.beersnob.BeerSearch.Presenter.MainPresenter.API_KEY;

/**
 * Created by User on 7/9/2017.
 */

public class BeerDetailsPresenter implements Presenter<DetailsMvpView> {

    private DetailsMvpView detailsMvpView;
    private Beer theBeer;
    private LikeDBHelper likeDBHelper;

    @Override
    public void attachView(DetailsMvpView view) {
        detailsMvpView = view;
        likeDBHelper = new LikeDBHelper(detailsMvpView.getContext());
    }

    @Override
    public void detachView() {
        detailsMvpView = null;
    }

    public void searchForBeerDetails(String id) {
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

    public void checkIfLiked(Beer b) {

        SQLiteDatabase db = likeDBHelper.getReadableDatabase();

    // Define a projection that specifies which columns from the database
    // you will actually use after this query.
        String[] projection = {
                LikeTableContract.LikeEntry._ID,
                LikeTableContract.LikeEntry.COLUMN_NAME_ID,
                LikeTableContract.LikeEntry.COLUMN_NAME_LIKE
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = LikeTableContract.LikeEntry.COLUMN_NAME_ID + " = ?";
        String[] selectionArgs = {b.getId()};

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                LikeTableContract.LikeEntry.COLUMN_NAME_ID + " DESC";

        Cursor cursor = db.query(
                LikeTableContract.LikeEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        List itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(LikeTableContract.LikeEntry._ID));
            itemIds.add(itemId);
        }
        cursor.close();

        detailsMvpView.setLiked(!itemIds.isEmpty());


    }

    public void toggleLikeBeer(Beer b, boolean likeStatus) {

        boolean newLikeStatus = !likeStatus;


        // Gets the data repository in write mode
        SQLiteDatabase db = likeDBHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(LikeTableContract.LikeEntry.COLUMN_NAME_ID, b.getId());
        values.put(LikeTableContract.LikeEntry.COLUMN_NAME_NAME, b.getName());
        values.put(LikeTableContract.LikeEntry.COLUMN_NAME_LIKE, newLikeStatus);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(LikeTableContract.LikeEntry.TABLE_NAME, null, values);

        if(newLikeStatus){

            detailsMvpView.showToast("Liked " + b.getNameDisplay() + "!");
        }else{
            detailsMvpView.showToast("Unliked " + b.getNameDisplay() + "!");

        }

        detailsMvpView.setLiked(newLikeStatus);
    }
}
