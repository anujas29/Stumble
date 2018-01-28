package com.project.techflier.stumbleapp.rest;

import com.project.techflier.stumbleapp.model.ArticleDataModel;
import com.project.techflier.stumbleapp.model.Image;
import com.project.techflier.stumbleapp.model.VideoDataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by anuja on 1/4/2018.
 */

public interface RetrofitDataInterface {
    @GET("wp-json/wp/v2/posts?categories=831")
    Call<List<VideoDataModel>> getVideos();

    @GET("wp-json/wp/v2/posts")
    Call<List<ArticleDataModel>> getArticle();

    @GET("wp-json/wp/v2/media/{id}")
    Call<Image> getImage(@Path("id") String mId);

    @GET("wp-json/wp/v2/posts/{id}")
    Call<ArticleDataModel> getArticleById(@Path("id") String Id);

}
