package com.leilao.backend.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.leilao.backend.model.Person;
import com.leilao.backend.repository.PersonRepository;

import jakarta.mail.MessagingException;

@Service
public class PersonService implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return personRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public Person create(Person person) {
        // person.setPassword(passwordEncoder.encode(person.getPassword()));
        Person personSaved = personRepository.save(person);

        Context context = new Context();
        context.setVariable("name", personSaved.getName());
        try {
            emailService.sendTemplateEmail(
                    personSaved.getEmail(),
                    "Cadastro Efetuado com Sucesso", context,
                    "emailWelcome");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return personSaved;
    }

    public Person update(Person person) {
        Person personSaved = personRepository.findById(person.getId())
                .orElseThrow(() -> new NoSuchElementException("Objeto não encontrado"));

        personSaved.setName(person.getName());
        personSaved.setEmail(person.getEmail());

        return personRepository.save(personSaved);
    }

}
