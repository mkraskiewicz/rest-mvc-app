package com.mkraskiewicz.api.v1.mapper;

import com.mkraskiewicz.api.v1.model.CategoryDTO;
import com.mkraskiewicz.domain.Category;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Maciej on 03/07/2018
 */
public class CategoryMapperTest {

    public static final String NAME = "Maciej";
    public static final Long ID = Long.valueOf(1);
    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    public void categoryToCategoryDTO() {

        //given
        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);

        //when
        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        //then
        assertEquals(NAME,categoryDTO.getName());
        assertEquals(ID, categoryDTO.getId() );
    }

    @Test
    public void categoryDTOToCategory() {

        //given
        String name = "Maciej";
        Long id = Long.valueOf(1);
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(name);
        categoryDTO.setId(id);

        //when
        Category category = categoryMapper.categoryDTOToCategory(categoryDTO);

        //then
        assertEquals(name,category.getName());
        assertEquals(id, category.getId() );
    }
}