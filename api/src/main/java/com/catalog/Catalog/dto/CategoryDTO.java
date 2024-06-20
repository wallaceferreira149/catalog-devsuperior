package com.catalog.Catalog.dto;

import java.io.Serializable;

import com.catalog.Catalog.enties.Category;

public class CategoryDTO implements Serializable {

    private Long id;
    private String name;

    public CategoryDTO(){}

    public CategoryDTO(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public CategoryDTO(Category entity) {
        id = entity.getId();
        name = entity.getName();
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
