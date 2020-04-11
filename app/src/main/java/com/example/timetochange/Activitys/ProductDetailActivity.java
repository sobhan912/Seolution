package com.example.timetochange.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.example.timetochange.R;
import com.example.timetochange.RecyclersView.ProductDetail.ProductDetailAdapter;
import com.example.timetochange.RecyclersView.Products.ProductsAdapter;
import com.example.timetochange.RecyclersView.Products.ProductsModel;
import com.example.timetochange.Utils.AppController;
import com.example.timetochange.Utils.Utility;
import com.makeramen.roundedimageview.RoundedImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailActivity extends AppCompatActivity {

    RoundedImageView imageProductDetail;
    TextView titleProductDetail, btnBuy, textProductDetail;
    RecyclerView rvSimilarProduct;
    List<ProductsModel> productsModel = new ArrayList<>();
    ProductDetailAdapter productsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        init();
        getProductIntent();
        setUpRecyclerViewProducts();
        getProducts();
    }

    private void init() {
        imageProductDetail = findViewById(R.id.imageProductDetail);
        titleProductDetail = findViewById(R.id.titleProductDetail);
        btnBuy = findViewById(R.id.btnBuy);
        textProductDetail = findViewById(R.id.textProductDetail);
        rvSimilarProduct = findViewById(R.id.rvSimilarProduct);
    }

    private void getProductIntent() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String image = intent.getStringExtra("image");
        String text = intent.getStringExtra("text");
        String price = intent.getStringExtra("price");
        String link = intent.getStringExtra("link");

        titleProductDetail.setText(title);
        Glide.with(ProductDetailActivity.this).load(image).into(imageProductDetail);
        textProductDetail.setText(text);
        btnBuy.setText("خرید دوره با قیمت: " + price);
    }

    private void setUpRecyclerViewProducts() {
        productsAdapter = new ProductDetailAdapter(productsModel, ProductDetailActivity.this);
        rvSimilarProduct.setLayoutManager(new LinearLayoutManager(ProductDetailActivity.this, LinearLayoutManager.HORIZONTAL, true));
        rvSimilarProduct.setAdapter(productsAdapter);
    }

    private void getProducts() {
        String url = "http://work-android.ir/Seolution/products_main.php";
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
                    Toast.makeText(ProductDetailActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ProductDetailActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        StringRequest request = new StringRequest(Request.Method.GET, url, listener, errorListener);
        AppController.getInstance().addToRequestQueue(request);
    }
}
