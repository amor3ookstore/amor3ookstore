package com.amorbookstore.service.impl;

import com.amorbookstore.dto.CategoryDTO;
import com.amorbookstore.entity.Category;
import com.amorbookstore.exception.ResourceNotFoundException;
import com.amorbookstore.repository.CategoryRepository;
import com.amorbookstore.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private CategoryDTO mapToDTO(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    private Category mapToEntity(CategoryDTO dto) {
        return Category.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        return mapToDTO(category);
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        if (categoryRepository.existsByName(categoryDTO.getName())) {
            throw new RuntimeException("Category already exists");
        }
        Category category = mapToEntity(categoryDTO);
        Category saved = categoryRepository.save(category);
        return mapToDTO(saved);
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        category.setName(categoryDTO.getName());
        Category updated = categoryRepository.save(category);
        return mapToDTO(updated);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
