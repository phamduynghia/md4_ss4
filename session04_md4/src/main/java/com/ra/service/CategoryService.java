package com.ra.service;

import com.ra.model.dto.req.CategoryRequestDTO;
import com.ra.model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategory();
    Category getCategoryById(Long id);
    Category addCategory(CategoryRequestDTO categoryRequestDTO);
    Category updateCategory(CategoryRequestDTO categoryRequestDTO, Long id);
    void deleteCategory(Long id);
}
