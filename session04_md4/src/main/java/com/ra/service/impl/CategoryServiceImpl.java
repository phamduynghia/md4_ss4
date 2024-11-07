package com.ra.service.impl;

import com.ra.model.dto.req.CategoryRequestDTO;
import com.ra.model.entity.Category;
import com.ra.repository.CategoryRepository;
import com.ra.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            throw new NoSuchElementException("ko có dm");
        }
        return categories;
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new NoSuchElementException("ko dm " + id));
    }

    @Override
    public Category addCategory(CategoryRequestDTO categoryRequestDTO) {
        if (categoryRepository.existsByCategoryName(categoryRequestDTO.getCategoryName())) {
            throw new NoSuchElementException("ko dm " + categoryRequestDTO.getCategoryName());
        }
        Category category = Category.builder()
                .categoryName(categoryRequestDTO.getCategoryName())
                .description(categoryRequestDTO.getDescription())
                .status(categoryRequestDTO.getStatus()).
                build();
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(CategoryRequestDTO categoryRequestDTO, Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NoSuchElementException("ko dm" + id));

        if (!category.getCategoryName().equals(categoryRequestDTO.getCategoryName())
                && categoryRepository.existsByCategoryName(categoryRequestDTO.getCategoryName())) {
            throw new NoSuchElementException("ko dm " + categoryRequestDTO.getCategoryName());
        }
        Category updatedCategory = Category.builder()
                .categoryName(categoryRequestDTO.getCategoryName())
                .description(categoryRequestDTO.getDescription())
                .status(categoryRequestDTO.getStatus())
                .build();
        updatedCategory.setId(id);
        return categoryRepository.save(updatedCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
