package com.example.timetochange.RecyclersView.Specialty;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timetochange.R;
import com.example.timetochange.RecyclersView.Products.ProductsAdapter;

import java.util.List;

public class SpecialtyAdapter extends RecyclerView.Adapter<SpecialtyAdapter.MyViewHolder> {

    List<SpecialtyModel> specialtyModel;
    Context context;

    public SpecialtyAdapter(List<SpecialtyModel> specialtyModel, Context context) {
        this.specialtyModel = specialtyModel;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.specialty_item_rv, parent, false);

        return new SpecialtyAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvSpecialityTitle.setText(specialtyModel.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return specialtyModel.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvSpecialityTitle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSpecialityTitle = itemView.findViewById(R.id.tvSpecialityTitle);
        }
    }
}
