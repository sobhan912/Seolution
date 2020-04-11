package com.example.timetochange.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.timetochange.R;
import com.example.timetochange.RecyclersView.Products.ProductsAdapter;
import com.example.timetochange.RecyclersView.Products.ProductsModel;
import com.example.timetochange.Utils.AppController;
import com.example.timetochange.Utils.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductFullPageActivity extends AppCompatActivity {

    RecyclerView rvProductFullPage;
    ProductsAdapter productsAdapter;
    List<ProductsModel> productsModel = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_full_page);
        init();
        setUpRecyclerViewProducts();
        getProducts();
    }

    private void init() {
        rvProductFullPage = findViewById(R.id.rvProductFullPage);
    }

    private void setUpRecyclerViewProducts() {
        productsAdapter = new ProductsAdapter(productsModel, ProductFullPageActivity.this);
        int mNoOfColumns = Utility.calculateNoOfColumns(ProductFullPageActivity.this, 180);
        rvProductFullPage.setLayoutManager(new GridLayoutManager(ProductFullPageActivity.this, mNoOfColumns));
        rvProductFullPage.setAdapter(productsAdapter);
    }

    private void getProducts() {
        String url = "http://work-android.ir/Seolution/products.php";
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
                        String text = object.getString("text");
                        String price = object.getString("price");
                        String link = object.getString("link");

                        productsModel.add(new ProductsModel(id, title, price, image, text, link));
                    }
                    productsAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ProductFullPageActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ProductFullPageActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        StringRequest request = new StringRequest(Request.Method.GET, url, listener, errorListener);
        AppController.getInstance().addToRequestQueue(request);
    }
}
