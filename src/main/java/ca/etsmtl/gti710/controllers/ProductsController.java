package ca.etsmtl.gti710.controllers;

import java.util.ArrayList;
import ca.etsmtl.gti710.exceptions.ProductNotFoundException;
import ca.etsmtl.gti710.models.Product;
import ca.etsmtl.gti710.models.ProductList;
import ca.etsmtl.gti710.providers.IProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class ProductsController {

	@Autowired
	IProvider provider;

    ProductList productList = new ProductList(30);

	@RequestMapping("/products")
	public @ResponseBody ArrayList<Product> products() {
        ArrayList<Product> list = productList.getProducList();
        if (list == null) {
            list = provider.getProducts();
            productList.setProductList(list);
        }

		return list;
	}

	@RequestMapping("/products/{product_id}")
	public Product product(@PathVariable("product_id") int product_id) {
		try {
			return provider.getProduct(product_id);
		} catch (NullPointerException e) {
			throw new ProductNotFoundException();
		}
	}
}