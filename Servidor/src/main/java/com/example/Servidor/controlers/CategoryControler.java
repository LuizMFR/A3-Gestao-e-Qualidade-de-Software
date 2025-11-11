package com.example.Servidor.controlers;


import com.example.Servidor.entities.Category;
import com.example.Servidor.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoryControler {
    private final CategoryService categoryService;

    public CategoryControler (CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> list(){
       return categoryService.listAll();
    }

    @GetMapping("/usuarios/userId")
    public List<Category> listForUser(@PathVariable int userId){
        return categoryService.listForUser(userId);
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category){
        return categoryService.saveCategory(category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable int id){
        categoryService.deleteCategory(id);
    }
}
