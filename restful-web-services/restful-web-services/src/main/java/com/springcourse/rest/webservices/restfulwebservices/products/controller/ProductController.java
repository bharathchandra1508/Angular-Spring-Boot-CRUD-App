package com.springcourse.rest.webservices.restfulwebservices.products.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springcourse.rest.webservices.restfulwebservices.products.exception.ResourceNotFoundException;
import com.springcourse.rest.webservices.restfulwebservices.products.model.Product;
import com.springcourse.rest.webservices.restfulwebservices.products.repository.ProductRepository;
import com.springcourse.rest.webservices.restfulwebservices.products.service.ProductService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts(){
		return ResponseEntity.ok().body(productService.getAllProducts());
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable long id){
		return ResponseEntity.ok().body(productService.getProductById(id));
	}
	
	@PostMapping("/products")
	public ResponseEntity<Product> createProduct(@RequestBody Product product){
		return ResponseEntity.ok().body(this.productService.createProduct(product));
	}
	
	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody Product product){
		product.setId(id);
		return ResponseEntity.ok().body(this.productService.updateProduct(product));
	}
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable long id){
		Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product does not exist with id : "+id));
		productRepository.delete(product);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}
