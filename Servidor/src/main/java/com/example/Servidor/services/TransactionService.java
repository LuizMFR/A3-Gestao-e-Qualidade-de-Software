package com.example.Servidor.services;

import com.example.Servidor.entities.Transaction;
import com.example.Servidor.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserService userService;

    public TransactionService(){
    }


    public boolean existsById(int id){
        return transactionRepository.existsById(id);
    }

    public List<Transaction> listAll(){
        return TransactionRepository.findAll();
    }

    public List<Transaction> listForUser(int userId){
        return transactionRepository.findByUsuarioID(userId);
    }

    public List<Transaction> listForCategory(int categoryId){
        return transactionRepository.findByCategoriaID(categoryId);
    }

    public List<Transaction> listForType(String type){
        return transactionRepository.findByType(type);
    }


    public Transaction createCategory(Transaction transaction){
        if(transaction.getUsuarioId() == null || !userService.existsById(transaction.getUsuarioId())){
            return null;
        }
        return transactionRepository.save(transaction);
    }

    public Transaction updateCategory(Integer id, Transaction newTransactionData){
        Transaction transaction = newTransactionData;
        transaction.setId(id);
        return transactionRepository.save(transaction);
    }

    public void deleteCategory(int id) {

        transactionRepository.deleteById(id);
    }


}
