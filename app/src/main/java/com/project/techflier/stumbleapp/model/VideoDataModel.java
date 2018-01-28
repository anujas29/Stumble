package com.project.techflier.stumbleapp.model;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by anuja on 1/5/2018.
 */

@Parcel
public class VideoDataModel  {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("link")
    @Expose
    private String link;

    @SerializedName("title")
    @Expose
    private Title title;

    @SerializedName("content")
    @Expose
    private Content content;

   /* @Override
    public int describeContents() {
        return 0;
    }*/


    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    /* protected VideoDataModel(Parcel in) {
             id = in.readByte() == 0x00 ? null : in.readInt();
             date = in.readString();
             link = in.readString();
             title = (Title) in.readValue(Title.class.getClassLoader());
         }


         @Override
         public void writeToParcel(Parcel dest, int flags) {
             dest.writeValue(id);
             dest.writeString(date);
             dest.writeString(link);
             dest.writeParcelable(title, flags);
         }

         private void readFromParcel(Parcel in) {
             id = in.readInt();
             date = in.readString();
             link = in.readString();
             title = (Title) in.readValue(Title.class.getClassLoader());
             //title = in.readParcelable(com.project.techflier.stumbleapp.model.Title.class.getClassLoader());

         }

         *//*protected VideoDataModel(Parcel in) {
        readFromParcel(in);
    }*//*

        public static final Parcelable.Creator<VideoDataModel> CREATOR = new Creator<VideoDataModel>() {
        public VideoDataModel createFromParcel(Parcel source) {
            return new VideoDataModel(source);

        }
        public VideoDataModel[] newArray(int size) {
            return new VideoDataModel[size];
        }
    };*/
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}