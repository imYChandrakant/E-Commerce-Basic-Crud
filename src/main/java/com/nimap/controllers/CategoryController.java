package com.nimap.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nimap.entities.Category;
import com.nimap.services.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
    private CategoryService categoryService;
    
    
    @PostMapping("")
    public ResponseEntity<?> addCategory(@RequestBody Category category){
    	return categoryService.addCategory(category);
    }
    
    @GetMapping("")
    public ResponseEntity<Page<Category>> getAllCategories(@RequestParam(defaultValue = "1") int page,
    	    @RequestParam(defaultValue = "10") int pageSize){
    	Page<Category> categories = categoryService.getAllCategories(page, pageSize);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable int id){
    	return categoryService.getCategoryById(id);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategoryById(@PathVariable int id,@RequestBody Category category){
    	return categoryService.updateCategoryById(id, category);
    }
    @DeleteMapping("/{id}")
    public String deleteCategoryById(@PathVariable int id){
    	return categoryService.deleteCategoryById(id);
    }

}
