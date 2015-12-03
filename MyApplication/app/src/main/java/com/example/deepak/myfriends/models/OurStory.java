package com.example.deepak.myfriends.models;

import com.example.deepak.myfriends.interfaces.IDataModel;
import com.google.gson.annotations.SerializedName;

/**
 *
 */
public class OurStory implements IDataModel{
/*
  {
            "type": "checkin_card",
            "title": "Ataparty",
            "image_url": "http: //3.bp.blogspot.com/-wu_gj60FXJI/U9a9QhC-ttI/AAAAAAAAAQQ/L_3CPD61NNw/s1600/Manifest+Friends.jpg",
            "location_url": "https: //www.google.co.in/maps/place/Compassites+Software+Solutions+Pvt.Ltd",
            "more_images": "http://sampleapp.com/moreimages"
            content
        },
 */

    @SerializedName("type")
    private String type;
    @SerializedName("title")
    private String title;
    @SerializedName("image_url")
    private String imageUrl;
    @SerializedName("location_url")
    private String locationUrl;
    @SerializedName("more_images")
    private String moreImages;
    @SerializedName("content")
    private String content;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLocationUrl() {
        return locationUrl;
    }

    public void setLocationUrl(String locationUrl) {
        this.locationUrl = locationUrl;
    }

    public String getMoreImages() {
        return moreImages;
    }

    public void setMoreImages(String moreImages) {
        this.moreImages = moreImages;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
