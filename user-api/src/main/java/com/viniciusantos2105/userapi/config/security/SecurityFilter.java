package com.viniciusantos2105.userapi.config.security;

import com.viniciusantos2105.userapi.domain.user.User;
import com.viniciusantos2105.userapi.domain.user.UserRepositoryImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    private final UserRepositoryImpl userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = recoverToken(request);
        if (token != null) {
            var subject = tokenService.validarToken(token);
            User user = userRepository.findUserByEmail(subject);
            if(user != null) {
                var autenticar = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                configurer(autenticar);
            }
        }
        filterChain.doFilter(request, response);
    }

    private void configurer(Authentication autenticar) {
        SecurityContextHolder.getContext().setAuthentication(autenticar);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityExpressionConfigurer configurer = new SecurityExpressionConfigurer(authentication);
        configurer.setDefaultRolePrefix("");
    }

    private String recoverToken(HttpServletRequest request)  {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}