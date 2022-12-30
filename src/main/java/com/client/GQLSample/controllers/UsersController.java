package com.client.GQLSample.controllers;

import com.client.GQLSample.dtos.UserDTO;
import com.client.GQLSample.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UsersController {
    private final UserService userService;

    @GetMapping("")
    public List<UserDTO> findAllUsers(){
        return userService.findAllUsers();
    }
    @GetMapping("/{id}")
    public UserDTO findUserById(@PathVariable int id){
        return userService.findById(id);
    }

    @PostMapping("")
    public ResponseEntity<?> addUser(@RequestBody UserDTO userDTO){
        userService.addUser(userDTO);
        return ResponseEntity.ok("Ok");
    }

}
