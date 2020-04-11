package com.example.timetochange.RecyclersView.Examples;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.timetochange.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class ExamplesAdapter extends RecyclerView.Adapter<ExamplesAdapter.MyViewHolder> {
    List<ExamplesModel> examplesModels;
    Context context;

    public ExamplesAdapter(List<ExamplesModel> examplesModels, Context context) {
        this.examplesModels = examplesModels;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.example_item_design, parent, false);

        return new ExamplesAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Glide.with(context).load(examplesModels.get(position).getImage()).into(holder.examplesImage);
        holder.exampleTitle.setText(examplesModels.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(examplesModels.get(position).getLink()));
                context.startActivity(browserIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return examplesModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView examplesImage;
        TextView exampleTitle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            examplesImage = itemView.findViewById(R.id.exampleImage);
            exampleTitle = itemView.findViewById(R.id.exampleTitle);
        }
    }
}
