package com.nyelito.beersnob.beersearch.View;

import android.content.Context;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nyelito.beersnob.GlideLoader;
import com.nyelito.beersnob.R;
import com.nyelito.beersnob.beersearch.model.Beer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 7/9/2017.
 */

public class BeerResultsAdapter extends RecyclerView.Adapter<BeerResultsAdapter.BeerResultsViewHolder>{

    private List<Beer> beerList;

    private Callback callback;

    public BeerResultsAdapter(List<Beer> beerList) {
        this.beerList = beerList;
    }

    public BeerResultsAdapter() {
        this.beerList = new ArrayList<>();
    }

    public void setBeerList(List<Beer> beerList) {
        this.beerList = beerList;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public BeerResultsAdapter.BeerResultsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.beer_item_layout, parent, false);

        final BeerResultsViewHolder viewHolder = new BeerResultsViewHolder(itemView);

        viewHolder.contentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.onItemClick(viewHolder.theBeer);
                }
            }
        });


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BeerResultsAdapter.BeerResultsViewHolder holder, int position) {
        Beer currBeer = beerList.get(position);

        Context context = holder.titleTextView.getContext();
        displayData(holder, currBeer);

        holder.theBeer = currBeer;


        GlideLoader glideLoader = new GlideLoader(holder.labelImageView, currBeer, context);
        glideLoader.loadBestImage(null);


    }

    private void displayData(BeerResultsViewHolder holder, Beer currBeer) {
        if (currBeer != null) {

            holder.titleTextView.setText(currBeer.getNameDisplay());
            holder.descriptionTextView.setText(currBeer.getDescription());

            if(currBeer.getAbv() != null){
                holder.abvTextView.setText(currBeer.getAbv() + "% ABV");
            }else{
                holder.abvTextView.setText("N/A");

            }

            if (currBeer.getGlass() != null) {
                holder.glassTextView.setText(currBeer.getGlass().getName());
            } else {
                holder.glassTextView.setText("N/A");
            }

            if (currBeer.getStyle() != null) {
                holder.styleTextView.setText(currBeer.getStyle().getShortName());
            } else {
                holder.styleTextView.setText("N/A");

            }
        }
    }

    @Override
    public int getItemCount() {
        if (beerList != null) {
            return beerList.size();
        } else {
            return 0;
        }
    }

    public static class BeerResultsViewHolder extends RecyclerView.ViewHolder {

        public View contentLayout;
        public TextView titleTextView;
        public ImageView labelImageView;
        public TextView descriptionTextView;
        public TextView styleTextView;
        public TextView abvTextView;
        public TextView glassTextView;
        public Beer theBeer;

        public BeerResultsViewHolder(View itemView) {
            super(itemView);

            contentLayout = itemView.findViewById(R.id.layout_content);
            titleTextView = (TextView) itemView.findViewById(R.id.text_beer_title);
            descriptionTextView = (TextView) itemView.findViewById(R.id.text_beer_description);
            styleTextView = (TextView) itemView.findViewById(R.id.text_style);
            abvTextView = (TextView) itemView.findViewById(R.id.text_abv);
            glassTextView = (TextView) itemView.findViewById(R.id.text_glass);
            labelImageView = (ImageView) itemView.findViewById(R.id.label_image);
        }
    }

    public interface Callback {
        void onItemClick(Beer beer);
    }

}
