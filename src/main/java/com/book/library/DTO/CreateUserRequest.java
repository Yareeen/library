package com.book.library.DTO;

import javax.management.relation.Role;

public class CreateUserRequest{
        String name;
        String username;
        String password;
        Role authorities;

    public CreateUserRequest(String name, String username, String password, Role authorities) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public CreateUserRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getAuthorities() {
        return authorities;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(Role authorities) {
        this.authorities = authorities;
    }


}