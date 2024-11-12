package com.leilao.backend.model;

import lombok.Data;

@Data
public class PersonAuthRequestDTO {
    private String email;
    private String password;
}
