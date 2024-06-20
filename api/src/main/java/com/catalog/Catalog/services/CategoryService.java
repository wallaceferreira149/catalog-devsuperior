package com.catalog.Catalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.catalog.Catalog.dto.CategoryDTO;
import com.catalog.Catalog.enties.Category;
import com.catalog.Catalog.repositories.CategoryRepository;
import com.catalog.Catalog.services.exceptions.EntityNotFoundException;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly=true)
    public List<CategoryDTO> findAll() {
        List<Category> list = repository.findAll();
        
        return list.stream().map(item -> new CategoryDTO(item)).collect(Collectors.toList());


    }

    @Transactional(readOnly=true)
    public CategoryDTO findById(Long id) {
        Optional<Category> obj = repository.findById(id);
        Category entity = obj.orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada."));
        return new CategoryDTO(entity);
    }
}
