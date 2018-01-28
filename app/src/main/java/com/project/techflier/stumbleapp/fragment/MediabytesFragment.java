package com.project.techflier.stumbleapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.project.techflier.stumbleapp.R;
import com.project.techflier.stumbleapp.activity.MediaBytesDetails;
import com.project.techflier.stumbleapp.adapter.VideosAdapter;
import com.project.techflier.stumbleapp.model.VideoDataModel;
import com.project.techflier.stumbleapp.rest.RetrofitDataInterface;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import android.support.v4.app.Fragment;

/**
 * Created by anuja on 1/9/2018.
 */

public class MediabytesFragment extends Fragment {

    List<VideoDataModel> VideoDataList = new ArrayList<>();
    public static VideosAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    View v;
    RecyclerView recyclerView;
    private String baseURL = "https://www.techflier.com";



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.videos_data, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        adapter = new VideosAdapter(VideoDataList,getActivity());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerviewTouchListener(getContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //Toast.makeText(getContext(), MovieDataList.get(position)+ " is selected!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), MediaBytesDetails.class);
               // intent.putExtra(Intent.EXTRA_TEXT, VideoDataList.get(position));
               // System.out.println("------------------------recyclerView--------------------------------------"+VideoDataList.get(position));


                intent.putExtra(Intent.EXTRA_TEXT, Parcels.wrap(VideoDataList.get(position)));
                startActivity(intent);

//                Intent intent = new Intent(getActivity().getBaseContext(),
//                        MediaBytesDetails.class);
//                intent.putExtra("position", VideoDataList.get(position));
//                getActivity().startActivity(intent);
            }
            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return v;
    }

//    @Override
//    public void watch(VideoDataModel mVideoDataModel, int position) {
//
//        //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(trailer.getTrailerUrl())));
//        System.out.println("----------------- inside watch() ----------------------------------");
//        String mYoutubeLink = mVideoDataModel.getContent().getYoutubeURL();
//
//        String html = mVideoDataModel.getContent().getYoutubeURL();
//        Document doc = Jsoup.parse(html);
//        Elements links = doc.select("iframe");
//        System.out.println("============= links ================"+links);
//        String absoluteUrl = links.attr("abs:src");
//
//        System.out.println("============= scr ================"+absoluteUrl);
//        //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(absoluteUrl)));
//
//
////        Intent myIntent = new Intent(CurrentActivity.this, NextActivity.class);
////        myIntent.putExtra("key", value); //Optional parameters
////        CurrentActivity.this.startActivity(myIntent);
//
//        Intent intent = new Intent(getActivity(), MediaBytesDetails.class).putExtra("urlStr",absoluteUrl);
//        startActivity(intent);
//
//    }

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
        Call<List<VideoDataModel>> call = service.getVideos();

        call.enqueue(new Callback<List<VideoDataModel>>() {
            @Override
            public void onResponse(Call<List<VideoDataModel>> call, Response<List<VideoDataModel>> response) {
                Log.e("VidosFragment", " response "+ response.body());
                //ArrayList<VideoDataModel> movies = response.body().getResults();
                List<VideoDataModel> VideosData = response.body();
                updateData(VideosData);

                System.out.println("----------------------------- response:::::"+response.body());

//                for(int i=0 ;i <response.body().size();i++)
//                {
//                    System.out.println("----------------------------- response:::::"+i);
//                    System.out.println("----------------------------- response:::::"+response.body().get(i).getTitle().getRendered().toString());
//                }

            }

            @Override
            public void onFailure(Call<List<VideoDataModel>> call, Throwable t) {
                System.out.println("----------------------------- onFailure:::::"+t);

            }
        });
    }

    private void updateData(List<VideoDataModel> data) {
       // System.out.println("----------------------------- updateData:::::"+data);

        VideoDataList = data;
        adapter = new VideosAdapter(VideoDataList,getActivity());
        recyclerView.setAdapter(adapter);

    }

    public interface ClickListener {
        void onClick(View view, int position);
        void onLongClick(View view, int position);
    }

    public static class RecyclerviewTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        // private MainActivity.ClickListener clickListener;
        private ClickListener clickListener;

        public RecyclerviewTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
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
