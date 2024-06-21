package com.viniciusantos2105.userapi.config.security;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.core.Authentication;

import java.util.function.Supplier;

public class SecurityExpressionConfigurer extends SecurityExpressionRoot {
    public SecurityExpressionConfigurer(Authentication authentication) {
        super(authentication);
    }

    public SecurityExpressionConfigurer(Supplier<Authentication> authentication) {
        super(authentication);
    }

    @Override
    public void setDefaultRolePrefix(String defaultRolePrefix) {
        super.setDefaultRolePrefix(defaultRolePrefix);
    }
}
