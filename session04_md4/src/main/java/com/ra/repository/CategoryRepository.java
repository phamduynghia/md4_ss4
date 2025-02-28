package com.ra.repository;

import com.ra.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
Page<Category> findByCategoryNameContains(String categoryName, Pageable pageable);
boolean existsByCategoryName(String categoryName);
boolean existsById(Long id);
    Page<Category> findCategoriesByStatusTrue(Pageable pageable);
}
