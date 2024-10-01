package com.leilao.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leilao.backend.model.Profile;

public interface ProfileRepository extends 
        JpaRepository<Profile, Long>{

}
