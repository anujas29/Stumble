package com.project.techflier.stumbleapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by anuja on 1/22/2018.
 */

@Parcel
public class Image {

    @SerializedName("source_url")
    @Expose
    String mImageURL;

    public String getmImageURL() {
        return mImageURL;
    }

    public void setmImageURL(String mImageURL) {
        this.mImageURL = mImageURL;
    }


}
