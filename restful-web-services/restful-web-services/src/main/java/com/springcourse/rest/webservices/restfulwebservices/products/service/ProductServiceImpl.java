package com.springcourse.rest.webservices.restfulwebservices.products.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springcourse.rest.webservices.restfulwebservices.products.exception.ResourceNotFoundException;
import com.springcourse.rest.webservices.restfulwebservices.products.model.Product;
import com.springcourse.rest.webservices.restfulwebservices.products.repository.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		Optional<Product> productDB = this.productRepository.findById(product.getId());
		if(productDB.isPresent()) {
			Product productUpdate = productDB.get();
			productUpdate.setId(product.getId());
			productUpdate.setName(product.getName());
			productUpdate.setDescription(product.getDescription());
			productUpdate.setPrice(product.getPrice());
			productUpdate.setCreateTimeStamp(product.getCreateTimeStamp());
			productRepository.save(productUpdate);
			return productUpdate;
		}
		else {
			throw new ResourceNotFoundException("Record does not exist with ID: " + product.getId());
		}
	}

	@Override
	public List<Product> getAllProducts() {
		return this.productRepository.findAll();
	}

	@Override
	public Product getProductById(long productID) {
		Optional<Product> productDB = this.productRepository.findById(productID);
		if(productDB.isPresent()) {
			return productDB.get();
		}
		else {
			throw new ResourceNotFoundException("Record does not exist with ID: " + productID);
		}
	}

}
