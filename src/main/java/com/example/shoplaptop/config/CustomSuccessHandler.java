package com.example.shoplaptop.config;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.shoplaptop.domain.User;
import com.example.shoplaptop.service.UserService;

import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.DefaultRedirectStrategy;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    protected String determineTargetUrl(Authentication authentication) {
        
        Map<String, String> roleTargetUrlMap = new HashMap<>();
        roleTargetUrlMap.put("ROLE_USER", "/");
        roleTargetUrlMap.put("ROLE_ADMIN", "/admin");
        
        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            String authorityName = grantedAuthority.getAuthority();
            if (roleTargetUrlMap.containsKey(authorityName)) {
                return roleTargetUrlMap.get(authorityName);
            }
        }

        throw new IllegalStateException();
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request , Authentication authentication) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

        String email = authentication.getName();
        User user = userService.getUserByEmail(email);
        if(user != null) {
            session.setAttribute("name", user.getName());
            session.setAttribute("id", user.getId());
            session.setAttribute("email", user.getEmail());
            Long sum = user.getCart() == null ? 0 : user.getCart().getSum();
            session.setAttribute("sum", sum);
        }
    }

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        
                
        String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
        clearAuthenticationAttributes(request , authentication);
    }
}
