package com.example.Servidor.controlers;

import com.example.Servidor.entities.User;
import com.example.Servidor.services.UserService;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/usuarios")
public class UserControler{
    
    @Autowired
    private UserService userService;

    public UserControler(){
        
    }

    @GetMapping("/health")
    public ResponseEntity<Object> HealthCheck() {
        return ResponseEntity.ok().build();
    }



    @GetMapping()
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public boolean getUserById(@PathVariable Integer id){
        return userService.existsById(id);
    }

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user){

        return null;
    }

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User userDetails){
       
        return null;
    }
}
