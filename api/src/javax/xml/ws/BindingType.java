/*
 * Copyright 2005 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javax.xml.ws;

import java.lang.annotation.Documented;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *  The <code>BindingType</code> annotation is used to
 *  specify the binding to use for a web service
 *  endpoint implementation class. As well as specify
 *  additional features that may be enabled.
 *  <p>
 *  This annotation may be overriden programmatically or via
 *  deployment descriptors, depending on the platform in use.
 *
 *  @since JAX-WS 2.0
 *
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BindingType {
     /**
      * A binding identifier (a URI).
      * If not specified, the default is the SOAP 1.1 / HTTP binding.
      * <p>
      * See the <code>SOAPBinding</code> and <code>HTTPBinding</code>
      * for the definition of the standard binding identifiers.
      *
      * @see javax.xml.ws.Binding
      * @see javax.xml.ws.soap.SOAPBinding#SOAP11HTTP_BINDING
      * @see javax.xml.ws.soap.SOAPBinding#SOAP12HTTP_BINDING
      * @see javax.xml.ws.soap.SOAPBinding#ADDRESSING_FEATURE
      * @see javax.xml.ws.soap.SOAPBinding#MTOM_FEATURE
      * @see javax.xml.ws.http.HTTPBinding#HTTP_BINDING
      */
     String value() default "" ;

     /**
      * An array of features to enable on the specified binding.
      * If not specified, no additional features are enabled.
      * <p>
      * See the <code>SOAPBinding</code> 
      * for the definition of the standard feature identifiers.
      *
      * @see javax.xml.ws.soap.SOAPBinding#ADDRESSING_FEATURE
      * @see javax.xml.ws.soap.SOAPBinding#MTOM_FEATURE
      */
     String[] features() default {};
}
