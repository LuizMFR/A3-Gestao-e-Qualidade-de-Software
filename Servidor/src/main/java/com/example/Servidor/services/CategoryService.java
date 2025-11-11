package com.example.Servidor.services;

import com.example.Servidor.entities.Category;
import com.example.Servidor.repositories.CategoryRepository;
import com.example.Servidor.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public CategoryService(CategoryRepository categoryRepository, UserRepository userRepository){
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public List<Category> listAll(){
        return categoryRepository.findAll();
    }

    public List<Category> listForUser(int userId){
        return categoryRepository.findByUserId(userId);
    }

    public Category saveCategory(Category category){
        if(category.getUser() == null || !userRepository.existsById(category.getUser().getId())){
            throw new  IllegalArgumentException("Usuário inválido para categoria.");
        }
        return categoryRepository.save(category);
    }

    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }


}
