package com.hashem.workshopmidexam;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hashem.workshopmidexam.databinding.ItemProductBinding;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    List<Product> products;
    OnAdapterActionListener listener;

    public ProductAdapter(List<Product> products,OnAdapterActionListener listener) {
        this.products = products;
        this.listener = listener;

    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductBinding binding = ItemProductBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        int pos = position;
        Product p = products.get(position);
        holder.productNameTv.setText(p.getProductName());
        holder.productQuantityTv.setText(p.getProductQuantity());
        holder.productOfferTv.setText(String.valueOf(p.isProductOffer()));
        holder.deleteIc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDelete(pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView productNameTv,productQuantityTv,productOfferTv;
        ImageView deleteIc;

        public ProductViewHolder(ItemProductBinding binding) {
            super(binding.getRoot());
            productNameTv = binding.productNameTv;
            productQuantityTv = binding.productQuantityTv;
            productOfferTv = binding.productOfferTv;
            deleteIc = binding.deleteIc;
        }
    }
    public interface OnAdapterActionListener{
        void onDelete(int pos);
    }
}
