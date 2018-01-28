package com.project.techflier.stumbleapp.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.techflier.stumbleapp.R;
import com.project.techflier.stumbleapp.data.StumbleContract;
import com.project.techflier.stumbleapp.model.ArticleDataModel;
import com.project.techflier.stumbleapp.model.FabData;
import com.project.techflier.stumbleapp.model.Image;
import com.project.techflier.stumbleapp.rest.RetrofitDataInterface;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by anuja on 1/27/2018.
 */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MyFavoriteViewHolder> {

    private List<FabData> FabDataset;
    private Context mContext;
    private String baseURL = "https://www.techflier.com";

    public FavoriteAdapter(List<FabData> mlist, Context context) {
        this.FabDataset = mlist;
        this.mContext = context;

    }

    @Override
    public MyFavoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext()).inflate(R.layout.videos_detail, parent, false);
        return new FavoriteAdapter.MyFavoriteViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(final MyFavoriteViewHolder holder, int position) {

        final FabData mDataModel = FabDataset.get(position);
        final Context context = holder.mView.getContext();
        holder.ImageTitle.setText(mDataModel.getmTitle());
        String mMediaID = mDataModel.getmMedia();

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
                    Picasso.with(context).load(json.getmImageURL()).fit().into(holder.ImageView);

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
         return FabDataset.size();
    }

    public class MyFavoriteViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.video_image)
        ImageView ImageView;
        @BindView(R.id.video_title)
        TextView ImageTitle;
        @BindView(R.id.video_subtitle)
        TextView Video_imageSubtitle;

        public final View mView;

        public MyFavoriteViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mView = view;
        }
    }

    public void add(ArrayList<FabData> article) {
        FabDataset.clear();
        FabDataset.addAll(article);
        notifyDataSetChanged();
    }

    public void add(Cursor cursor) {
        FabDataset.clear();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                //String id = cursor.getString(MoviesContract.MovieEntry.COL_MOVIE_ID);
                String id = cursor.getString(StumbleContract.StumbleEntry.COL_ARTICLE_ID);
                String title = cursor.getString(StumbleContract.StumbleEntry.COL_TITLE);
                String media = cursor.getString(StumbleContract.StumbleEntry.COL_MEDIA);

                FabData article = new FabData(id,title,media);
                System.out.println("---------------------------------------- movie:::"+article);
                FabDataset.add(article);
            } while (cursor.moveToNext());
        }
        notifyDataSetChanged();


    }
}
