
package com.nyelito.beersnob.BeerDetails.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nyelito.beersnob.BeerSearch.Model.Beer;

public class DetailedBeerResult {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("detailedBeer")
    @Expose
    private Beer detailedBeer;
    @SerializedName("status")
    @Expose
    private String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Beer getDetailedBeer() {
        return detailedBeer;
    }

    public void setDetailedBeer(Beer detailedBeer) {
        this.detailedBeer = detailedBeer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
