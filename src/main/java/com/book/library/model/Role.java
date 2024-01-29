package com.book.library.model;

import org.springframework.security.core.GrantedAuthority;

//Roller sabit olduğu için enum yapmak mantıklıdır.
//Simple Granted Authority kullansaydık string verebilirdik. Ama bir kişinin birden fazla rolü olsun istiyoruz.
public enum Role implements GrantedAuthority {
    ROLE_USER("USER"),
    ROLE_ADMIN("ADMIN"),
    ROLE_LIBRARIAN("LIBRARIAN");

    Role(String value) {
        this.value = value;
    }

    Role() {
    }

    private String value;
    @Override
    public String getAuthority() {
        return name();
    }
}
