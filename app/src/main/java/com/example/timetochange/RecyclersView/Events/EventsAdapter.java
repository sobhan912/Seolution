package com.example.timetochange.RecyclersView.Events;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.timetochange.Activitys.EventsPageActivity;
import com.example.timetochange.R;
import com.example.timetochange.RecyclersView.Products.ProductsAdapter;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.MyViewHolder> {
    List<EventsModel> events;
    Context context;

    public EventsAdapter(List<EventsModel> events, Context context) {
        this.events = events;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.events_item_design, parent, false);

        return new EventsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int position) {
        Glide.with(context).load(events.get(position).getImage()).into(myViewHolder.imageEventsPage);
        myViewHolder.titleEventsPage.setText(events.get(position).getTitle());
        myViewHolder.placeEventsPage.setText(events.get(position).getLocation());
        myViewHolder.priceEventsPage.setText(events.get(position).getPrice());
        myViewHolder.dateEventsPage.setText(events.get(position).getDate());

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EventsPageActivity.class);
                intent.putExtra("title", events.get(position).getTitle());
                intent.putExtra("image", events.get(position).getImage());
                intent.putExtra("price", events.get(position).getPrice());
                intent.putExtra("link", events.get(position).getLink());
                intent.putExtra("date", events.get(position).getDate());
                intent.putExtra("text", events.get(position).getText());
                intent.putExtra("location", events.get(position).getLocation());
                intent.putExtra("capacity", events.get(position).getCapacity());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView imageEventsPage;
        TextView titleEventsPage, placeEventsPage, priceEventsPage, dateEventsPage;
        ConstraintLayout layoutEventsPage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageEventsPage = itemView.findViewById(R.id.imageEventsPage);
            titleEventsPage = itemView.findViewById(R.id.titleEventsPage);
            placeEventsPage = itemView.findViewById(R.id.placeEventsPage);
            priceEventsPage = itemView.findViewById(R.id.priceEventsPage);
            dateEventsPage = itemView.findViewById(R.id.dateEventsPage);
            layoutEventsPage = itemView.findViewById(R.id.layoutEventsPage);
        }
    }
}
