package com.client.GQLSample.services;

import com.client.GQLSample.dtos.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> findAllUsers();
    UserDTO findById(int id);
    void addUser(UserDTO userDTO);
}
