package com.project.techflier.stumbleapp.fragment;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.project.techflier.stumbleapp.R;

import com.project.techflier.stumbleapp.activity.ArticleDetails;
import com.project.techflier.stumbleapp.activity.MediaBytesDetails;
import com.project.techflier.stumbleapp.adapter.ArticleAdapter;
import com.project.techflier.stumbleapp.adapter.VideosAdapter;
import com.project.techflier.stumbleapp.model.ArticleDataModel;
import com.project.techflier.stumbleapp.model.Image;
import com.project.techflier.stumbleapp.model.ListModel;
import com.project.techflier.stumbleapp.model.VideoDataModel;
import com.project.techflier.stumbleapp.rest.RetrofitDataInterface;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by anuja on 1/4/2018.
 */

public class ArticleFragment extends Fragment{

    //List<ArticleDataModel> ArticeDataList = new ArrayList<>();
    List<ArticleDataModel> mList = new ArrayList<>();
    //List<ListM> mList = new ArrayList<>();
    public static ArticleAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    View v;
    RecyclerView mRecyclerView;
    private String baseURL = "https://www.techflier.com";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.videos_data, container, false);
       // mList = new ArrayList<ListModel>();
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mAdapter = new ArticleAdapter(mList,getActivity());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

       mRecyclerView.addOnItemTouchListener(new RecyclerviewTouchListener(getContext(), mRecyclerView, new ClickListener() {
           @Override
           public void onClick(View view, int position) {

               Intent intent = new Intent(getContext(), ArticleDetails.class);
                //intent.putExtra(Intent.EXTRA_TEXT, VideoDataList.get(position));
              // intent.putExtra(Intent.EXTRA_TEXT, mList.get(position));
                System.out.println("------------------------recyclerView--------------------------------------"+mList.get(position));


               intent.putExtra(Intent.EXTRA_TEXT, Parcels.wrap(mList.get(position)));
               startActivity(intent);

           }

           @Override
           public void onLongClick(View view, int position) {

           }
       }));

        return v;
    }
    @Override
    public void onStart() {
        super.onStart();
        if (isNetworkConnected()) {
            getRetrofit();
        }
        else
            System.out.println("----------------- no internet connection ----------------------------------");
        //Toast.makeText(getContext(), getString(R.string.no_internet_available), Toast.LENGTH_LONG).show();
    }
    private boolean isNetworkConnected() {
        ConnectivityManager conMgr =  (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }



    public void getRetrofit(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitDataInterface service = retrofit.create(RetrofitDataInterface.class);

        Call<List<ArticleDataModel>> call = service.getArticle();
        call.enqueue(new Callback<List<ArticleDataModel>>() {
            @Override
            public void onResponse(Call<List<ArticleDataModel>> call, Response<List<ArticleDataModel>> response) {

                Log.e("Article", " response "+ response.body());
                //ArrayList<VideoDataModel> movies = response.body().getResults();
                List<ArticleDataModel> ArticleData = response.body();
                updateData(ArticleData);
               // mAdapter.add(ArticleData);

                System.out.println("----------------------------- response:::::"+response.body());

            }

            @Override
            public void onFailure(Call<List<ArticleDataModel>> call, Throwable t) {

            }
        });

    }



    private void updateData( List<ArticleDataModel> data) {
         System.out.println("----------------------------- updateData:::::"+data.size());

        mList = data;
        mAdapter = new ArticleAdapter(mList,getActivity());
        mRecyclerView.setAdapter(mAdapter);


//        for (int i=0; i<data.size();i++) {
//
//            String mMediaID = data.get(i).getMfeatured_media();
//            final String mTitle = data.get(i).getArticle_title().getRendered();
//
//            Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl(baseURL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//
//            RetrofitDataInterface service = retrofit.create(RetrofitDataInterface.class);
//            //Call<List<ArticleDataModel>> call = service.getArticle();
//            Call<Image> call = service.getImage(mMediaID);
//            call.enqueue(new Callback<Image>() {
//                @Override
//                public void onResponse(Call<Image> call, Response<Image> response) {
//
//                    Image json = response.body();
//                    System.out.println("----------------------------- json onResponse ---");
//                    // mList = new ListModel(ListModel.IMAGE_TYPE, mTitle,json.getmImageURL());
//                    if (json != null) {
//                        mList.add(new ListModel(ListModel.IMAGE_TYPE, mTitle, json.getmImageURL()));
//
//                        System.out.println("----------------------------- json not null----------------------------------------------");
//                    } else {
//                        System.out.println("----------------------------- json null----------------------------------------------");
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<Image> call, Throwable t) {
//
//                }
//            });
//
//
//
////        mAdapter = new ArticleAdapter(mList,getActivity());
////        mRecyclerView.setAdapter(mAdapter);
//        }
//        mAdapter.notifyDataSetChanged();
//        System.out.println("----------mList size() ------------"+mList.size());


//        mAdapter = new ArticleAdapter(mList,getActivity());
//        mRecyclerView.setAdapter(mAdapter);

    }

    public interface ClickListener {
        void onClick(View view, int position);
        void onLongClick(View view, int position);
    }

    public static class RecyclerviewTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        // private MainActivity.ClickListener clickListener;
        private ArticleFragment.ClickListener clickListener;

        public RecyclerviewTouchListener(Context context, final RecyclerView recyclerView, final ArticleFragment.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }



}
