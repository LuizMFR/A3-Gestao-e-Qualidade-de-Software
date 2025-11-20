package com.example.servidor.controlers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.servidor.entities.Transaction;
import com.example.servidor.services.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class TransactionControler {

    @Autowired
    private TransactionService transactionService;

    public TransactionControler(){
    }


    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<Transaction>> listForUser(@PathVariable int userId){

        List<Transaction> transactions = transactionService.listForUser(userId);
        if (transactions.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(transactions);
    }

    @GetMapping("/categoria/{categoryId}")
    public ResponseEntity<List<Transaction>> listForCategory(@PathVariable int categoryId){

        List<Transaction> transactions = transactionService.listForCategory(categoryId);
        if (transactions.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(transactions);
    }

    @GetMapping("/tipo/{type}")
    public ResponseEntity<List<Transaction>> listForType(@PathVariable String type){

        List<Transaction> transactions = transactionService.listForType(type);
        if (transactions.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(transactions);
    }



    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction){
        if(transactionService.createTransaction(transaction) == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(transaction);
        
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(
        @PathVariable Integer id, 
        @RequestBody Transaction category){

        if(transactionService.updateTransaction(id, category) == null){
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok().body(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id){
        if(!transactionService.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        transactionService.deleteCategory(id);
        if(!transactionService.existsById(id)){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.internalServerError().build();
    }
}

