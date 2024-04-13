package com.example.parsing_json_from_nested_object_into_recyclerview_using_retrofit_android.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.parsing_json_from_nested_object_into_recyclerview_using_retrofit_android.Interfaces.ProductAPI;
import com.example.parsing_json_from_nested_object_into_recyclerview_using_retrofit_android.Interfaces.RecyclerViewInterface;
import com.example.parsing_json_from_nested_object_into_recyclerview_using_retrofit_android.Model.Product;
import com.example.parsing_json_from_nested_object_into_recyclerview_using_retrofit_android.Adapters.ProductAdapter;
import com.example.parsing_json_from_nested_object_into_recyclerview_using_retrofit_android.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {
    private String BASE_URL = "https://fakestoreapi.com/";
    private List<Product> productList;
    private RecyclerView recyclerView;
    private Retrofit retrofit;

    public static final String DESCRIPTION = "description_details";
    public static final String URL = "image_details";
    public static final String PRICE = "price_details";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        productList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler);
        initRetrofit();
        getProductsFromAPI();
    }

    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void getProductsFromAPI() {
        ProductAPI productAPI = retrofit.create(ProductAPI.class);
        productAPI.getProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> products = response.body();
                for(Product product: products){
                    productList.add(product);
                }
                putDataInRecyclerView(productList);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable throwable) {

            }
        });

    }

    private void putDataInRecyclerView(List<Product> productList) {
        ProductAdapter productAdapter = new ProductAdapter(this, productList,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(productAdapter);
    }


    @Override
    public void onItemClicked(int position) {
        Intent intent = new Intent(MainActivity.this, ProductDetailsActivity.class);
        intent.putExtra(DESCRIPTION, productList.get(position).getDescription());
        intent.putExtra(URL, productList.get(position).getImage());
        intent.putExtra(PRICE, productList.get(position).getPrice() + "");
        startActivity(intent);
    }
}
