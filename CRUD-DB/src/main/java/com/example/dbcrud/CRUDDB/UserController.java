package com.example.dbcrud.CRUDDB;

import com.example.dbcrud.CRUDDB.entity.UserInfo;
import com.example.dbcrud.CRUDDB.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepo userRepo;

    @PostMapping("/users")
    public ResponseEntity<UserInfo> addUser(@RequestBody UserInfo userInfo){
        return new ResponseEntity<>(userRepo.save(userInfo), HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserInfo>> getUsers(){
        return new ResponseEntity<>(userRepo.findAll(),HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserInfo> getUser(@PathVariable int id){
        Optional<UserInfo> userInfo = userRepo.findById(id);
        if(userInfo.isPresent()){
            return new ResponseEntity<>(userInfo.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserInfo> updateUser(@PathVariable int id ,@RequestBody UserInfo userInfo){
        Optional<UserInfo> userInfo1 = userRepo.findById(id);
        if(userInfo1.isPresent()){
            userInfo1.get().setName(userInfo.getName());
            userInfo1.get().setEmail(userInfo.getEmail());
            userInfo1.get().setAddress(userInfo.getAddress());
            return new ResponseEntity<>(userRepo.save(userInfo1.get()),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<UserInfo> deleteUser(@PathVariable int id){
        Optional<UserInfo> userInfo = userRepo.findById(id);
        if(userInfo.isPresent()){
            userRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
