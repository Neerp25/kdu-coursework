package com.example.assement2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "User")
public class User {
    @Id
    Integer Userid;
    String firstname;
    String lastname;
    Constant Role;
    String email;
    String passward;

}
