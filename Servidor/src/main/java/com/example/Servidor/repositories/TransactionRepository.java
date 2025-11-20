package com.example.servidor.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.servidor.entities.Transaction;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findByUsuarioId(int userId);

    List<Transaction> findByCategoriaId(int categoryId);

    List<Transaction> findByTipo(String type);
}
