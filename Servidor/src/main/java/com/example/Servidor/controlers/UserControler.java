package com.example.Servidor.controlers;

import com.example.Servidor.repositories.UserRepository;
import com.example.Servidor.entities.User;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;


@RestController
@RequestMapping("/usuarios")
public class UserControler{

    @Autowired
    private UserRepository userRepository;


    @GetMapping
    public List<User> listarUsuarios(){
        return userRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<User> criarUsuario(@RequestBody User user){
        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> atualizarUsuario(@PathVariable Integer id, @RequestBody User dados) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setNome(dados.getNome());
                    user.setSobrenome(dados.getSobrenome());
                    user.setEmail(dados.getEmail());
                    user.setSenha(dados.getSenha());
                    user.setProfissao(dados.getProfissao());
                    user.setNascimento(dados.getNascimento());

                    User updatedUser = userRepository.save(user);
                    return ResponseEntity.ok(updatedUser);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Integer id){
        if (!userRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
            }
    }

