package com.example.royex.inventoryapps;

/**
 * Created by royex on 6/22/17.
 */

public class AddProductDatatype {
    String product;
    String stockIn;
    String stockOut;


    public AddProductDatatype(){

    }


    public AddProductDatatype(String p, String stIn, String stOut){
        this.product = p;
        this.stockIn = stIn;
        this.stockOut = stOut;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getStockIn() {
        return stockIn;
    }

    public void setStockIn(String stockIn) {
        this.stockIn = stockIn;
    }

    public String getStockOut() {
        return stockOut;
    }

    public void setStockOut(String stockOut) {
        this.stockOut = stockOut;
    }


}
