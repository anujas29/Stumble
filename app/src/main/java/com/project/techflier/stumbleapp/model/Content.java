package com.project.techflier.stumbleapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by anuja on 1/10/2018.
 */

@Parcel
public class Content {
    @SerializedName("rendered")
    @Expose
    private String youtubeURL;

    public Content()
    {

    }

    public String getYoutubeURL() {
        return youtubeURL;
    }

    public void setYoutubeURL(String youtubeURL) {
        this.youtubeURL = youtubeURL;
    }
}
