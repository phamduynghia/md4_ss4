package com.ra.controller.admin;

import com.ra.model.dto.req.CategoryRequestDTO;
import com.ra.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/category")
@RequiredArgsConstructor
public class CategoryController {
@Autowired
    private final CategoryService categoryService;

@GetMapping
public ResponseEntity<?> getAllCategory() {
return new ResponseEntity<>(categoryService.getAllCategory(), HttpStatus.OK);
}

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable("id") Long id) {
return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
    }

@PostMapping
    public ResponseEntity<?> addCategory(@RequestBody CategoryRequestDTO categoryRequestDTO) {
    return new ResponseEntity<>(categoryService.addCategory(categoryRequestDTO), HttpStatus.OK);
}

@PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@Valid @PathVariable("id") Long id, @RequestBody CategoryRequestDTO categoryRequestDTO) {
    return new ResponseEntity<>(categoryService.updateCategory(categoryRequestDTO, id), HttpStatus.OK);
}

@DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id) {
    categoryService.deleteCategory(id);
    return new ResponseEntity<>(HttpStatus.OK);
}

}
