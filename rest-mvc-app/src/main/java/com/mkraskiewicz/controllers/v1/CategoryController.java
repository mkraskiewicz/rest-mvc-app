package com.mkraskiewicz.controllers.v1;

import com.mkraskiewicz.api.v1.model.CategoryDTO;
import com.mkraskiewicz.api.v1.model.CategoryListDTO;
import com.mkraskiewicz.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Maciej on 03/07/2018
 */
@RestController
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

    public static final String BASE_URL = "/api/v1/categories/";
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDTO getAllCategories(){

        return new CategoryListDTO(categoryService.getAllCategories());
    }

    @GetMapping("{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryByName(@PathVariable("name") String name){

        return categoryService.getCategoryByName(name);
    }
}
