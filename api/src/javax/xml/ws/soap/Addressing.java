/*
 * Copyright 2007 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javax.xml.ws.soap;

import java.lang.annotation.Documented;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


import javax.xml.ws.Action;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.FaultAction;
import javax.xml.ws.spi.WebServiceFeatureAnnotation;


/**
 * <p>
 * This feature represents the use of WS-Addressing with either
 * the SOAP 1.1/HTTP or SOAP 1.2/HTTP binding.  Using this feature
 * with any other binding is NOT required.
 * <p>
 * {@delete Enabling this feature will result in the
 * <code>wsaw:UsingAddressing</code> element being added to the
 * <code>wsdl:Binding</code> for
 * the endpoint and in the runtime being capable of responding to
 * WS-Addressing headers.}
 * <p>
 * The following describes the effects of this feature with respect
 * to be enabled or disabled:
 * <ul>
 *  <li> ENABLED: In this Mode, {@add WS-}Addressing will be enabled.
 *       {@delete If there is not a WSDL associated with the Endpoint and
 *       a WSDL is to be generated, it MUST be generated with the
 *       wsaw:UsingAddressing element.} At runtime, {@add WS-}Addressing headers
 *       MUST be consumed by the receiver and {@delete generated}{@add produced} by the
 *       sender even if the WSDL declares otherwise. The
 *       mustUnderstand="0" attribute MUST be used on the {@add response WS-}Addressing
 *       headers. 
 *  <li> DISABLED: In this Mode, {@add WS-}Addressing will be disabled
 *       even if an associated WSDL specifies otherwise. At runtime,
 *       {@add WS-}Addressing headers MUST NOT be used. {@add WS-Addressing may be explicitly
 *       disabled to prevent a JAX-WS implementation from consuming and producing
 *       WS-Addressing headers. If an application
 *       has implemented WS-Addressing itself, it MUST explicitly disable this feature.
*        Not doing so may break compatibility with future versions of JAX-WS.}
 * </ul>
 * <p>
 * The <code>required</code> property can be used to
 * specify if {@delete the <code>required</code> attribute on the
 * <code>wsaw:UsingAddressing</code> element should
 * be <code>true</code> or <code>false</code>} {@add WS-Addressing headers MUST
 * be present on incoming messages.}  By default the
 * <code>required</code> property is <code>false</code>. 
 * <p>
 * {@add The definition of this annotation is incomplete in this release of JAX-WS as
 * there is no standard way to convey the use of WS-Addressing via a WSDL and there is no
 * standard definition for the default value of WS-Addressing <code>Action</code> headers; 
 * however, the runtime behavior of this annotation is well-defined. 
 * It is intended that a future version of 
 * JAX-WS will require the use of the standard mechanism to convey the use
 * of WS-Addressing via WSDL and default values for WS-Addressing <code>Action</code> headers
 * as defined by the W3C WG on WS-Addressing.}  
 * <p>
 * {@add To write a portable endpoint and its corresponding client with this version of JAX-WS,
 * an endpoint MUST explicitly specify what WS-Addressing <code>Actions</code> are to be used via the }{@link Action} {@add and }
 * {@link FaultAction} {@add annotations.  The client MUST explicitly enable addresssing via the
 * }{@link AddressingFeature}{@add , and for each invocation, the client MUST explicitly set the }
 * {@link BindingProvider#SOAPACTION_URI_PROPERTY}.
 * {@add After the W3C WG on WS-Addressing has specified how the use of WS-Addressing is specified in the WSDL,
 * and what the default value must be for Action headers, a future version of JAX-WS will remove these requirements.}
 * <p>
 * See <a href="http://www.w3.org/TR/2006/REC-ws-addr-core-20060509/">Web Services Addressing - Core</a>
 * {@add and <a href="http://www.w3.org/TR/2006/REC-ws-addr-soap-20060509/">Web Services Addressing 1.0 - SOAP Binding</a> }
 * for more information on WS-Addressing.
 * {@delete </a> for more information on <code>wsaw:UsingAddressing</code>.}
 * {@delete See <a href="http://www.w3.org/TR/2006/CR-ws-addr-wsdl-20060529/#indicatinguse/">WS-Addressing - WSDL 1.0
 * </a> for more information on <code>wsaw:UsingAddressing</code>.}
 *
 * @since JAX-WS 2.1
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@WebServiceFeatureAnnotation(id=AddressingFeature.ID,bean=AddressingFeature.class)
public @interface Addressing {
    /**
     * Specifies if this feature is enabled or disabled.
     */
    boolean enabled() default true;
    
    /**
     * {@delete Property to determine the value of the <code>wsdl:required</code> attribute 
     * on <code>wsaw:UsingAddressing</code> element in the WSDL.}
     * {@add Property to determine if WS-Addressing headers MUST
     * be present on incoming messages.}
     */
    boolean required() default false;
}
