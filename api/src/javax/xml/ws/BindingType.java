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
 *  The <code>BindingType</code> annotation is used to
 *  specify the binding to use for a web service
 *  endpoint implementation class. As well as specify
 *  additional features that may be enabled.
 *  <p>
 *  This annotation may be overridden programmatically.
 *  <p>
 *  Here is a sample use of the BindingType annotation that specifies
 *  use of the SOAP1.1/HTTP binding, it enables both the 
 *  AddressingFeature and the MTOMFeature and also sets the 
 *  <code>threshold</code> feature parameter on the MTOMFeature.
 * 
 *  <pre>
 *  &#64;BindingType(value=SOAPBinding.SOAP11HTTP_BINDING,
 *            features={
 *               &#64;Feature(value=AddressingFeature.ID),
 *               &#64;Feature(value=MTOMFeature.ID,
 *                   parameters={&#64;FeatureParameter(name=MTOMFeature.THRESHOLD, value="1000")})
 *           })
 *  public interface MyWebService { ... }
 *  </pre>
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
      * @see javax.xml.ws.http.HTTPBinding#HTTP_BINDING
      */
     String value() default "" ;

     /**
      * An array of Features to enable/disable on the specified binding.
      * If not specified, features will be enabled/disabled based
      * on their own rules. Refer to the documentation of the feature
      * to determine when it will be automatically enabled.
      * <p>
      * See the <code>SOAPBinding</code> 
      * for the definition of the standard feature identifiers.
      *
      * @see javax.xml.ws.soap.AddressingFeature
      * @see javax.xml.ws.soap.MTOMFeature
      *
      * @since JAX-WS 2.1
      */
     Feature[] features() default {};
}
