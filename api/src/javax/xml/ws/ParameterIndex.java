/*
 * Copyright 2004 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javax.xml.ws;

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

/** 
 * Annotates a field or a method of a wrapper class with information
 * about the method parameter it corresponds to.
 *
 *  @since JAX-WS 2.0
**/
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ParameterIndex {
  /**
   *  The index of the method parameter that the annotated program
   *  element (a field or a method) corresponds to.
  **/
  public int value() default -1;
}
