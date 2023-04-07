package com.document_management.DTO;


import lombok.Data;

@Data
public class UserDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailId;
    private String username;
    private String password;
    private Long roleId;
}