package com.foodware.auth.entity;

import com.foodware.auth.user.UserRole;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Integer id;
    private String phoneNumber;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public User(String password, String phoneNumber, UserRole userRole) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.userRole = userRole;
    }
}


