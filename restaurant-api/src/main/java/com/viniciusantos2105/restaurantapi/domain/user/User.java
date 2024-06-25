package com.viniciusantos2105.restaurantapi.domain.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

    @Id
    private Long userId;
    @Column(name = "user_name")
    private String userFullName;
    @Column(name = "user_email", unique = true)
    private String userEmail;
    @Column(name = "user_password")
    private String userPassword;
    @Column(name = "user_type")
    private UserType userType;

    public User() {}

    public User(Long userId, String userFullName, String userEmail, String userPassword, UserType userType) {
        this.userId = userId;
        this.userFullName = userFullName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userType = userType;
    }

    public void setUserName(String userFullName) {
        this.userFullName = userFullName;
    }

    public void setUserType(String userType) {
        this.userType = UserType.fromString(userType);
    }


}
