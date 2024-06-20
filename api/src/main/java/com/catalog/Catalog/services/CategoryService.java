package com.catalog.Catalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.catalog.Catalog.enties.Category;
import com.catalog.Catalog.repositories.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly=true)
    public List<Category> findAll() {
        return repository.findAll();
    }
}
