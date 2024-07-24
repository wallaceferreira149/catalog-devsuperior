package com.catalog.Catalog.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ValidationError extends StandardError {

  private List<FieldMessage> errors = new ArrayList<>();

  public void addError(String fieldName, String message) {
    this.errors.add(new FieldMessage(fieldName, message));
  }
}
