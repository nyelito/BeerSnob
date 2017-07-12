package com.nyelito.beersnob;

import android.content.Context;
import android.support.v7.content.res.AppCompatResources;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.florent37.glidepalette.GlidePalette;
import com.nyelito.beersnob.BeerSearch.Model.Beer;

/**
 * Created by User on 7/9/2017.
 */

public class GlideLoader {

    private ImageView imageView;
    private Beer currBeer;
    private Context context;


    public GlideLoader(ImageView imageView, Beer currBeer, Context context) {
        this.imageView = imageView;
        this.currBeer = currBeer;
        this.context = context;
    }


    public void loadBestImage(View paletteView){

        if (currBeer != null) {
            String imageUrl = null;
            if (currBeer.getLabels() != null) {
                if (currBeer.getLabels().getLarge() != null) {
                    imageUrl = currBeer.getLabels().getLarge();
                } else if (currBeer.getLabels().getMedium() != null) {
                    imageUrl = currBeer.getLabels().getMedium();

                } else if (currBeer.getLabels().getIcon() != null) {
                    imageUrl = currBeer.getLabels().getIcon();

                }

            }

            if (imageUrl != null) {
                if(paletteView != null) {
                    Glide.with(context)
                            .load(imageUrl)
                            .listener(GlidePalette.with(imageUrl)
                                    .use(GlidePalette.Profile.VIBRANT_DARK)
                                    .intoBackground(paletteView))
                            .into(imageView);

                }else{
                    Glide.with(context)
                            .load(imageUrl)
                            .into(imageView);

                }

            } else {
                Glide.with(context).clear(imageView);
                imageView.setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.ic_local_drink_black_24dp));
            }
        }
    }
}
