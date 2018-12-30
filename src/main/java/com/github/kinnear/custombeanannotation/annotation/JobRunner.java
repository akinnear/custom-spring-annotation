package com.github.kinnear.custombeanannotation.annotation;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Bean
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public @interface JobRunner {
    @AliasFor(annotation = Bean.class, attribute = "value")
    String[] value() default {};
    @AliasFor(annotation = Bean.class, attribute = "name")
    String[] name() default {};
    @AliasFor(annotation = Bean.class, attribute = "autowire")
    Autowire autowire() default Autowire.NO;
    @AliasFor(annotation = Bean.class, attribute = "initMethod")
    String initMethod() default "";
    @AliasFor(annotation = Bean.class, attribute = "destroyMethod")
    String destroyMethod() default AbstractBeanDefinition.INFER_METHOD;
}
