package com.example.servidor.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.servidor.entities.Transaction;
import com.example.servidor.repositories.TransactionRepository;

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
        return transactionRepository.findAll();
    }

    public List<Transaction> listForUser(int userId){
        return transactionRepository.findByUsuarioId(userId);
    }

    public List<Transaction> listForCategory(int categoryId){
        return transactionRepository.findByCategoriaId(categoryId);
    }

    public List<Transaction> listForType(String type){
        return transactionRepository.findByTipo(type);
    }


    public Transaction createTransaction(Transaction transaction){
        if(transaction.getUsuarioId() == null || !userService.existsById(transaction.getUsuarioId())){
            return null;
        }
        return transactionRepository.save(transaction);
    }

    public Transaction updateTransaction(Integer id, Transaction newTransactionData){
        Transaction transaction = newTransactionData;
        transaction.setId(id);
        return transactionRepository.save(transaction);
    }

    public void deleteCategory(int id) {

        transactionRepository.deleteById(id);
    }


}
