package com.client.GQLSample.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserListResponse {
    private List<UserDTO> users;
}
