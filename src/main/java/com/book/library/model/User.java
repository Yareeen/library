package com.book.library.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import jakarta.persistence.*;
import java.util.Collection;


@Entity
@Table(name = "users")
//Spring securitye ait bir entity olduğunu security contexte bildirmek için implements ederiz.
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String password;

    /*
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @JoinTable(name = "authorities", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING) //Enuma karşılık geldiğini belirtmek için
    private Set<Role> authorities; // 'librarian', 'admin', or 'user'. Birden fazla rol vermek için
  */

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role authorities; // 'librarian', 'admin', or 'user'.

    private boolean accountNonExpired;
    private boolean isEnabled;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;

    public User(Long id, String name, String username, String password, Role authorities, boolean accountNonExpired, boolean isEnabled, boolean accountNonLocked, boolean credentialsNonExpired) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.accountNonExpired = accountNonExpired;
        this.isEnabled = isEnabled;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public User(String name, String username, String password, Role authorities, boolean accountNonExpired, boolean isEnabled, boolean accountNonLocked, boolean credentialsNonExpired) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.accountNonExpired = accountNonExpired;
        this.isEnabled = isEnabled;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public User() {
    }

    public Long getId() {
        return id;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Bu metodda kullanıcının rollerini döndürmelisiniz.
        // Örneğin: return Collections.singletonList(new SimpleGrantedAuthority(authorities.name()));
        return null;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        // Burada collection'ı uygun bir şekilde işleyerek Role tipine dönüştürebilirsiniz.
        // Örneğin: this.authorities = authorities.stream().map(grantedAuthority -> Role.valueOf(grantedAuthority.getAuthority())).collect(Collectors.toSet());
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

}