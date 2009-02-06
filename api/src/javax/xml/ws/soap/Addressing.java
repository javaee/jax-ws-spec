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

import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceRef;
import javax.xml.ws.WebServiceRefs;
import javax.xml.ws.WebServiceProvider;
import javax.xml.ws.soap.AddressingFeature.Responses;
import javax.xml.ws.spi.WebServiceFeatureAnnotation;

/**
 * This feature represents the use of WS-Addressing with either
 * the SOAP 1.1/HTTP or SOAP 1.2/HTTP binding. Using this feature
 * with any other binding is undefined.
 * <p>
 * This annotation MUST only be used in conjunction with the
 * {@link javax.jws.WebService}, {@link WebServiceProvider},
 *  and {@link WebServiceRef} annotations.
 * When used with a <code>javax.jws.WebService</code> annotation, this
 * annotation MUST only be used on the service endpoint implementation
 * class.
 * When used with a <code>WebServiceRef</code> annotation, this annotation
 * MUST only be used when a proxy instance is created. The injected SEI
 * proxy MUST honor the values of the <code>Addressing</code> annotation.
 * <p>
 * The following describes the effects of this feature with respect
 * to be enabled or disabled:
 * <ul>
 *  <li> ENABLED: In this Mode, WS-Addressing will be enabled. It means
 *       the endpoint supports WS-Addressing but does not require its use.
 *       A sender could send messages with WS-Addressing headers or without
 *       WS-Addressing headers. But a receiver MUST consume both types of
 *       messages.
 *  <li> DISABLED: In this Mode, WS-Addressing will be disabled.
 *       At runtime, WS-Addressing headers MUST NOT be used by a sender or
 *       receiver.
 * </ul>
 * <p>
 * If the feature is enabled, the <code>required</code> property determines
 * whether the endpoint requires WS-Addressing. If it is set true,
 * WS-Addressing headers MUST be present on incoming and outgoing messages.
 * By default the <code>required</code> property is <code>false</code>.
 *
 * <p>
 * If the web service developer has not explicitly enabled this feature,
 * WSDL's wsam:Addressing policy assertion is used to find
 * the use of WS-Addressing. By using the feature explicitly, an application
 * overrides WSDL's indication of the use of WS-Addressing. In some cases,
 * this is really required. For example, if an application has implemented
 * WS-Addressing itself, it can use this feature to disable addressing. That
 * means a JAX-WS implementation doesn't consume or produce WS-Addressing
 * headers.
 *
 * <p>
 * If addressing is enabled, a corresponding wsam:Addressing policy assertion
 * must be generated in the WSDL as per
 * <a href="http://www.w3.org/TR/ws-addr-metadata/#wspolicyassertions">
 * 3.1 WS-Policy Assertions</a>
 *
 * <p>
 * <b>Example 1: </b>Possible Policy Assertion in the generated WSDL for
 * <code>&#64;Addressing</code>
 * <pre>
 *   &lt;wsam:Addressing wsp:Optional="true">
 *     &lt;wsp:Policy/>
 *   &lt;/wsam:Addressing>
 * </pre>
 *
 * <p>
 * <b>Example 2: </b>Possible Policy Assertion in the generated WSDL for
 * <code>&#64;Addressing(required=true)</code>
 * <pre>
 *   &lt;wsam:Addressing>
 *     &lt;wsp:Policy/>
 *   &lt;/wsam:Addressing>
 * </pre>
 *
 * <p>
 * <b>Example 3: </b>Possible Policy Assertion in the generated WSDL for
 * <code>&#64;Addressing(required=true, responses=Responses.ANONYMOUS)</code>
 * <pre>
 *   &lt;wsam:Addressing>
 *      &lt;wsp:Policy>
 *        &lt;wsam:AnonymousResponses/>
 *      &lt;/wsp:Policy>
 *   &lt;/wsam:Addressing>
 * </pre>
 *
 * <p>
 * See <a href="http://www.w3.org/TR/2006/REC-ws-addr-core-20060509/">
 * Web Services Addressing - Core</a>,
 * <a href="http://www.w3.org/TR/2006/REC-ws-addr-soap-20060509/">
 * Web Services Addressing 1.0 - SOAP Binding</a>,
 * and <a href="http://www.w3.org/TR/ws-addr-metadata/">
 * Web Services Addressing 1.0 - Metadata</a>
 * for more information on WS-Addressing.
 * 
 * @since JAX-WS 2.1
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@WebServiceFeatureAnnotation(id=AddressingFeature.ID,bean=AddressingFeature.class)
public @interface Addressing {
    /**
     * Specifies if this feature is enabled or disabled. If enabled, it means
     * the endpoint supports WS-Addressing but does not require its use.
     * Corresponding
     * <a href="http://www.w3.org/TR/ws-addr-metadata/#wspolicyaddressing">
     * 3.1.1 Addressing Assertion</a> must be generated in the generated WSDL.
     */
    boolean enabled() default true;
    
    /**
     * If addressing is enabled, this property determines whether the endpoint
     * requires WS-Addressing. If required is true, the endpoint requires
     * WS-Addressing and WS-Addressing headers MUST
     * be present on incoming messages. A corresponding
     * <a href="http://www.w3.org/TR/ws-addr-metadata/#wspolicyaddressing">
     * 3.1.1 Addressing Assertion</a> must be generated in the WSDL.
     */
    boolean required() default false;

    /**
     * If addressing is enabled, this property determines if endpoint requires
     * the use of anonymous responses, or non-anonymous responses, or all.
     *
     * <p>
     * {@link Responses#ALL} supports all response types and this is the
     * default value.
     *
     * <p>
     * {@link Responses#ANONYMOUS} requires the use of only anonymous
     * responses. It will result into wsam:AnonymousResponses nested assertion
     * as specified in
     * <a href="http://www.w3.org/TR/ws-addr-metadata/#wspolicyanonresponses">
     * 3.1.2 AnonymousResponses Assertion</a> in the generated WSDL.
     *
     * <p>
     * {@link Responses#NON_ANONYMOUS} requires the use of only non-anonymous
     * responses. It will result into
     * wsam:AnonymousResponses nested assertion as specified in
     * <a href="http://www.w3.org/TR/ws-addr-metadata/#wspolicynonanonresponses">
     * 3.1.3 NonAnonymousResponses Assertion</a> in the generated WSDL.
     *
     * @since JAX-WS 2.2
     */
    Responses responses() default Responses.ALL;

}
