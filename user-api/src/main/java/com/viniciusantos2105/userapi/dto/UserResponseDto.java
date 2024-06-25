package com.viniciusantos2105.userapi.dto;

import com.viniciusantos2105.userapi.domain.user.UserType;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.viniciusantos2105.userapi.domain.user.User}
 */
@Getter
@Setter
public class UserResponseDto  {
    private String userFullName;
    private String userEmail;
    private  String userType;

    public void setUserType(UserType userType) {
        this.userType = userType.getDescription();
    }
}