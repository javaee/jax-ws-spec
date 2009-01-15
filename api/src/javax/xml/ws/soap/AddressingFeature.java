/*
 * Copyright 2007 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javax.xml.ws.soap;

import javax.xml.ws.WebServiceFeature;

/**
 * This feature represents the use of WS-Addressing with either
 * the SOAP 1.1/HTTP or SOAP 1.2/HTTP binding.  Using this feature
 * with any other binding is NOT required.
 * <p>
 * Enabling this feature on the client will cause the JAX-WS runtime
 * to include WS-Addressing headers in SOAP messages.
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
 * @see Addressing
 * @since JAX-WS 2.1
 */

public final class AddressingFeature extends WebServiceFeature {
    /** 
     * Constant value identifying the AddressingFeature
     */
    public static final String ID = "http://www.w3.org/2005/08/addressing/module";
  
    /**
     * Property for the <code>required</code> feature parameter. When WS-Addressing 
     * is enabled, the value of this property will be used
     * to specify if WS-Addressing headers MUST be present on incoming messages.  This 
     * property only has meaning on the endpoint and has no
     * affect when used on the client.
     */
    protected final boolean required;

    /**
     * If addressing is enabled, this property determines if endpoint requires
     * the use of only anonymous responses, or only non-anonymous responses, or all.
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
    public enum Responses { ANONYMOUS, NON_ANONYMOUS, ALL }


    private final Responses responses;

    /**
     * Create an <code>AddressingFeature</code>.
     * The instance created will be enabled.
     */
    public AddressingFeature() {
        this(true, false, Responses.ALL);
    }
    
    /** 
     * Create an <code>AddressingFeature</code>
     * 
     * @param enabled specifies whether this feature should
     * be enabled or not.
     */
    public AddressingFeature(boolean enabled) {
        this(enabled, false, Responses.ALL);
    }

    /** 
     * Create an <code>AddressingFeature</code>
     * 
     * @param enabled specifies whether this feature should
     * be enabled or not.
     * @param required specifies whether
     * WS-Addressing headers MUST be present on incoming messages. This property
     * only has meaning on the endpoint and has no affect when
     * used on the client.
     */
    public AddressingFeature(boolean enabled, boolean required) {
        this(enabled, required, Responses.ALL);
    }

    /**
     * Create an <code>AddressingFeature</code>
     *
     * @param enabled specifies whether this feature should
     * be enabled or not.
     * @param required specifies whether
     * WS-Addressing headers MUST be present on incoming messages. This property
     * only has meaning on the endpoint and has no affect when
     * used on the client.
     * @param responses specifies whether endpoint requires
     * the use of anonymous responses.
     */
    public AddressingFeature(boolean enabled, boolean required, Responses responses) {
        this.enabled = enabled;
        this.required = required;
        this.responses = responses;
    }
    
    /**
     * {@inheritDoc}
     */
    public String getID() {
        return ID;
    }
    
    /**
     * Gets the boolean value used to determine if WS-Addressing
     * headers MUST be present on incoming messages.  This property
     * only has meaning on the endpoint, and has no affect
     * when used on the client.
     *
     * @return the current required value
     */
    public boolean isRequired() {
        return required;
    }

    /**
     * If addressing is enabled, this property determines if endpoint requires
     * the use of anonymous responses, or non-anonymous responses,
     * or all responses.
     *
     * <p>
     * @return {@link Responses#ALL} when endpoint supports all types of
     * responses
     *         {@link Responses#ANONYMOUS} when endpoint requires the use of
     * only anonymous responses.
     *         {@link Responses#NON_ANONYMOUS} when endpoint requires the use
     * of only non-anonymous responses.
     *
     * @since JAX-WS 2.2
     */
    public Responses getResponses() {
        return responses;
    }

}
