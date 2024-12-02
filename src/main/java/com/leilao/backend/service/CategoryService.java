package com.leilao.backend.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leilao.backend.model.Category;
import com.leilao.backend.repository.CategoryRepository;
import com.leilao.backend.security.AuthPersonProvider;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AuthPersonProvider authPersonProvider;

    public Category create(Category category) {
        category.setPerson(authPersonProvider.getAuthenticatedUser());
        return categoryRepository.save(category);
    }

    public Category update(Category category) {
        Category categorySaved = categoryRepository.findById(category.getId())
                .orElseThrow(() -> new NoSuchElementException("Objeto não encontrado"));
        categorySaved.setName(category.getName());
        return categoryRepository.save(categorySaved);
    }

    public void delete(Long id) {
        Category categorySaved = categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Objeto não encontrado"));
        categoryRepository.delete(categorySaved);
    }

    public List<Category> listAll() {
        return categoryRepository.findAll();
    }
}
