package com.example.Servidor.services;

import com.example.Servidor.entities.Category;
import com.example.Servidor.repositories.CategoryRepository;
import com.example.Servidor.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserService userService;

    public CategoryService(){
    }


    public boolean existsById(int id){
        return categoryRepository.existsById(id);
    }

    public List<Category> listAll(){
        return categoryRepository.findAll();
    }

    public List<Category> listForUser(int userId){
        return categoryRepository.findByUsuarioID(userId);
    }

    public Category createCategory(Category category){
        if(category.getUsuarioID() == null || !userService.existsById(category.getUsuarioID())){
            return null;
        }
        return categoryRepository.save(category);
    }

    public Category updateCategory(Integer id, Category newCategoryData){
        Category category = newCategoryData;
        category.setId(id);
        return categoryRepository.save(category);
    }

    public void deleteCategory(int id) {

        categoryRepository.deleteById(id);
    }


}
