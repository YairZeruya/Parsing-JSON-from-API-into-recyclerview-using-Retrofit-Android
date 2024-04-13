package com.example.parsing_json_from_nested_object_into_recyclerview_using_retrofit_android.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parsing_json_from_nested_object_into_recyclerview_using_retrofit_android.Interfaces.RecyclerViewInterface;
import com.example.parsing_json_from_nested_object_into_recyclerview_using_retrofit_android.Model.Product;
import com.example.parsing_json_from_nested_object_into_recyclerview_using_retrofit_android.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private Context context;
    private List<Product> productList;
    private RecyclerViewInterface recyclerViewInterface;

    public ProductAdapter(Context context, List<Product> productList, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.productList = productList;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.price.setText("Price: " + product.getPrice() + " $");
        holder.title.setText("Title: " + product.getTitle());
        holder.rate.setText("Rate: " + product.getRating().getRate());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView price;
        private TextView rate;

        public ViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            rate = itemView.findViewById(R.id.rate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClicked(position);
                        }
                    }
                }
            });
        }
    }
}
