package com.springcourse.rest.webservices.restfulwebservices.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springcourse.rest.webservices.restfulwebservices.products.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
