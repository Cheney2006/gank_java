package com.cheney.gankjava.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

@Documented
@Qualifier
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Github {
}
