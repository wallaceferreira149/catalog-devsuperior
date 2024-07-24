package com.catalog.Catalog.services.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.catalog.Catalog.dto.CreateUserDTO;
import com.catalog.Catalog.enties.User;
import com.catalog.Catalog.repositories.UserRepository;
import com.catalog.Catalog.resources.exceptions.FieldMessage;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, CreateUserDTO> {

    @Autowired
    private UserRepository repository;
    
    @Override
    public void initialize(UserInsertValid ann) {
        
    }

    @Override
    public boolean isValid(CreateUserDTO dto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        // inclua os testes aqui, inserindo erros na lista
        User user = repository.findByEmail(dto.email());
        if (user != null) {
            list.add(new FieldMessage("email", "Email ja existe"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
    
}
