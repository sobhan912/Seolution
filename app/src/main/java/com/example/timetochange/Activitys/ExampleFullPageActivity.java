package com.example.timetochange.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.timetochange.R;
import com.example.timetochange.RecyclersView.Examples.ExamplesAdapter;
import com.example.timetochange.RecyclersView.Examples.ExamplesModel;
import com.example.timetochange.Utils.AppController;
import com.example.timetochange.Utils.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ExampleFullPageActivity extends AppCompatActivity {

    RecyclerView rvExamplesFullPage;
    List<ExamplesModel> examplesModel = new ArrayList<>();
    ExamplesAdapter examplesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_full_page);
        init();
        setUpRecyclerViewExamples();
        getSampleSite();
    }

    private void init() {
        rvExamplesFullPage = findViewById(R.id.rvExamplesFullPage);
    }

    private void setUpRecyclerViewExamples() {
        examplesAdapter = new ExamplesAdapter(examplesModel, ExampleFullPageActivity.this);
        int mNoOfColumns = Utility.calculateNoOfColumns(ExampleFullPageActivity.this, 180);
        rvExamplesFullPage.setLayoutManager(new GridLayoutManager(ExampleFullPageActivity.this, mNoOfColumns));
        rvExamplesFullPage.setAdapter(examplesAdapter);
    }

    private void getSampleSite() {
        String url = "http://work-android.ir/Seolution/sampleSite.php";
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
                        String link = object.getString("link");

                        examplesModel.add(new ExamplesModel(id, title, image, link));
                    }
                    examplesAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ExampleFullPageActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ExampleFullPageActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        StringRequest request = new StringRequest(Request.Method.GET, url, listener, errorListener);
        AppController.getInstance().addToRequestQueue(request);
    }
}
