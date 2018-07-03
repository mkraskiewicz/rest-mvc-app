package com.mkraskiewicz.api.v1.mapper;

import com.mkraskiewicz.api.v1.model.CategoryDTO;
import com.mkraskiewicz.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Created by Maciej on 03/07/2018
 */
@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(source = "id", target = "id")
    CategoryDTO categoryToCategoryDTO(Category category);

    @Mapping(source = "id", target = "id")
    Category categoryDTOToCategory(CategoryDTO categoryDTO);
}
