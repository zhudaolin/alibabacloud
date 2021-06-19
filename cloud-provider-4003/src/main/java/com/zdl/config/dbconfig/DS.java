package com.zdl.config.dbconfig;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface DS {

    String value() default "";
}
