package com.example.timetochange.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.timetochange.Activitys.ExampleFullPageActivity;
import com.example.timetochange.Activitys.ProductFullPageActivity;
import com.example.timetochange.R;
import com.example.timetochange.RecyclersView.Categorys.CategoryAdapter;
import com.example.timetochange.RecyclersView.Categorys.CategoryModel;
import com.example.timetochange.RecyclersView.Examples.ExamplesAdapter;
import com.example.timetochange.RecyclersView.Examples.ExamplesModel;
import com.example.timetochange.RecyclersView.Products.ProductsAdapter;
import com.example.timetochange.RecyclersView.Products.ProductsModel;
import com.example.timetochange.Utils.AppController;
import com.example.timetochange.Utils.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    RecyclerView rvCategory, rvExamples, rvProducts;
    TextView tvShoar, btnMoreProduct, btnMoreExamples;
    List<CategoryModel> categories = new ArrayList<>();
    List<ExamplesModel> examplesModel = new ArrayList<>();
    List<ProductsModel> productsModel = new ArrayList<>();
    CategoryAdapter categoryAdapter;
    ExamplesAdapter examplesAdapter;
    ProductsAdapter productsAdapter;
    ProgressDialog progressDialog;

    public String text;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        init(rootView);
        setListeners();
        categories.clear();
        examplesModel.clear();
        productsModel.clear();
        setUpRecyclerViewCategory();
        setUpRecyclerViewExamples();
        setUpRecyclerViewProducts();
        getShoar();
        getSampleSite();
        getProducts();
        tvShoar.setText(text);
        return rootView;
    }

    private void init(View rooView) {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("لطفا منتظر بمانید ...");
        progressDialog.show();
        tvShoar = rooView.findViewById(R.id.tvShoar);
        rvExamples = rooView.findViewById(R.id.rvExamples);
        rvCategory = rooView.findViewById(R.id.rvCategory);
        rvProducts = rooView.findViewById(R.id.rvProducts);
        btnMoreProduct = rooView.findViewById(R.id.btnMoreProduct);
        btnMoreExamples = rooView.findViewById(R.id.btnMoreExamples);
    }

    private void setListeners() {
        btnMoreProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ProductFullPageActivity.class));
            }
        });
        btnMoreExamples.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ExampleFullPageActivity.class));
            }
        });
    }

    private void setUpRecyclerViewCategory() {
        categoryAdapter = new CategoryAdapter(categories, getActivity());
        rvCategory.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        rvCategory.setAdapter(categoryAdapter);

        categories.add(new CategoryModel("آموزش حضوری", ""));
        categories.add(new CategoryModel("آموزش غیر حضوری", ""));
        categories.add(new CategoryModel("آموزش آنلاین", ""));
        categories.add(new CategoryModel("هاست", ""));
        categories.add(new CategoryModel("همایش", ""));
        categories.add(new CategoryModel("کتاب", ""));
        categories.add(new CategoryModel("کسب و کار اینترنتی", ""));
    }

    private void setUpRecyclerViewExamples() {
        examplesAdapter = new ExamplesAdapter(examplesModel, getActivity());
        rvExamples.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        rvExamples.setAdapter(examplesAdapter);
    }

    private void setUpRecyclerViewProducts() {
        productsAdapter = new ProductsAdapter(productsModel, getActivity());
        int mNoOfColumns = Utility.calculateNoOfColumns(getActivity(), 180);
        rvProducts.setLayoutManager(new GridLayoutManager(getActivity(), mNoOfColumns));
        rvProducts.setAdapter(productsAdapter);
    }

    private void getShoar() {
        String url = "http://work-android.ir/Seolution/shoar.php";
        Response.Listener listener = new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                try {
                    JSONArray jsonArray = new JSONArray(response.toString());
                    JSONObject object = jsonArray.getJSONObject(0);
                    tvShoar.setText(object.getString("text"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        StringRequest request = new StringRequest(Request.Method.GET, url, listener, errorListener);
        AppController.getInstance().addToRequestQueue(request);
    }

    private void getSampleSite() {
        String url = "http://work-android.ir/Seolution/sampleSite_main.php";
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
                    Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        StringRequest request = new StringRequest(Request.Method.GET, url, listener, errorListener);
        AppController.getInstance().addToRequestQueue(request);
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
                        progressDialog.dismiss();
                    }
                    productsAdapter.notifyDataSetChanged();
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
