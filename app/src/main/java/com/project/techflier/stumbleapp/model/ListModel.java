package com.project.techflier.stumbleapp.model;

/**
 * Created by anuja on 1/22/2018.
 */

public class ListModel {




    public static final int IMAGE_TYPE =1;
    public String id , title;
            //subtitle, Image;
    public int type;


    public ListModel ( String  mId, String mtitle  ){
        this.id = mId;
        this.title = mtitle;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
