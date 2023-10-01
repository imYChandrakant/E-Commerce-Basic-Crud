package com.nimap.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.nimap.entities.Category;
import com.nimap.repositories.CategoryRepository;
import com.nimap.responseWrapper.ResponseWrapper;



@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	ResponseWrapper responseWrapper = new ResponseWrapper();

	public ResponseEntity<?> addCategory(Category category) {

		Category addedData = categoryRepository.save(category);
		
		responseWrapper.setMessage("NEW CATEGORY ADDED SUCESSFULLY");
		responseWrapper.setData(addedData);
		return new ResponseEntity<>(responseWrapper, HttpStatus.OK);
	}

	public Page<Category> getAllCategories(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
       return categoryRepository.findAll(pageable);
    }
	
	public ResponseEntity<?> getCategoryById(int id){
		Category categoriesById = categoryRepository.findById(id).orElseThrow(()->{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"NO SUCH CATEGORY FOUND WITH THE GIVEN ID: "+id);
		});

		responseWrapper.setMessage("FOUND CATEGORY WITH GIVEN ID: "+id);
		responseWrapper.setData(categoriesById);
		return new ResponseEntity<>(responseWrapper, HttpStatus.FOUND);
	}
	
	public ResponseEntity<?> updateCategoryById(int id,Category category){
		getCategoryById(id);
		category.setId(id);
		Category updatedData = categoryRepository.save(category);
		
		responseWrapper.setMessage("CATEGORY DATA UPDATED SUCESSFULLY");
		responseWrapper.setData(updatedData);
		return new ResponseEntity<>(responseWrapper, HttpStatus.OK);
	}
	
	public String deleteCategoryById(int id){
		getCategoryById(id);
		categoryRepository.deleteById(id);
		return "CATEGORY GIVEN WITH ID "+id+" HAS BEEN DELETED SUCESSFULLY";
	}
	
}
