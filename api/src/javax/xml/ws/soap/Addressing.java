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
 * <p>
 * This feature represents the use of WS-Addressing with either
 * the SOAP 1.1/HTTP or SOAP 1.2/HTTP binding.  Using this feature
 * with any other binding is NOT required.
 * <p>
 * This annotation MUST only be used in conjunction with the
 * {@link javax.jws.WebService}, {@link WebServiceProvider},
 * {@link WebServiceRef}, or {@link WebServiceRefs} annotations.
 * <p>
 * When used with the <code>javax.jws.WebService</code> annotation, this
 * annotation MUST only be used on the service endpoint implementation
 * class.
 * <p>
 * This annotation has no affect when used with 
 * <code>WebServiceRef</code> annotations that are used to specify a generated
 * service class. When used with <code>WebServiceRef</code> that specifies a
 * service endpoint interface (SEI), the injected SEI proxy MUST
 * honor the values of the <code>Addressing</code> annotation.
 *
 * <p>
 * The following describes the effects of this feature with respect
 * to be enabled or disabled:
 * <ul>
 *  <li> ENABLED: In this Mode, WS-Addressing will be enabled.
 *       At runtime, WS-Addressing headers
 *       MUST be consumed by the receiver and produced by the
 *       sender even if the WSDL declares otherwise. The
 *       mustUnderstand="0" attribute MUST be used on the response WS-Addressing
 *       headers. If a WSDL needs to be generated, it MUST contain a corresponding
 *       wsam:Addressing policy assertion.
 *  <li> DISABLED: In this Mode, WS-Addressing will be disabled
 *       even if an associated WSDL specifies otherwise. At runtime,
 *       WS-Addressing headers MUST NOT be used. WS-Addressing may be explicitly
 *       disabled to prevent a JAX-WS implementation from consuming and producing
 *       WS-Addressing headers. If an application
 *       has implemented WS-Addressing itself, it MUST explicitly disable this feature.
*        Not doing so may break compatibility with future versions of JAX-WS.
 * </ul>
 * <p>
 * The <code>required</code> property determines whether the endpoint
 * requires WS-Addressing. If it is set true, WS-Addressing headers MUST
 * be present on incoming messages. By default the
 * <code>required</code> property is <code>false</code>.
 *
 * <p>
 * If the web service developer has not explicitly enabled this feature,
 * corresponding WSDL's wsam:Addressing policy assertion is used to find
 * the use of WS-Addressing.
 * If addressing is enabled, a corresponding wsam:Addressing policy assertion
 * must be generated in the WSDL as per
 * <a href="http://www.w3.org/TR/ws-addr-metadata/#wspolicyassertions">3.1 WS-Policy Assertions</a>
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
 * See <a href="http://www.w3.org/TR/2006/REC-ws-addr-core-20060509/">Web Services Addressing - Core</a>,
 * <a href="http://www.w3.org/TR/2006/REC-ws-addr-soap-20060509/">Web Services Addressing 1.0 - SOAP Binding</a>,
 * and <a href="http://www.w3.org/TR/ws-addr-metadata/">Web Services Addressing 1.0 - Metadata</a>
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
     * Specifies if this feature is enabled or disabled. If enabled, it means the
     * endpoint supports WS-Addressing but does not require its use. Corresponding
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
     * {@link Responses#ALL} supports all response types and this is the default
     * value.
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
