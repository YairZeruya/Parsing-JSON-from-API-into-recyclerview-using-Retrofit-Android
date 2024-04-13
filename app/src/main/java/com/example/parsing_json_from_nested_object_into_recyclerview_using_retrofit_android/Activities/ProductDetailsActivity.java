package com.example.parsing_json_from_nested_object_into_recyclerview_using_retrofit_android.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.parsing_json_from_nested_object_into_recyclerview_using_retrofit_android.R;

public class ProductDetailsActivity extends AppCompatActivity {

    private TextView descriptionTextView;
    private Button button;
    private String description;
    private String url;
    private String price;
    private ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_deatails);
        findViewByID();
        getDataFromIntent();
        refreshUI();
        backToIntent();
    }

    private void refreshUI() {
        descriptionTextView.setText(description + "Price: " + price + " $");
        Glide.with(this)
                .load(url)
                .into(imageView);
    }

    private void backToIntent() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductDetailsActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void getDataFromIntent() {
        // checking if the intent has extra
        if (getIntent().hasExtra(MainActivity.DESCRIPTION)) {
            description = getIntent().getStringExtra(MainActivity.DESCRIPTION);
            url = getIntent().getStringExtra(MainActivity.URL);
            price = getIntent().getStringExtra(MainActivity.PRICE);
        }
    }

    private void findViewByID() {
        descriptionTextView = findViewById(R.id.description);
        imageView = (ImageView) findViewById(R.id.imageView);
        button = findViewById(R.id.button);

    }
}
