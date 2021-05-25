package com.example.security.domain.user;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Entity
@Getter
public class UserInfo implements UserDetails {

    @Id
    @Column(name = "code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column
    private String auth;

    @Builder
    public UserInfo(String username, String password, String auth){
        this.username = username;
        this.password = password;
        this.auth = auth;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (String role: auth.split(",")){ //auth : "ROLE_ADMIN,ROLE_USER"
            roles.add(new SimpleGrantedAuthority(role));
        }
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; //true: 계정 만료되지 않음
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; //true: 계정 잠금되지 않음
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; //true: 패스워드 만료되지 않음
    }

    @Override
    public boolean isEnabled() {
        return true; //true: 계정 사용가능함
    }
}
