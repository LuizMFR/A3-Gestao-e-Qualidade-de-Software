package com.example.servidor.repositories;

import org.springframework.stereotype.Repository;

import com.example.servidor.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;

/*REPOSITÓRIO É ONDE ESTÁ OS COMANDOS SQL, NESTE CASO, JÁ ESTOU IMPLEMENTADOS PELO JPAREPOSITORY */

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

    boolean existsByEmailAndSenha(String email, String senha);
    
    boolean existsByEmail(String email);

    User findByEmailAndSenha(String email, String senha);

    User findByEmail(String email);
}
