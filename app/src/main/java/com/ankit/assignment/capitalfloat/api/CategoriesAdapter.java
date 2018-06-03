package com.ankit.assignment.capitalfloat.api;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ankit.assignment.capitalfloat.R;
import com.ankit.assignment.capitalfloat.model.Categories;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder> {

    List<Categories> mCategoryList;
    ArrayList<Categories> selectedList = new ArrayList<>();
    Context mContext;

    public CategoriesAdapter(Context context , List<Categories> categoryList){
        this.mContext = context;
        this.mCategoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_items , parent , false);
        return new CategoryViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        final int pos = position;
        holder.mCategoryTextView.setText(mCategoryList.get(pos).getCategories());
        holder.mCategoryCheckBox.setChecked(mCategoryList.get(pos).isSelected());
        holder.mCategoryCheckBox.setTag(mCategoryList.get(pos));
        holder.mCategoryCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox) v;
                Categories categories = (Categories) checkBox.getTag();
                categories.setSelected(checkBox.isSelected());
                mCategoryList.get(pos).setSelected(checkBox.isChecked());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{

        TextView mCategoryTextView;
        CheckBox mCategoryCheckBox;
        LinearLayout mCategoryLayout;

        public CategoryViewHolder(View view){
            super(view);

            mCategoryTextView = view.findViewById(R.id.tv_category);
            mCategoryCheckBox = view.findViewById(R.id.cb_category);
            mCategoryLayout = view.findViewById(R.id.ll_checkbox);
        }
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public List<Categories> getCategoryList() {
        for (int i = 0 ; i<mCategoryList.size() ; i++){
            if (mCategoryList.get(i).isSelected()){
                selectedList.add(mCategoryList.get(i));
            }
        }
        return selectedList;
    }

    public int getSelectedSize(){
        return selectedList.size();
    }
}
