package ca.etsmtl.gti710.models;

import java.util.ArrayList;
import java.util.Date;

public class ProductList {

    private ArrayList<Product> productList;
    private Date cache;

    public ProductList(int cacheTime) {

        cache = new Date(System.currentTimeMillis() + (cacheTime * 60000));
    }

    public ArrayList<Product> getProducList() {
        final Date date = new Date();
        if (date.after(cache)) {

            productList = null;
        }
        return productList;
    }

    public void setProductList(ArrayList<Product> list) {
        productList = list;
    }
}
