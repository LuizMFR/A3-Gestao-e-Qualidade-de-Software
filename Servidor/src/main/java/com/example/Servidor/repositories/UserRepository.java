package com.example.Servidor.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Servidor.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
    
}
