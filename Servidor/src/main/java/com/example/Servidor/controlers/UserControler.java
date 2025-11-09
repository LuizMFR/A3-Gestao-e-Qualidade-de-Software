package com.example.Servidor.controlers;

import com.example.Servidor.entities.User;
import com.example.Servidor.services.UserService;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

/*CAMADA QUE RECEBE LIDA COM AS REQUISIÇÕES, VAI RECEBER AS REQUISIÇÕES, CHAMAR O SERVICE 
 * E COM BASE NA RESPOSTA DO SERVICE RETORNAR ALGO OU UM STATUS CODE DE ERRO
 */

@RestController
@RequestMapping("/usuarios")
public class UserControler{
    
    @Autowired
    private UserService userService;

        public UserControler(){
        
    }



    @GetMapping()
    private List<User> getUsers(){
        return userService.getAllUsers();
    }

    private User getUserById(Integer id){
        return userService.getUserById(id);
    }

    @GetMapping("/{id}")
    public boolean userIdExists(@PathVariable Integer id){
        return userService.existsById(id);
    }

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user){
        
        if(userService.createUser(user) == null){
           return ResponseEntity.badRequest().build(); 
        }

        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User newUserData){
       
        if (userService.updateUser(id, newUserData) == null){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().body(newUserData);
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id){
        if (!userService.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        userService.deleteUser(id);
        if(!userService.existsById(id)){
            return ResponseEntity.noContent().build();
            }
        
        return ResponseEntity.internalServerError().build();
    }
}
