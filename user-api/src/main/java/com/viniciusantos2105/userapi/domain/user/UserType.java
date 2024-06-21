package com.viniciusantos2105.userapi.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum UserType implements GrantedAuthority {

    OWNER("Dono"),
    CLIENT("Cliente");

    private final String description;

    @Override
    public String getAuthority() {
        return "ROLE_" + description + ".";
    }

    public static UserType fromString(String userType) {
        return Arrays.stream(UserType.values())
                .filter(value -> value.description.equalsIgnoreCase(userType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Tipo de usuario invalido"));
    }
}
