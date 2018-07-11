package com.mkraskiewicz.services.impl;

import com.mkraskiewicz.api.v1.mapper.CategoryMapper;
import com.mkraskiewicz.api.v1.model.CategoryDTO;
import com.mkraskiewicz.repositories.CategoryRepository;
import com.mkraskiewicz.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Maciej on 03/07/2018
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> categoryMapper.categoryToCategoryDTO(category))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {

        return categoryMapper.categoryToCategoryDTO(categoryRepository.findByName(name));
    }
}
