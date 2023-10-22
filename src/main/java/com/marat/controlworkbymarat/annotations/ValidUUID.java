package com.marat.controlworkbymarat.annotations;



import com.marat.controlworkbymarat.validations.UUIDValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target(ElementType.FIELD)
@Constraint(validatedBy={UUIDValidator.class})
@Retention(RUNTIME)
public @interface ValidUUID {
    String message() default "Invalid uuid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}