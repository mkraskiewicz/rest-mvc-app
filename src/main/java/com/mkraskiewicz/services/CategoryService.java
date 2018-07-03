package com.mkraskiewicz.services;

import com.mkraskiewicz.api.v1.model.CategoryDTO;

import java.util.List;

/**
 * Created by Maciej on 03/07/2018
 */
public interface CategoryService {

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryByName(String name);
}
