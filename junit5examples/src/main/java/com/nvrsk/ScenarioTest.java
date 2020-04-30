package com.nvrsk;

import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@TestTemplate
@ExtendWith(ScenarioTestExtension.class)
public @interface ScenarioTest {

    Class<? extends ScenarioFactory<? extends Scenario>>[] factory();
}
