package com.document_management.Entity;
//import com.document_management.CustomValidation.PhoneNoValidation;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
@Entity
@Data
@Table(name = "Users")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId")
    private Integer userId;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "Username")
    private String username;

    @Column(name = "Password")
    private String password;

    @Column(name = "Phonenumber")
    private String phoneNumber;

    @Column(name = "EmailId")
    private String emailId;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "UserId")
//    private long UserId;
//
//    @Column(name = "FirstName")
//    private String firstName;
//
//    @Column(name = "LastName")
//    private String lastName;
//
//   //@PhoneNoValidation
//    @Column(name = "PhoneNumber")
//    private String phoneNumber;
//
//
//    @Column(name = "EmailId")
//    private String emailId;
//
//    @Column(name = "UserName")
//    private String username;
//
//    @Column(name = "Password")
//    private String password;



}
