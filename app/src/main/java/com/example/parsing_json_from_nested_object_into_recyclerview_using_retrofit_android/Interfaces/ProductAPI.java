package com.example.parsing_json_from_nested_object_into_recyclerview_using_retrofit_android.Interfaces;

import com.example.parsing_json_from_nested_object_into_recyclerview_using_retrofit_android.Model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductAPI {
    @GET("products")
    Call<List<Product>> getProducts();
}
