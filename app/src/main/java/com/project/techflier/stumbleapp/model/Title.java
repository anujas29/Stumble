package com.project.techflier.stumbleapp.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;


/**
 * Created by anuja on 1/5/2018.
 */

@Parcel
public class Title {

    @SerializedName("rendered")
    @Expose
    private String rendered;


    public String getRendered() {
        return rendered;
    }

    public void setRendered(String rendered) {
        this.rendered = rendered;
    }

    public Title() {
    }

    /* public Title(Parcel in){
        readFromParcel(in);
    }

    public static final Parcelable.Creator<Title> CREATOR = new Parcelable.Creator<Title>() {

        @Override
        public Title createFromParcel(Parcel source) {
            return new Title(source);
        }

        @Override
        public Title[] newArray(int size) {
            return new Title[size];
        }
    };

    @Override
    public int describeContents() {

        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(rendered);

    }

    private void readFromParcel(Parcel in) {
        rendered = in.readString();

    }*/
}