package com.mkraskiewicz.controllers.v1;

import com.mkraskiewicz.api.v1.model.CategoryDTO;
import com.mkraskiewicz.api.v1.model.CategoryListDTO;
import com.mkraskiewicz.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Maciej on 03/07/2018
 */
@Controller
@RequestMapping("/api/v1/categories/")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<CategoryListDTO> getAllCategories(){

        return new ResponseEntity<CategoryListDTO>(new CategoryListDTO(categoryService.getAllCategories()),
                HttpStatus.OK);
    }

    @GetMapping("{name}")
    public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable("name") String name){

        return new ResponseEntity<CategoryDTO>(categoryService.getCategoryByName(name),
                HttpStatus.OK);
    }
}
