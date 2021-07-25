package it.fabrick.test.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import it.fabrick.test.annotation.utility.CustomDateDeserializer;
import it.fabrick.test.annotation.utility.CustomDateSerializer;

@Retention(RUNTIME)
@Target({ TYPE, FIELD, METHOD, PARAMETER })
@JsonSerialize(using = CustomDateSerializer.class)
@JacksonAnnotationsInside
@JsonDeserialize(using = CustomDateDeserializer.class)
public @interface CustomDate {

}
