package com.example.Servidor.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Servidor.entities.User;

/*REPOSITÓRIO É ONDE ESTÁ OS COMANDOS SQL, NESTE CASO, JÁ ESTOU IMPLEMENTADOS PELO JPAREPOSITORY */

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

    boolean existsByEmailAndSenha(String email, String senha);
    
    boolean existsByEmail(String email);
}
