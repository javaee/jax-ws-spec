/*
 * Copyright 2005 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */


package javax.xml.ws;

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import javax.xml.namespace.QName;
/**
 * Used to annotate a Provider implementation class.
 *
 * @since JAX-WS 2.0
 * @see javax.xml.ws.Provider
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface WebServiceProvider {
    /**
     * Location of the WSDL description for the service.
     */
    String wsdlLocation() default "";    
    
    /**
     * Service name.
     */
    String serviceName() default "";
    
    /**
     * Target namespace for the service
     */
    String targetNamespace() default "";

    /**
     * Port name.
     */
    String portName() default "";
}
