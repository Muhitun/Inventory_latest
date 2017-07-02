package com.example.royex.inventoryapps;

/**
 * Created by royex on 6/22/17.
 */

public class InvoiceListDatatype {
    String product;
    String stockIn;
    String stockOut;
    String stock;

    public InvoiceListDatatype(){

    }


    public InvoiceListDatatype(String p, String stIn, String stOut, String stock){
        this.product = p;
        this.stockIn = stIn;
        this.stockOut = stOut;
        this.stock = stock;
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

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
}
