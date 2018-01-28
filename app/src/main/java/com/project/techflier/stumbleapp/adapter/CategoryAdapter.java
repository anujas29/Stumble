package com.project.techflier.stumbleapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.techflier.stumbleapp.R;
import com.project.techflier.stumbleapp.model.Categories;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by anuja on 1/17/2018.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyCategoryAdapter> {

    private List<Categories> mCategories;
    private Context mContext;

    public CategoryAdapter(List<Categories> mlist, Context context) {
        this.mCategories = mlist;
        this.mContext = context;

    }


    @Override
    public MyCategoryAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext()).inflate(R.layout.activity_category, parent, false);
        return new CategoryAdapter.MyCategoryAdapter(view) ;
    }

    @Override
    public void onBindViewHolder(MyCategoryAdapter holder, int position) {

        holder.mCategory_name.setText(mCategories.get(position).mName);

    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    public class MyCategoryAdapter extends RecyclerView.ViewHolder
    {
        @BindView(R.id.category_name)
        TextView mCategory_name;
        public final View mView;

        public MyCategoryAdapter(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mView = view;

        }
    }
}
