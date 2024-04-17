package com.fan.essay.anno;

import com.fan.essay.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {StateValidation.class }) // 指定校验器
@Target({FIELD})
@Retention(RUNTIME)
public @interface State {

    String message() default "state的值只能是已发布或草稿";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
