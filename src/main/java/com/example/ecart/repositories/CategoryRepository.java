package com.example.ecart.repositories;

import com.example.ecart.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {


    Category findByCategoryName(String categoryName);
}
