package com.catalog.Catalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.catalog.Catalog.enties.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
    
}
