package com.viniciusantos2105.orderapi.domain.user;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class User {

    private UUID userId;
    private String userFullName;
    private String userEmail;
    private String userPassword;
    private UserType userType;

    public User() {
    }

    public User(UUID userId, String userFullName, String userEmail, String userPassword, UserType userType) {
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
