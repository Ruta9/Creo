package com.example.demo.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StatusUniquenessConstraintValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface StatusUniquenessConstraint {

    String message() default "Status names should be unique for one ticket type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
