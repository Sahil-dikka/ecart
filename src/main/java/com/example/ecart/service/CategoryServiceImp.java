package com.example.ecart.service;

import com.example.ecart.model.Category;
import com.example.ecart.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService{

    //private List<Category> categories = new ArrayList<>();
    private Long nextID= 1L;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category) {
        //category.setCategoryId(nextID++);
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(long id) {

        Category deletedCategory = categoryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Category does not exists"));

        categoryRepository.delete(deletedCategory);
        return "Category deleted ";
    }

    public String updateCategory(long id,Category category){


        Category savedCategory = categoryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Category does not exists"));

        category.setCategoryId(id);
        savedCategory = categoryRepository.save(category);
        return "Category Updated Successfully";
    }
}
