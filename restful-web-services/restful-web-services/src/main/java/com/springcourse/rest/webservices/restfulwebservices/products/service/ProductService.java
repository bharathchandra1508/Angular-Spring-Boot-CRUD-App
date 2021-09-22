package com.springcourse.rest.webservices.restfulwebservices.products.service;

import java.util.List;

import com.springcourse.rest.webservices.restfulwebservices.products.model.Product;

public interface ProductService {
	
	Product createProduct(Product product);
	Product updateProduct(Product product);
	List<Product> getAllProducts();
	Product getProductById(long productID);
	
}
