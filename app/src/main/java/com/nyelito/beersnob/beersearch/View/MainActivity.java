package com.nyelito.beersnob.beersearch.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nyelito.beersnob.beerDetails.view.BeerDetailsActivity;
import com.nyelito.beersnob.beersearch.Presenter.MainPresenter;
import com.nyelito.beersnob.R;
import com.nyelito.beersnob.beersearch.model.Beer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainMvpView{

    public static final String TAG = "Main Activity";
    public static final String BEERLIST_KEY = "BEERLIST_KEY";

    private MainPresenter presenter;

    private Toolbar toolbar;
    private EditText searchInput;
    private RecyclerView searchResultsRecyclerView;
    private List<Beer> currentBeerList;
    private ProgressBar progressBar;
    private TextView noDataText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null){
            currentBeerList = savedInstanceState.getParcelableArrayList(BEERLIST_KEY);
        }else{
            currentBeerList = new ArrayList<Beer>();
        }

        presenter = new MainPresenter();
        presenter.attachView(this);

        setContentView(R.layout.main_activity);
        //Set up ToolBar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        searchResultsRecyclerView = (RecyclerView) findViewById(R.id.beer_results_recycler_view);

        setupRecyclerView(searchResultsRecyclerView);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        noDataText = (TextView) findViewById(R.id.no_data_text);


        searchInput = (EditText) findViewById(R.id.search_edit_text);


        searchInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    presenter.searchForBeer(v.getText().toString());
                    hideSoftKeyboard();
                    return true;
                }
                return false;
            }
        });
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        BeerResultsAdapter resultsAdapter = new BeerResultsAdapter(currentBeerList);


        recyclerView.setAdapter(resultsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        updateBeerList();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @Override
    public Context getContext() {
        return this.getApplicationContext();
    }

    @Override
    public void showSearchResults(List<Beer> results) {
        currentBeerList = results;
        hideSoftKeyboard();

        progressBar.setVisibility(View.INVISIBLE);
        if(currentBeerList != null && !currentBeerList.isEmpty()){
            noDataText.setVisibility(View.INVISIBLE);
            searchResultsRecyclerView.setVisibility(View.VISIBLE);
            updateBeerList();
        }else{
            noDataText.setVisibility(View.VISIBLE);
        }
    }

    private void updateBeerList() {
        BeerResultsAdapter adapter = (BeerResultsAdapter) searchResultsRecyclerView.getAdapter();

        adapter.setCallback(new BeerResultsAdapter.Callback() {
            @Override
            public void onItemClick(Beer beer) {
                Intent i = new Intent(MainActivity.this, BeerDetailsActivity.class);
                i.putExtra(BeerDetailsActivity.BEER_DETAILS_KEY, beer);
                startActivity(i);

            }
        });
        adapter.setBeerList(currentBeerList);
        adapter.notifyDataSetChanged();
        searchResultsRecyclerView.requestFocus();

    }

    @Override
    public void showMessage(int stringId) {

    }

    @Override
    public void showProgressIndicator() {

        noDataText.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        searchResultsRecyclerView.setVisibility(View.INVISIBLE);


    }


    private void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(searchInput.getWindowToken(), 0);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        ArrayList<Beer> beerArrayList = new ArrayList<Beer>(currentBeerList);
        outState.putParcelableArrayList(BEERLIST_KEY, beerArrayList);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        currentBeerList = savedInstanceState.getParcelableArrayList(BEERLIST_KEY);
        updateBeerList();
    }
}
