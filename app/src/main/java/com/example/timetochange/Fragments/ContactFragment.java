package com.example.timetochange.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.timetochange.R;
import com.example.timetochange.RecyclersView.Specialty.SpecialtyAdapter;
import com.example.timetochange.RecyclersView.Specialty.SpecialtyModel;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends Fragment {

    RecyclerView rvSpeciality;
    List<SpecialtyModel> specialtyModel = new ArrayList<>();
    SpecialtyAdapter specialtyAdapter;

    public ContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contact, container, false);
        init(rootView);
        setUpRecyclerViewSpeciality();
        return rootView;
    }

    private void init(View rootView) {
        rvSpeciality = rootView.findViewById(R.id.rvSpecialty);
    }

    private void setUpRecyclerViewSpeciality() {
        specialtyAdapter = new SpecialtyAdapter(specialtyModel, getActivity());
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(getActivity());
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.FLEX_END);
        rvSpeciality.setLayoutManager(layoutManager);
        rvSpeciality.setAdapter(specialtyAdapter);

        specialtyModel.add(new SpecialtyModel("طراح سایت"));
        specialtyModel.add(new SpecialtyModel("دیجیتال مارکتینک"));
        specialtyModel.add(new SpecialtyModel("سوشال مارکتینگ"));
        specialtyModel.add(new SpecialtyModel("سئو"));
        specialtyModel.add(new SpecialtyModel("مدرس طراحی سایت"));
        specialtyModel.add(new SpecialtyModel("مدرس مباحث سئو"));
    }
}
