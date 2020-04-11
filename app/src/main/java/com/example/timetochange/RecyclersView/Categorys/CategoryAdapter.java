package com.example.timetochange.RecyclersView.Categorys;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timetochange.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    List<CategoryModel> categoryModel;
    Context context;

    public CategoryAdapter(List<CategoryModel> categoryModel, Context context) {
        this.categoryModel = categoryModel;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_item_design, parent, false);

        return new CategoryAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.tvTitleCategory.setText("# " + categoryModel.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(categoryModel.get(position).getLink()));
                context.startActivity(browserIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryModel.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitleCategory;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitleCategory = itemView.findViewById(R.id.tvTitleCategory);
        }
    }
}
