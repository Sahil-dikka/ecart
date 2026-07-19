package com.example.ecart.service;

import com.example.ecart.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    void createCategory(Category category);

    String deleteCategory(long id);

    String updateCategory(long id,Category category);
}

