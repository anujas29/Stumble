package com.project.techflier.stumbleapp.fragment;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
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
import com.project.techflier.stumbleapp.adapter.ArticleAdapter;
import com.project.techflier.stumbleapp.adapter.FavoriteAdapter;
import com.project.techflier.stumbleapp.data.StumbleContract;
import com.project.techflier.stumbleapp.model.ArticleDataModel;
import com.project.techflier.stumbleapp.model.FabData;
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
 * Created by anuja on 1/27/2018.
 */

public class FavoriteFragment extends Fragment implements  LoaderManager.LoaderCallbacks<Cursor> {

    List<FabData> mList = new ArrayList<>();
    ArticleDataModel articleData  = new ArticleDataModel() ;
    public static FavoriteAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    View v;
    RecyclerView mRecyclerView;
    private static final int LOADER_ID = 0;
    private String baseURL = "https://www.techflier.com";
    public String articleID;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.videos_data, container, false);
        // mList = new ArrayList<ListModel>();
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mAdapter = new FavoriteAdapter(mList,getActivity());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);


        mRecyclerView.addOnItemTouchListener(new ArticleFragment.RecyclerviewTouchListener(getContext(), mRecyclerView, new ArticleFragment.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Intent intent = new Intent(getContext(), ArticleDetails.class);
                //intent.putExtra(Intent.EXTRA_TEXT, VideoDataList.get(position));
                // intent.putExtra(Intent.EXTRA_TEXT, mList.get(position));
                System.out.println("------------------------ fab recyclerView--------------------------------------"+mList.get(position));

                articleID = mList.get(position).getmId();
//                intent.putExtra(Intent.EXTRA_TEXT, Parcels.wrap(mList.get(position)));
//                startActivity(intent);

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
            getArticle();
        }
        else
            Toast.makeText(getContext(), getString(R.string.no_internet_available), Toast.LENGTH_LONG).show();
    }
    private boolean isNetworkConnected() {
        ConnectivityManager conMgr =  (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private void  getArticle()
    {
        //getLoaderManager().initLoader(TASK_LOADER_ID, null, this);
        getLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = StumbleContract.StumbleEntry.CONTENT_URI;
        return new CursorLoader(getContext(),
                uri,

                StumbleContract.StumbleEntry.ARTICLE_COLUMNS,
                null,
                null,
                null);


    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.add(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }


    public interface ClickListener {
        void onClick(View view, int position);
        void onLongClick(View view, int position);
    }

    public static class RecyclerviewTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        // private MainActivity.ClickListener clickListener;
        private FavoriteFragment.ClickListener clickListener;

        public RecyclerviewTouchListener(Context context, final RecyclerView recyclerView, final FavoriteFragment.ClickListener clickListener) {
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
