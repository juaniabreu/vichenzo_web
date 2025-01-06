package com.example.vichenzobackend.controller;


import com.example.vichenzobackend.dto.AuthLoginRequestDTO;
import com.example.vichenzobackend.dto.AuthResponseDto;
import com.example.vichenzobackend.service.UserDetailsServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/auth"})
@CrossOrigin({"*"})
public class AuthenticationController {
    @Autowired
    private UserDetailsServiceImp userDetailsService;

    public AuthenticationController() {
    }

    @PostMapping({"/login"})
    public ResponseEntity<AuthResponseDto> login(@RequestBody @Valid AuthLoginRequestDTO userRequest) {
        return new ResponseEntity(this.userDetailsService.loginUser(userRequest), HttpStatus.OK);
    }
}
