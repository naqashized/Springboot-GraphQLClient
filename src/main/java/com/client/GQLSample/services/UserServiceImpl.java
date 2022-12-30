package com.client.GQLSample.services;

import com.client.GQLSample.dtos.AddUserResponse;
import com.client.GQLSample.dtos.UserDTO;
import com.client.GQLSample.dtos.UserListResponse;
import com.client.GQLSample.dtos.UserResponse;
import com.netflix.graphql.dgs.client.CustomGraphQLClient;
import com.netflix.graphql.dgs.client.GraphQLResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final CustomGraphQLClient customGraphQLClient;

    @Override
    public List<UserDTO> findAllUsers() {
        String query = "query{\n" +
                "  users{\n" +
                "    id name address\n" +
                "  }\n" +
                "}";
        Map<String, ?> variables = new HashMap<>();
        GraphQLResponse graphQLResponse =  customGraphQLClient.executeQuery(query,variables);
        UserListResponse userResponse = graphQLResponse.dataAsObject(UserListResponse.class);
        return userResponse.getUsers();
    }

    @Override
    public UserDTO findById(int id) {
        String query ="query findUserById($id : Int!){\n" +
                "  user(id:$id){\n" +
                "    id name address dob\n" +
                "  }\n" +
                "}";
        Map<String, Object> variables = new HashMap<>();
        variables.put("id", id);
        GraphQLResponse graphQLResponse =  customGraphQLClient.executeQuery(query,variables);
        UserResponse userResponse = graphQLResponse.dataAsObject(UserResponse.class);
        return userResponse.getUser();
    }

    @Override
    public void addUser(UserDTO userDTO) {
        String mutation ="mutation addUser($input: UserInput!){\n" +
                "addUser( input:$input ){\n" +
                "  id name\n" +
                "}\n" +
                "}";
        Map<String, Object> variables = new HashMap<>();
        variables.put("input", userDTO);
        GraphQLResponse graphQLResponse =  customGraphQLClient.executeQuery(mutation,variables);
        AddUserResponse addUserResponse = graphQLResponse.dataAsObject(AddUserResponse.class);

    }
}
