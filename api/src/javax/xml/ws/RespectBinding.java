/*
 * Copyright 2007 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javax.xml.ws;

import java.lang.annotation.Documented;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.xml.ws.spi.WebServiceFeatureAnnotation;


/**
 * This feature clarifies the use of the <code>wsdl:binding</code>
 * in a JAX-WS runtime.
 * <p>
 * This annotation MUST only be used in conjunction the
 * <code>javax.jws.WebService</code>, {@link WebServiceProvider},
 * {@link WebServiceRef} annotations.
 * When used with the <code>javax.jws.WebService</code> annotation this
 * annotation MUST only be used on the service endpoint implementation
 * class.
 * When used with a <code>WebServiceRef</code> annotation, this annotation
 * MUST only be used when a proxy instance is created. The injected SEI
 * proxy, and endpoint MUST honor the values of the <code>RespectBinding</code>
 * annotation.
 * <p>
 * This feature is only useful with web services that have an
 * associated WSDL. Enabling this feature requires that a JAX-WS
 * implementation inspect the <code>wsdl:binding</code> for an
 * endpoint at runtime to make sure that all <code>wsdl:extensions</code>
 * that have the <code>required</code> attribute set to <code>true</code>
 * are understood and are being used.
 * <p>
 * The following describes the affects of this feature with respect
 * to be enabled or disabled:
 * <ul>
 *  <li> ENABLED: In this Mode, a JAX-WS runtime MUST assure that all
 *  required <code>wsdl:binding</code> extensions are either understood
 *  and used by the runtime, or explicitly disabled by the web service
 *  application. A web service can disable a particular
 *  extension if there is a corresponding {@link WebServiceFeature} or annotation.
 *  Similarly, a web service client can disable
 *  particular extension using the corresponding <code>WebServiceFeature</code> while
 *  creating a proxy or Dispatch instance.
 *    The runtime MUST also make sure that binding of
 *    SEI parameters/return values respect the <code>wsdl:binding</code>.
 *    With this feature enabled, if a required (<code>wsdl:required="true"</code>)
 *    <code>wsdl:binding</code> extension is in the WSDL and it is not
 *    supported by a JAX-WS runtime and it has not
 *    been explicitly turned off by the web service developer, then
 *    that JAX-WS runtime MUST behave appropriately based on whether it is
 *    on the client or server:
 *    <UL>
 *      <li>Client: runtime MUST throw a
 *      <code>WebServiceException</code> no sooner than when one of the methods
 *      above is invoked but no later than the first invocation of an endpoint
 *      operation.
 *      <li>Server: throw a WebServiceException and the endpoint MUST fail to deploy
 *    </ul>
 *  <li> DISABLED: In this Mode, an implementation may choose whether
 *  to inspect the <code>wsdl:binding</code> or not and to what degree
 *  the <code>wsdl:binding</code> will be inspected.  For example,
 *  one implementation may choose to behave as if this feature is enabled,
 *  another implementation may only choose to verify the SEI's
 *  parameter/return type bindings.
 * </ul>
 *
 * @see RespectBindingFeature
 *
 * @since JAX-WS 2.1
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@WebServiceFeatureAnnotation(id=RespectBindingFeature.ID,bean=RespectBindingFeature.class)
public @interface RespectBinding {
    /**
     * Specifies if this feature is enabled or disabled.
     */
    boolean enabled() default true;
}
