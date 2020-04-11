package com.example.timetochange.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.timetochange.Fragments.ContactFragment;
import com.example.timetochange.Fragments.EventsFragment;
import com.example.timetochange.Fragments.HomeFragment;
import com.example.timetochange.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton btnHome;
    LinearLayout lnrEvents, lnrContact;
    FrameLayout mainFrameLayout;

    HomeFragment homeFragment = new HomeFragment();
    EventsFragment eventsFragment = new EventsFragment();
    ContactFragment contactFragment = new ContactFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setupActivity();
        setListeners();
    }

    private void init() {
        btnHome = findViewById(R.id.btnHome);
        lnrEvents = findViewById(R.id.lnrEvents);
        lnrContact = findViewById(R.id.lnrContact);
        mainFrameLayout = findViewById(R.id.mainFrameLayout);
    }

    private void setupActivity() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(R.id.mainFrameLayout, homeFragment);
        transaction.commit();
    }

    private void setListeners() {
        lnrContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupFragment(contactFragment);
            }
        });
        lnrEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupFragment(eventsFragment);
            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupFragment(homeFragment);
            }
        });
    }

    private void setupFragment(Fragment selectedFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(R.id.mainFrameLayout, selectedFragment);
        transaction.commit();
    }
}
