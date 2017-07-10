package com.nyelito.beersnob.beersearch.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Beer implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("nameDisplay")
    @Expose
    private String nameDisplay;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("abv")
    @Expose
    private String abv;
    @SerializedName("ibu")
    @Expose
    private String ibu;
    @SerializedName("glasswareId")
    @Expose
    private Integer glasswareId;
    @SerializedName("srmId")
    @Expose
    private Integer srmId;
    @SerializedName("availableId")
    @Expose
    private Integer availableId;
    @SerializedName("styleId")
    @Expose
    private Integer styleId;
    @SerializedName("isOrganic")
    @Expose
    private String isOrganic;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("statusDisplay")
    @Expose
    private String statusDisplay;
    @SerializedName("servingTemperature")
    @Expose
    private String servingTemperature;
    @SerializedName("servingTemperatureDisplay")
    @Expose
    private String servingTemperatureDisplay;
    @SerializedName("createDate")
    @Expose
    private String createDate;
    @SerializedName("updateDate")
    @Expose
    private String updateDate;
    @SerializedName("glass")
    @Expose
    private Glass glass;
    @SerializedName("srm")
    @Expose
    private Srm srm;
    @SerializedName("available")
    @Expose
    private Available available;
    @SerializedName("style")
    @Expose
    private Style style;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("labels")
    @Expose
    private Labels labels;
    @SerializedName("foodPairings")
    @Expose
    private String foodPairings;
    @SerializedName("originalGravity")
    @Expose
    private String originalGravity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameDisplay() {
        return nameDisplay;
    }

    public void setNameDisplay(String nameDisplay) {
        this.nameDisplay = nameDisplay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAbv() {
        return abv;
    }

    public void setAbv(String abv) {
        this.abv = abv;
    }

    public String getIbu() {
        return ibu;
    }

    public void setIbu(String ibu) {
        this.ibu = ibu;
    }

    public Integer getGlasswareId() {
        return glasswareId;
    }

    public void setGlasswareId(Integer glasswareId) {
        this.glasswareId = glasswareId;
    }

    public Integer getSrmId() {
        return srmId;
    }

    public void setSrmId(Integer srmId) {
        this.srmId = srmId;
    }

    public Integer getAvailableId() {
        return availableId;
    }

    public void setAvailableId(Integer availableId) {
        this.availableId = availableId;
    }

    public Integer getStyleId() {
        return styleId;
    }

    public void setStyleId(Integer styleId) {
        this.styleId = styleId;
    }

    public String getIsOrganic() {
        return isOrganic;
    }

    public void setIsOrganic(String isOrganic) {
        this.isOrganic = isOrganic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDisplay() {
        return statusDisplay;
    }

    public void setStatusDisplay(String statusDisplay) {
        this.statusDisplay = statusDisplay;
    }

    public String getServingTemperature() {
        return servingTemperature;
    }

    public void setServingTemperature(String servingTemperature) {
        this.servingTemperature = servingTemperature;
    }

    public String getServingTemperatureDisplay() {
        return servingTemperatureDisplay;
    }

    public void setServingTemperatureDisplay(String servingTemperatureDisplay) {
        this.servingTemperatureDisplay = servingTemperatureDisplay;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public Glass getGlass() {
        return glass;
    }

    public void setGlass(Glass glass) {
        this.glass = glass;
    }

    public Srm getSrm() {
        return srm;
    }

    public void setSrm(Srm srm) {
        this.srm = srm;
    }

    public Available getAvailable() {
        return available;
    }

    public void setAvailable(Available available) {
        this.available = available;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Labels getLabels() {
        return labels;
    }

    public void setLabels(Labels labels) {
        this.labels = labels;
    }

    public String getFoodPairings() {
        return foodPairings;
    }

    public void setFoodPairings(String foodPairings) {
        this.foodPairings = foodPairings;
    }

    public String getOriginalGravity() {
        return originalGravity;
    }

    public void setOriginalGravity(String originalGravity) {
        this.originalGravity = originalGravity;
    }


    protected Beer(Parcel in) {
        id = in.readString();
        name = in.readString();
        nameDisplay = in.readString();
        description = in.readString();
        abv = in.readString();
        ibu = in.readString();
        glasswareId = in.readByte() == 0x00 ? null : in.readInt();
        srmId = in.readByte() == 0x00 ? null : in.readInt();
        availableId = in.readByte() == 0x00 ? null : in.readInt();
        styleId = in.readByte() == 0x00 ? null : in.readInt();
        isOrganic = in.readString();
        status = in.readString();
        statusDisplay = in.readString();
        servingTemperature = in.readString();
        servingTemperatureDisplay = in.readString();
        createDate = in.readString();
        updateDate = in.readString();
        glass = (Glass) in.readValue(Glass.class.getClassLoader());
        srm = (Srm) in.readValue(Srm.class.getClassLoader());
        available = (Available) in.readValue(Available.class.getClassLoader());
        style = (Style) in.readValue(Style.class.getClassLoader());
        type = in.readString();
        labels = (Labels) in.readValue(Labels.class.getClassLoader());
        foodPairings = in.readString();
        originalGravity = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(nameDisplay);
        dest.writeString(description);
        dest.writeString(abv);
        dest.writeString(ibu);
        if (glasswareId == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(glasswareId);
        }
        if (srmId == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(srmId);
        }
        if (availableId == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(availableId);
        }
        if (styleId == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(styleId);
        }
        dest.writeString(isOrganic);
        dest.writeString(status);
        dest.writeString(statusDisplay);
        dest.writeString(servingTemperature);
        dest.writeString(servingTemperatureDisplay);
        dest.writeString(createDate);
        dest.writeString(updateDate);
        dest.writeValue(glass);
        dest.writeValue(srm);
        dest.writeValue(available);
        dest.writeValue(style);
        dest.writeString(type);
        dest.writeValue(labels);
        dest.writeString(foodPairings);
        dest.writeString(originalGravity);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Beer> CREATOR = new Parcelable.Creator<Beer>() {
        @Override
        public Beer createFromParcel(Parcel in) {
            return new Beer(in);
        }

        @Override
        public Beer[] newArray(int size) {
            return new Beer[size];
        }
    };
}