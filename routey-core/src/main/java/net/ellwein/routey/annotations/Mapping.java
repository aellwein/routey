package net.ellwein.routey.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Mapping {

	/**
	 * Request URIs.
	 * 
	 * @return
	 */
	String[] value() default {};

	RequestMethod[] method() default {};

}
