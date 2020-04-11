package com.example.timetochange.RecyclersView.Products;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.timetochange.Activitys.ProductDetailActivity;
import com.example.timetochange.R;
import com.example.timetochange.RecyclersView.Examples.ExamplesAdapter;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder> {
    List<ProductsModel> products;
    Context context;

    public ProductsAdapter(List<ProductsModel> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.products_item_design, parent, false);

        return new ProductsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Glide.with(context).load(products.get(position).getImage()).into(holder.imageProduct);
        holder.titleProduct.setText(products.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("title", products.get(position).getTitle());
                intent.putExtra("text", products.get(position).getText());
                intent.putExtra("image", products.get(position).getImage());
                intent.putExtra("price", products.get(position).getPrice());
                intent.putExtra("link", products.get(position).getLink());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView imageProduct;
        TextView titleProduct;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageProduct = itemView.findViewById(R.id.imageProduct);
            titleProduct = itemView.findViewById(R.id.tvTitleProduct);
        }
    }
}
