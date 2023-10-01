package com.nimap.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.nimap.entities.Product;
import com.nimap.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@PostMapping("")
	public ResponseEntity<?> addProduct(@RequestBody Product product) {
		return productService.addProduct(product);
	}

	@GetMapping("")
	public Page<Product> getAllProducts(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int pageSize) {
		return productService.getAllProducts(page, pageSize);
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<?> getProductById(@PathVariable int id) {
		return productService.getProductById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateProductById(@PathVariable int id, @RequestBody Product product) {
		return productService.updateProductById(id, product);
	}
	@DeleteMapping("/{id}")
	public String deleteProductById(@PathVariable int id){
		return productService.deleteProductById(id);
	}

}
