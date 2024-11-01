package com.adera.take.home.test.validation;

import java.lang.annotation.*;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = StrongPasswordValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StrongPassword {
  String message() default "password Length minimal 8 karakter dan ada kombinasi uppercase letters, lowercase letters, numbers, special characters.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
