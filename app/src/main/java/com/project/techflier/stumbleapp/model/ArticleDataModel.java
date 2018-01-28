package com.project.techflier.stumbleapp.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by USER on 23-12-2017.
 */


@Parcel
    public class ArticleDataModel  {

        @SerializedName("id")
        @Expose
        private Integer Article_id;

        @SerializedName("date")
        @Expose
        private String Article_date;

        @SerializedName("link")
        @Expose
        private String Article_link;

        @SerializedName("featured_media")
        @Expose
        private String mfeatured_media;

        @SerializedName("title")
        @Expose
        private Title Article_title;

        @SerializedName("content")
        @Expose
        private Content Article_content;

   /* @Override
    public int describeContents() {
        return 0;
    }*/

    public Integer getArticle_id() {
        return Article_id;
    }

    public String getMfeatured_media() {
        return mfeatured_media;
    }

    public void setMfeatured_media(String mfeatured_media) {
        this.mfeatured_media = mfeatured_media;
    }

    public void setArticle_id(Integer article_id) {
        Article_id = article_id;
    }

    public String getArticle_date() {
        return Article_date;
    }

    public void setArticle_date(String article_date) {
        Article_date = article_date;
    }

    public String getArticle_link() {
        return Article_link;
    }

    public void setArticle_link(String article_link) {
        Article_link = article_link;
    }

    public Title getArticle_title() {
        return Article_title;
    }

    public void setArticle_title(Title article_title) {
        Article_title = article_title;
    }

    public Content getArticle_content() {
        return Article_content;
    }

    public void setArticle_content(Content article_content) {
        Article_content = article_content;
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

    }