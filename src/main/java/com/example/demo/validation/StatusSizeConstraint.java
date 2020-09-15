package com.example.demo.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StatusSizeConstraintValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface StatusSizeConstraint {

    String message() default "There can not be less than 2 or more than 8 statuses for one ticket type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
