package com.example.timetochange.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.timetochange.R;

public class EventsPageActivity extends AppCompatActivity {

    ImageView imageEventsDetail, btnShare;
    TextView tvTitleEventsDetail, tvCapacityEventsDetail, tvPriceEventsDetail, tvLocationEventsDetail, tvDateEventsDetail, tvTextEventsDetail, btnRegister;
    public String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_page);
        init();
        getEventsIntent();
        setListeners();
    }

    private void init() {
        imageEventsDetail = findViewById(R.id.imageEventsDetail);
        tvTitleEventsDetail = findViewById(R.id.tvTitleEventsDetail);
        tvCapacityEventsDetail = findViewById(R.id.tvCapacityEventsDetail);
        tvPriceEventsDetail = findViewById(R.id.tvPriceEventsDetail);
        tvLocationEventsDetail = findViewById(R.id.tvLocationEventsDetail);
        tvDateEventsDetail = findViewById(R.id.tvDateEventsDetail);
        tvTextEventsDetail = findViewById(R.id.tvTextEventsDetail);
        btnRegister = findViewById(R.id.btnRegister);
        btnShare = findViewById(R.id.btnShare);
    }

    private void getEventsIntent() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String image = intent.getStringExtra("image");
        String price = intent.getStringExtra("price");
        String date = intent.getStringExtra("date");
        String location = intent.getStringExtra("location");
        String text = intent.getStringExtra("text");
        String capacity = intent.getStringExtra("capacity");
        link = intent.getStringExtra("link");

        Glide.with(EventsPageActivity.this).load(image).into(imageEventsDetail);
        tvTitleEventsDetail.setText(title);
        tvPriceEventsDetail.setText(price);
        tvDateEventsDetail.setText(date);
        tvLocationEventsDetail.setText(location);
        tvTextEventsDetail.setText(text);
        tvCapacityEventsDetail.setText(capacity);
    }

    private void setListeners() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                startActivity(browserIntent);
            }
        });
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Seolution");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, link);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });
    }
}
