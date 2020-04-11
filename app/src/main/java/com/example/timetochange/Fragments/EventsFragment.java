package com.example.timetochange.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.timetochange.R;
import com.example.timetochange.RecyclersView.Events.EventsAdapter;
import com.example.timetochange.RecyclersView.Events.EventsModel;
import com.example.timetochange.RecyclersView.Examples.ExamplesModel;
import com.example.timetochange.Utils.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment {

    RecyclerView rvEvents;
    List<EventsModel> eventsModels = new ArrayList<>();
    EventsAdapter eventsAdapter;
    ProgressDialog progressDialog;

    public EventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_events, container, false);
        init(rootView);
        eventsModels.clear();
        setUpRecyclerViewEvents();
        getEvents();
        return rootView;
    }

    private void init(View rootView) {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("لطفا منتظر بمانید ...");
        progressDialog.show();
        rvEvents = rootView.findViewById(R.id.rvEvents);
    }

    private void setUpRecyclerViewEvents() {
        eventsAdapter = new EventsAdapter(eventsModels, getActivity());
        rvEvents.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvEvents.setAdapter(eventsAdapter);
    }

    private void getEvents() {
        String url = "http://work-android.ir/Seolution/events.php";
        Response.Listener listener = new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                try {
                    JSONArray jsonArray = new JSONArray(response.toString());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        int id = object.getInt("id");
                        String title = object.getString("title");
                        String image = object.getString("image");
                        String price = object.getString("price");
                        String link = object.getString("link");
                        String date = object.getString("date");
                        String text = object.getString("text");
                        String location = object.getString("location");
                        String capacity = object.getString("capacity");

                        eventsModels.add(new EventsModel(id, title, image, price, link, date, text, location, capacity));
                        progressDialog.dismiss();
                    }
                    eventsAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        };
        StringRequest request = new StringRequest(Request.Method.GET, url, listener, errorListener);
        AppController.getInstance().addToRequestQueue(request);
    }
}
