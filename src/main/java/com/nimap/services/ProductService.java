package com.nimap.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import com.nimap.entities.Category;
import com.nimap.entities.Product;
import com.nimap.repositories.CategoryRepository;
import com.nimap.repositories.ProductRepository;
import com.nimap.responseWrapper.ResponseWrapper;


@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;

	@Autowired
	CategoryRepository categoryRepository;
	
	ResponseWrapper responseWrapper = new ResponseWrapper();

	public ResponseEntity<?> addProduct(Product product) {

		String category = product.getCategories().getCategoryName();
		
		Category foundedCategory = categoryRepository.findByCategoryName(category).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "PROVIDED CATEGORY " + category + " DOES NOT EXIST");
		});
		
		product.setCategories(foundedCategory);
		Product addedProduct = productRepository.save(product);
		
		responseWrapper.setMessage("NEW PRODUCT ADDED SUCESSFULLY");
		responseWrapper.setData(addedProduct);
		return new ResponseEntity<>(responseWrapper, HttpStatus.OK);
	}
	
	public Page<Product> getAllProducts(int page, int pageSize) {
		Pageable pageable = PageRequest.of(page - 1, pageSize);
		return productRepository.findAll(pageable);
	}

	public ResponseEntity<?> getProductById(int id) {
		Product productById = productRepository.findById(id).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NO SUCH PRODUCT FOUND WITH THE GIVEN ID: "+id);
		});
		
		responseWrapper.setMessage("FOUND PRODUCT WITH GIVEN ID: "+id);
		responseWrapper.setData(productById);
		return new ResponseEntity<>(responseWrapper, HttpStatus.FOUND);
	}

	public ResponseEntity<?> updateProductById(int id, Product product) {
		getProductById(id);
		product.setId(id);
		String category = product.getCategories().getCategoryName();
		Category foundedCategory = categoryRepository.findByCategoryName(category).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "PROVIDED CATEGORY " + category + " DOES NOT EXIST");
		});
		product.setCategories(foundedCategory);
		Product updatedData = productRepository.save(product);
		
		responseWrapper.setMessage("PRODUCT UPDATED SUCESSFULLY");
		responseWrapper.setData(updatedData);
		return new ResponseEntity<>(responseWrapper, HttpStatus.OK);
	}

	public String deleteProductById(int id) {
		getProductById(id);
		productRepository.deleteById(id);
		return "PRODUCT GIVEN WITH ID "+id+" HAS BEEN DELETED SUCESSFULLY";
	}

}
