/*
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javax.xml.ws;

import java.lang.annotation.Documented;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *  The <code>FeatureParameter</code> annotation is used to
 *  specify paramaters for a feature. 
 *  <p>
 *  This annotation may be overridden programmatically.
 *
 *  @since JAX-WS 2.1
 *
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FeatureParameter {
     /**
      * The name of the feature parameter;
      *
      * @since JAX-WS 2.1
      */
     String name() default "";

     /**
      * The value of parameter.  
      *
      * @since JAX-WS 2.1
      */
     String value() default "";     
}
