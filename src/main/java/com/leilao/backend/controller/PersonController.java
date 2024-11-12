package com.leilao.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leilao.backend.model.Person;
import com.leilao.backend.model.PersonAuthRequestDTO;
import com.leilao.backend.model.PersonAuthResponseDTO;
import com.leilao.backend.security.JwtService;
import com.leilao.backend.service.PersonService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public PersonAuthResponseDTO authenticateUser(@RequestBody PersonAuthRequestDTO authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(), authRequest.getPassword()));
        return new PersonAuthResponseDTO(
                authRequest.getEmail(), jwtService.generateToken(authentication.getName()));
    }

    @PostMapping("/password-code-request")
    public String passwordCodeRequest(@RequestBody PersonAuthRequestDTO person) {
        return personService.passwordCodeRequest(person);
    }

    @PostMapping
    public Person create(@Valid @RequestBody Person person) {
        return personService.create(person);
    }

    @PutMapping
    public Person update(@Valid @RequestBody Person person) {
        return personService.create(person);
    }
}
