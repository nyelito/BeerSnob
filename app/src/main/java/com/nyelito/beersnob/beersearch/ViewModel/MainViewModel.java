//package com.nyelito.beersnob.beersearch.ViewModel;
//
//import android.content.Context;
//import android.databinding.ObservableField;
//import android.support.annotation.NonNull;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.util.Log;
//import android.view.View;
//
//import com.nyelito.beersnob.BeerSnobApplication;
//import com.nyelito.beersnob.beerdb.BeerDBService;
//import com.nyelito.beersnob.beersearch.model.BeerSearchData;
//
//import org.w3c.dom.Text;
//
//import java.util.List;
//
//import io.reactivex.Observable;
//import io.reactivex.Observer;
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.disposables.Disposable;
//import io.reactivex.schedulers.Schedulers;
//
///**
// * Created by User on 7/9/2017.
// */
//
//public class MainViewModel implements ViewModel {
//
//    private static final String TAG = "MainViewModel";
//
//
//    private Context context;
////    private Subscription subscription;
//    private BeerSearchData beerResults;
//    private DataListener dataListener;
//    private String searchText;
//
//    public TextWatcher getSearchTextWatcher(){
//        Log.i(TAG, "Getting the text watcher");
//        return new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
//                searchText = charSequence.toString();
////                searchButtonVisibility.set(charSequence.length() > 0 ? View.VISIBLE : View.GONE);
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        };
//
//    }
//
//    public MainViewModel(Context context, DataListener dataListener){
//        this.context = context;
//        this.dataListener = dataListener;
//    }
//
//
//
//    public void setDataListener(DataListener dataListener) {
//        this.dataListener = dataListener;
//    }
//
//    public void onClickSearch(View view) {
//
//        searchForBeer(searchText);
//
//
//    }
//
//    private void searchForBeer(final String searchText) {
//        BeerSnobApplication application = BeerSnobApplication.get(context);
//        BeerDBService beerDBService = application.getBeerDBService();
//
//        Observable<BeerSearchData> searchObservable = beerDBService.searchForBeer(searchText, API_KEY);
//
//
//
//
//                searchObservable.subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<BeerSearchData>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(BeerSearchData value) {
//                        beerResults = value;
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, "Error", e);
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        if(dataListener != null){
//                            dataListener.onSearchChanged(beerResults);
//                        }
//                    }
//                });
//    }
//
//
//
//
//    @Override
//    public void destroy() {
////        if (subscription != null && !subscription.isUnsubscribed()) subscription.unsubscribe();
////        subscription = null;
//        context = null;
//        dataListener = null;
//    }
//
//    @Override
//    public void setSearchText(String searchText) {
//        this.searchText = searchText;
//    }
//
//
//    public interface DataListener {
//        void onSearchChanged(BeerSearchData repositories);
//    }
//}
