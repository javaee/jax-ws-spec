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
 *  The <code>Feature</code> annotation is used to
 *  enable/disable a feature to use for a web service
 *  endpoint implementation class. 
 *  <p>
 *  This annotation may be overriden programmatically or via
 *  deployment descriptors, depending on the platform in use.
 *
 *  @since JAX-WS 2.1
 *
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Feature {
     /**
      * The ID of feature to enable or disable;
      * <p>
      * See the <code>SOAPBinding</code> 
      * for the definition of the standard feature identifiers.
      *
      * @see javax.xml.ws.soap.SOAPBinding#ADDRESSING_FEATURE
      * @see javax.xml.ws.soap.SOAPBinding#MTOM_FEATURE
      *
      */
     String value() default "";

     /**
      * Specifies if the features is enabled or disabled.  
      *
      * <p>
      * See the <code>SOAPBinding</code> 
      * for the definition of the standard feature identifiers.
      *
      * @see javax.xml.ws.soap.SOAPBinding#ADDRESSING_FEATURE
      * @see javax.xml.ws.soap.SOAPBinding#MTOM_FEATURE
      *
      */
     boolean enabled() default true;     
     
     /**
      * Parameters for the specified feature.  Each feature
      * may take any number of parameters to customize the
      * use of the feature.
      *
      */
     FeatureParameter[] parameters() default {};   
}
