package com.mkraskiewicz.services;

import com.mkraskiewicz.api.v1.mapper.CategoryMapper;
import com.mkraskiewicz.api.v1.model.CategoryDTO;
import com.mkraskiewicz.domain.Category;
import com.mkraskiewicz.repositories.CategoryRepository;
import com.mkraskiewicz.services.impl.CategoryServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by Maciej on 03/07/2018
 */
public class CategoryServiceTest {

    public static final Long ID = 3L;
    public static final String NAME = "mkraskiewicz";
    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE,categoryRepository);
    }

    @Test
    public void getAllCategories() {

        //given
        List<Category> categoryList = Arrays.asList(new Category(), new Category(), new Category());
        when(categoryRepository.findAll()).thenReturn(categoryList);

        //when
        List<CategoryDTO> returnedCategoryList = categoryService.getAllCategories();

        //then
        assertEquals(3, returnedCategoryList.size());

    }

    @Test
    public void getCategoryByName() {

        //given
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);
        when(categoryRepository.findByName(anyString())).thenReturn(category);

        //when
        CategoryDTO returnedCategoryDTO = categoryService.getCategoryByName(anyString());

        //then
        assertEquals(NAME,returnedCategoryDTO.getName());
        assertEquals(ID, returnedCategoryDTO.getId() );

    }
}