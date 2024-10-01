package com.leilao.backend.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @JsonIgnore
    private String password;
    
    @JsonIgnore
    @Column(name = "validation_code")
    private String validationCode;

    // @Temporal(TemporalType.TIMESTAMP)
    // private Date validationCodeValidity;
    @JsonIgnore
    private LocalDateTime validationCodeValidity;
    @OneToMany(mappedBy = "person", orphanRemoval = true,
     cascade = CascadeType.ALL)
    @Setter(value = AccessLevel.NONE)
    private List<PersonProfile> personProfile;

    public void setPersonProfile(List<PersonProfile> lpp) {
        for (PersonProfile p : lpp) {
            p.setPerson(this);
        }
        personProfile = lpp;
    }
}
