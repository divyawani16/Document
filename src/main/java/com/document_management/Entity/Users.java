package com.document_management.Entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Table(name = "Users")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId")
    private long UserId;


    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;


    @Column(name = "PhoneNumber")
    private String phoneNumber;


    @Column(name = "EmailId")
    private String emailId;

    @Column(name = "UserName")
    private String username;

    @Column(name = "Password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;


    public Users(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }


}
