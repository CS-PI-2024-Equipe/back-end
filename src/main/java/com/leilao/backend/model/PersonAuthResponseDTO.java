package com.leilao.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
// @AllArgsConstructor
public class PersonAuthResponseDTO {

    private String email;
    private String token;

    public PersonAuthResponseDTO(String email, String token) {
        this.email = email;
        this.token = token;
    }
}
