package com.example.ecart.service;

import com.example.ecart.exceptions.APIExceptions;
import com.example.ecart.exceptions.ResourceNotFoundException;
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

        List<Category> result =  categoryRepository.findAll();
        if(result.isEmpty()){
            throw new APIExceptions("No Category Exists");
        }
        return result;
    }

    @Override
    public void createCategory(Category category) {
        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if(savedCategory != null){
            throw new APIExceptions("Category with the name " + category.getCategoryName() + " already exists !!");
        }
        categoryRepository.save(category);
    }


    @Override
    public String deleteCategory(long id) {

        Category deletedCategory = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category","Category Id",id));

        categoryRepository.delete(deletedCategory);
        return "Category deleted ";
    }

    public String updateCategory(long id,Category category){


        Category savedCategory = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category","Category Id",id));

        category.setCategoryId(id);
        savedCategory = categoryRepository.save(category);
        return "Category Updated Successfully";
    }


}
