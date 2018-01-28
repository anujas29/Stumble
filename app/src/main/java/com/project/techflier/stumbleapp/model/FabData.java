package com.project.techflier.stumbleapp.model;

/**
 * Created by anuja on 1/27/2018.
 */

public class FabData {

    private String mId;

    private String mDate;
    private String mLink;
    private String mMedia;

    private String mTitle;

    private String mContent;


//    public FabData(String id,String date, String link, String media, String title, String content)
//    {
//        mId = id;
//        mDate = date;
//        mLink = link;
//        mMedia = media;
//        mTitle = title;
//        mContent = content;
//
//    }

    public FabData(String id,String title,String media)
    {
        mId = id;
        mTitle = title;
        mMedia = media;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmLink() {
        return mLink;
    }

    public void setmLink(String mLink) {
        this.mLink = mLink;
    }

    public String getmMedia() {
        return mMedia;
    }

    public void setmMedia(String mMedia) {
        this.mMedia = mMedia;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }
}
