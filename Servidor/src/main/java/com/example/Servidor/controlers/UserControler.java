package com.example.Servidor.controlers;

import com.example.Servidor.repositories.UserRepository;
import com.example.Servidor.entities.User;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UserControler{
    
    private UserRepository userRepository;

    public UserControler(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id){
        return userRepository.findById(id).orElse(null);
    }

    @PostMapping("")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User saved = userRepository.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(location).body(saved);
    }

    @RequestMapping("/update/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User userDetails){
        User user = userRepository.findById(id).orElse(null);
        if(user != null){
            user.setNome(userDetails.getNome());
            user.setEmail(userDetails.getEmail());
            return userRepository.save(user);
        }
        return null;
    }
}
