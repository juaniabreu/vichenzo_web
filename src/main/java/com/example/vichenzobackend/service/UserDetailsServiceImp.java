package com.example.vichenzobackend.service;

import com.example.vichenzobackend.dto.AuthLoginRequestDTO;
import com.example.vichenzobackend.dto.AuthResponseDto;
import com.example.vichenzobackend.model.UserSec;
import com.example.vichenzobackend.repository.IUserRepository;
import com.example.vichenzobackend.utils.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    private IUserRepository userRepo;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDetailsServiceImp() {
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserSec userSec = (UserSec)this.userRepo.findUserEntityByUsername(username).orElseThrow(() -> {
            return new UsernameNotFoundException("El usuario " + username + "no fue encontrado");
        });
        if (!userSec.isEnabled()) {
            throw new DisabledException("El usuario está deshabilitado.");
        } else {
            List<SimpleGrantedAuthority> authorityList = new ArrayList();
            userSec.getRolesList().forEach((role) -> {
                authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRole())));
            });
            userSec.getRolesList().stream().flatMap((role) -> {
                return role.getPermissionsList().stream();
            }).forEach((permission) -> {
                authorityList.add(new SimpleGrantedAuthority(permission.getPermissionName()));
            });
            return new User(userSec.getUsername(), userSec.getPassword(), userSec.isEnabled(), userSec.isAccountNotExpired(), userSec.isCredentialNotExpired(), userSec.isAccountNotLocked(), authorityList) {
            };
        }
    }

    public AuthResponseDto loginUser(@Valid AuthLoginRequestDTO authLoginRequest) {
        String username = authLoginRequest.username();
        String password = authLoginRequest.password();
        Authentication authentication = this.authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accesToken = this.jwtUtils.createToken(authentication);
        AuthResponseDto authResponseDto = new AuthResponseDto(username, "Login succesfull", accesToken, true);
        return authResponseDto;
    }

    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = this.loadUserByUsername(username);
        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username or password");
        } else if (!this.passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        } else {
            return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        }
    }
}