package net.ellwein.routey.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mapping annotation can be used within a user class to annotate a method which
 * is responsible for request processing.
 * 
 * @author Alexander Ellwein
 * @since 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Mapping {

	/**
	 * Default value which may contain request URI(s) in regex form. Routey
	 * servlet will map to the first matching URI regex in the internal map.
	 * 
	 * @return array of request URIs
	 */
	String[] value() default {};

	/**
	 * Contains the request methods, which are processed on this mapping. On
	 * default, only GET method requests get processed.
	 * 
	 * @return array with request methods
	 */
	RequestMethod[] method() default {};

}
