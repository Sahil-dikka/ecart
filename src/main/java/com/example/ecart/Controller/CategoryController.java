package com.example.ecart.Controller;

import com.example.ecart.model.Category;
import com.example.ecart.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {

    public CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/api/public/category")
    public ResponseEntity<List<Category>> getAllCategries(){

        List<Category> lst = categoryService.getAllCategories();
        return new ResponseEntity<>(lst,HttpStatus.OK);
    }

    @PostMapping("api/public/category")
    public ResponseEntity<String> createCategory(@Valid @RequestBody Category category){
        categoryService.createCategory(category);
        return new ResponseEntity<>("Category created successfully",HttpStatus.CREATED);
    }

    @DeleteMapping("api/admin/category/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable long id){
            String result  = categoryService.deleteCategory(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("api/admin/category/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable long id,@RequestBody Category category){
            String result = categoryService.updateCategory(id,category);
            return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
