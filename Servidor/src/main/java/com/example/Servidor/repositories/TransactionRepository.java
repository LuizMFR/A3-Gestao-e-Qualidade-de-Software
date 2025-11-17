package com.example.Servidor.repositories;


import com.example.Servidor.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findByUsuarioID(int userId);

    List<Transaction> findByCategoriaID(int categoryId);

    List<Transaction> findByType(String type);
}
