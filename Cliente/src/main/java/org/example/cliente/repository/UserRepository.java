package org.example.cliente.repository;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    
    public boolean validarCredenciais(String email, String senha) {
        return true;   
    }
    
}
