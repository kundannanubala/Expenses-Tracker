package com.SpringBootMVC.ExpensesTracker.DTO;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails extends User {

    private String clientId;

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, String clientId) {
        super(username, password, authorities);
        this.clientId = clientId;
    }

    public String getClientId() {
        return clientId;
    }
}
