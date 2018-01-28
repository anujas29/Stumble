package com.project.techflier.stumbleapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.techflier.stumbleapp.R;
import com.project.techflier.stumbleapp.activity.MediaBytesDetails;
import com.project.techflier.stumbleapp.model.VideoDataModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by anuja on 1/5/2018.
 */

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.MyVideoViewHolder> {

    private List<VideoDataModel> video_dataset;
    private Context mContext;
   // private final onVideoCallbacks mCallbacks;

//    public VideosAdapter(List<VideoDataModel> mlist,  onVideoCallbacks callbacks) {
//        this.video_dataset = mlist;
//        //this.mContext = context;
//        mCallbacks = callbacks;
//    }

    public VideosAdapter(List<VideoDataModel> mlist, Context context) {
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
    public MyVideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext()).inflate(R.layout.videos_detail, parent, false);
        return new MyVideoViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(final MyVideoViewHolder holder, final int position) {

        final VideoDataModel mDataModel = video_dataset.get(position);
        final Context context = holder.mView.getContext();

        //holder.Video_imageTitle.setText(video_dataset.get(position).getTitle().getRendered());

        ((MyVideoViewHolder) holder).Video_imageTitle.setText(video_dataset.get(position).getTitle().getRendered());

//         holder.mView.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View v) {
//                 mCallbacks.watch(mDataModel, holder.getAdapterPosition());
//             }
//         });

    }

    @Override
    public int getItemCount() {
        return video_dataset.size();
    }

    public void add(List<VideoDataModel> videoData) {
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


}
