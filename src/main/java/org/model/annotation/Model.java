package org.model.annotation;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Model {
    String intent();
    String method();


    enum DefaultIntent{
        SALE, BUY,
    }
}
