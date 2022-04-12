package com.example.hotel_application.utils.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryResponseData {

    @SerializedName("Cat_id")
    @Expose
    private Integer catId;
    @SerializedName("Cat_name")
    @Expose
    private String catName;
    @SerializedName("Cat_image")
    @Expose
    private String catImage;

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatImage() {
        return catImage;
    }

    public void setCatImage(String catImage) {
        this.catImage = catImage;
    }
}
