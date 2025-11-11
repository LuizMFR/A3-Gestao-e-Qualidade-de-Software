package com.example.Servidor.repositories;

import com.example.Servidor.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByUserId(int userId);
}
