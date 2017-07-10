package com.nyelito.beersnob.beerDetails.view;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nyelito.beersnob.GlideLoader;
import com.nyelito.beersnob.R;
import com.nyelito.beersnob.beersearch.model.Beer;

public class BeerDetailsActivity extends AppCompatActivity  implements DetailsMvpView{

    public static final String BEER_DETAILS_KEY = "BEER_DETAILS_KEY";
    private Beer currentBeer;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_details);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }

        currentBeer = extras.getParcelable(BEER_DETAILS_KEY);

        setupView();


    }

    private void setupView() {

        TextView styleView = (TextView) findViewById(R.id.style_text);
        TextView descriptionText = (TextView) findViewById(R.id.description_text);

        if(currentBeer != null){
            getSupportActionBar().setTitle(currentBeer.getNameDisplay());
            if(currentBeer.getStyle() != null){
                styleView.setText(currentBeer.getStyle().getName());
            }
            descriptionText.setText(currentBeer.getDescription());

            ImageView labelImage = (ImageView) findViewById(R.id.beer_details_hero_image);

            GlideLoader glideLoader = new GlideLoader(labelImage, currentBeer, BeerDetailsActivity.this);

            glideLoader.loadBestImage(toolbar);
        }
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void showBeerDetails(Beer theBeer) {
        currentBeer = theBeer;
        setupView();
    }
}
