package com.project.techflier.stumbleapp.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.techflier.stumbleapp.R;
import com.project.techflier.stumbleapp.data.StumbleContract;
import com.project.techflier.stumbleapp.model.ArticleDataModel;
import com.project.techflier.stumbleapp.model.Image;
import com.project.techflier.stumbleapp.model.ListModel;
import com.project.techflier.stumbleapp.rest.RetrofitDataInterface;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by anuja on 1/17/2018.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.MyVideoViewHolder> {

   // private List<ListModel> video_dataset;

    private List<ArticleDataModel> video_dataset;
    private Context mContext;
    private String baseURL = "https://www.techflier.com";
    // private final onVideoCallbacks mCallbacks;

//    public VideosAdapter(List<VideoDataModel> mlist,  onVideoCallbacks callbacks) {
//        this.video_dataset = mlist;
//        //this.mContext = context;
//        mCallbacks = callbacks;
//    }

    public ArticleAdapter(List<ArticleDataModel> mlist, Context context) {
        this.video_dataset = mlist;
        this.mContext = context;

    }

//    public VideosAdapter(List<VideoDataModel> mlist, Context context, onVideoCallbacks callbacks) {
//        this.video_dataset = mlist;
//        this.mContext = context;
//        mCallbacks = callbacks;
//    }

//    public interface onVideoCallbacks {
//        void watch(VideoDataModel mVideoDataModel, int position);
//    }

    @Override
    public ArticleAdapter.MyVideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext()).inflate(R.layout.videos_detail, parent, false);
        return new ArticleAdapter.MyVideoViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(final ArticleAdapter.MyVideoViewHolder holder, final int position) {

        final ArticleDataModel mDataModel = video_dataset.get(position);
        final Context context = holder.mView.getContext();

//        ((ArticleAdapter.MyVideoViewHolder) holder).Video_imageTitle.setText(mDataModel.title);
//
//        Picasso.with(context).load(mDataModel.Image).fit().into(holder.Video_imageView);

        ((ArticleAdapter.MyVideoViewHolder) holder).Video_imageTitle.setText(mDataModel.getArticle_title().getRendered());
        String mMediaID = mDataModel.getMfeatured_media();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitDataInterface service = retrofit.create(RetrofitDataInterface.class);
        //Call<List<ArticleDataModel>> call = service.getArticle();
        Call<Image> call = service.getImage(mMediaID);
        call.enqueue(new Callback<Image>() {
            @Override
            public void onResponse(Call<Image> call, Response<Image> response) {
                Image json = response.body();
                System.out.println("----------------------------- json onResponse ---");
                // mList = new ListModel(ListModel.IMAGE_TYPE, mTitle,json.getmImageURL());
                if (json != null) {
                   // mList.add(new ListModel(ListModel.IMAGE_TYPE, mTitle, json.getmImageURL()));
                    Picasso.with(context).load(json.getmImageURL()).fit().into(holder.Video_imageView);

                    System.out.println("----------------------------- json not null----------------------------------------------"+json.getmImageURL());
                } else {
                    System.out.println("----------------------------- json null----------------------------------------------");
                }

            }

            @Override
            public void onFailure(Call<Image> call, Throwable t) {

            }
        });

    }

    @Override
    public int getItemCount() {
        System.out.println("------------- getItemCount() -------------------------"+video_dataset.size());
        return video_dataset.size();
    }

    public void add(List<ArticleDataModel> videoData) {
        video_dataset.clear();
        video_dataset.addAll(videoData);
        notifyDataSetChanged();
    }

    public class MyVideoViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.video_image)
        ImageView Video_imageView;
        @BindView(R.id.video_title)
        TextView Video_imageTitle;
        @BindView(R.id.video_subtitle)
        TextView Video_imageSubtitle;

        public final View mView;

        public MyVideoViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mView = view;
        }
    }

    public void add(Cursor cursor) {
        video_dataset.clear();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                cursor.getString(StumbleContract.StumbleEntry.COL_ARTICLE_ID);

            }while (cursor.moveToNext());
        }

    }


}
