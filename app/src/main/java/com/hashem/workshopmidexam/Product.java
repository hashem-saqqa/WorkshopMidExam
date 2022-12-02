package com.hashem.workshopmidexam;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public class Product {
    @PrimaryKey
    @NotNull
    String productName;
    String productQuantity;
    boolean productOffer;
    int productImage;

    public Product(String productName, String productQuantity, boolean productOffer, int productImage) {
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productOffer = productOffer;
        this.productImage = productImage;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    public boolean isProductOffer() {
        return productOffer;
    }

    public void setProductOffer(boolean productOffer) {
        this.productOffer = productOffer;
    }
}
