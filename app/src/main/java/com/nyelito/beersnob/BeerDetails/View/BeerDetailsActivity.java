package com.nyelito.beersnob.BeerDetails.View;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nyelito.beersnob.GlideLoader;
import com.nyelito.beersnob.R;
import com.nyelito.beersnob.BeerDetails.Presenter.BeerDetailsPresenter;
import com.nyelito.beersnob.BeerSearch.Model.Beer;

public class BeerDetailsActivity extends AppCompatActivity  implements DetailsMvpView{

    public static final String BEER_DETAILS_KEY = "BEER_DETAILS_KEY";
    private Beer currentBeer;
    private BeerDetailsPresenter presenter;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private boolean isLiked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_details);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter = new BeerDetailsPresenter();
        presenter.attachView(this);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }

        currentBeer = extras.getParcelable(BEER_DETAILS_KEY);


        fab = (FloatingActionButton) findViewById(R.id.like_button);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    presenter.toggleLikeBeer(currentBeer, isLiked);
            }
        });
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


            presenter.checkIfLiked(currentBeer);



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

    @Override
    public void showToast(String toastText) {
        Snackbar.make(findViewById(R.id.coordinator_layout), toastText, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setLiked(boolean isLiked) {

        this.isLiked = isLiked;
        Drawable thumbIcon = null;
        if(isLiked){
            thumbIcon = ContextCompat.getDrawable(BeerDetailsActivity.this, R.drawable.ic_thumb_down_black_24dp);

        }else{
            thumbIcon = ContextCompat.getDrawable(BeerDetailsActivity.this, R.drawable.ic_thumb_up_black_24dp);

        }

        thumbIcon.setTint(ContextCompat.getColor(BeerDetailsActivity.this, R.color.white));
        fab.setImageDrawable(thumbIcon);
    }


}
