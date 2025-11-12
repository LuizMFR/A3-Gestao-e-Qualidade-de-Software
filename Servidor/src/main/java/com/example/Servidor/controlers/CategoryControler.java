package com.example.Servidor.controlers;


import com.example.Servidor.entities.Category;
import com.example.Servidor.services.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoryControler {

    @Autowired
    private CategoryService categoryService;

    public CategoryControler (){
    }


    @GetMapping("/{userId}")
    public ResponseEntity<List<Category>> listForUser(@PathVariable int userId){
        if (categoryService.listForUser(userId).isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(categoryService.listForUser(userId));
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        if(categoryService.createCategory(category) == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(category);
        
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(
        @PathVariable Integer id, 
        @RequestBody Category category){

        if(categoryService.updateCategory(id, category) == null){
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok().body(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id){
        if(!categoryService.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        categoryService.deleteCategory(id);
        if(!categoryService.existsById(id)){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.internalServerError().build();
    }
}
