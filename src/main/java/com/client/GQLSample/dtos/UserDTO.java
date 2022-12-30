package com.client.GQLSample.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
    private int id;
    private String name;
    private String address;
    private String dob;
}
