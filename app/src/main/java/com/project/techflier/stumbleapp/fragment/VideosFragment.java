//package com.project.techflier.stumbleapp.fragment;
//
//import android.support.v4.app.Fragment;
//import android.content.Context;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.project.techflier.stumbleapp.R;
//import com.project.techflier.stumbleapp.adapter.VideosAdapter;
//import com.project.techflier.stumbleapp.model.VideoDataModel;
//import com.project.techflier.stumbleapp.rest.RetrofitDataInterface;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
///**
// * Created by anuja on 1/5/2018.
// */
//
//public class VideosFragment extends Fragment {
//
//    List<VideoDataModel> VideoDataList = new ArrayList<>();
//    public static VideosAdapter adapter;
//    private LinearLayoutManager mLayoutManager;
//    View v;
//    RecyclerView recyclerView;
//    private String baseURL = "https://www.techflier.com";
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        //mLayoutManager = new LinearLayoutManager(VideosFragment.this, LinearLayoutManager.VERTICAL, false);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        v = inflater.inflate(R.layout.videos_data, container, false);
//        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
//        adapter = new VideosAdapter(VideoDataList, this);
////        recyclerView.setLayoutManager(layoutManager);
////        recyclerView.setAdapter(adapter);
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapter);
//
//        return v;
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        if (isNetworkConnected()) {
//            getRetrofit();
//        }
//        else
//            System.out.println("----------------- no internet connection ----------------------------------");
//         //Toast.makeText(getContext(), getString(R.string.no_internet_available), Toast.LENGTH_LONG).show();
//    }
//    private boolean isNetworkConnected() {
//        ConnectivityManager conMgr =  (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
//        return netInfo != null && netInfo.isConnectedOrConnecting();
//    }
//
//
//
//    public void getRetrofit(){
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(baseURL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        RetrofitDataInterface service = retrofit.create(RetrofitDataInterface.class);
//        Call<List<VideoDataModel>> call = service.getVideos();
//
//        call.enqueue(new Callback<List<VideoDataModel>>() {
//            @Override
//            public void onResponse(Call<List<VideoDataModel>> call, Response<List<VideoDataModel>> response) {
//                Log.e("VidosFragment", " response "+ response.body());
//                //ArrayList<VideoDataModel> movies = response.body().getResults();
//                List<VideoDataModel> VideosData = response.body();
//                updateData(VideosData);
//
//                System.out.println("----------------------------- response:::::"+response.body());
//
//                for(int i=0 ;i <response.body().size();i++)
//                {
//                    System.out.println("----------------------------- response:::::"+i);
//                    System.out.println("----------------------------- response:::::"+response.body().get(i).getTitle().getRendered().toString());
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<List<VideoDataModel>> call, Throwable t) {
//                System.out.println("----------------------------- onFailure:::::"+t);
//
//            }
//        });
//    }
//
//    private void updateData(List<VideoDataModel> data) {
//        System.out.println("----------------------------- updateData:::::"+data);
//
//        VideoDataList = data;
//        adapter = new VideosAdapter(VideoDataList, this);
//        recyclerView.setAdapter(adapter);
//
//    }
//
//
//
//}
