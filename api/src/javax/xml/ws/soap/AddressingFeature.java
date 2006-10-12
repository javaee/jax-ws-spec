/*
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javax.xml.ws.soap;

import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.spi.Provider;

/**
 * This feature represents the use of WS-Addressing with either
 * the SOAP 1.1/HTTP or SOAP 1.2/HTTP binding.  Using this feature
 * with any other binding is NOT required.
 * <p>
 * Enabling this feature on the server will result in the
 * <code>wsaw:UsingAddressing</code> element being added to the 
 * <code>wsdl:Binding</code> for 
 * the endpoint and in the runtime being capable of responding to
 * WS-Addressing headers.
 * <p>
 * Enabling this feature on the client will cause the JAX-WS runtime
 * to include WS-Addressing headers in SOAP messages.
 * <p>
 * If the web service developer has not explicitly enabled this feature,
 * it may be automatically enabled if the associated WSDL enables
 * WS-Addressing via the <code>wsaw:UsingAddressing</code> element with
 * the <code>wsdl:required</code> attribute set to <code>true</code>.
 * <br>
 * See {@link javax.xml.ws.RespectBindingFeature} for more information 
 * on required WSDL extensions.
 * <p>
 * The following describes the affects of this feature with respect
 * to be enabled or disabled:
 * <ul>
 *  <li> ENABLED: In this Mode, Addressing will be enabled. 
 *       If there is not a WSDL associated with the Endpoint and
 *       a WSDL is to be generated, it MUST be generated with the 
 *       wsaw:UsingAddressing element. At runtime, Addressing headers
 *       MUST be consumed by the receiver and generated by the
 *       sender even if the WSDL declares otherwise. The
 *       mustUnderstand="0" attribute MUST be used on the Addressing
 *       headers.
 *  <li> DISABLED: In this Mode, Addressing will be disabled
 *       even if an associated WSDL specifies otherwise. At runtime, 
 *       Addressing headers MUST NOT be used.
 * </ul>
 * <p>
 * The {@link #required} property can be used to 
 * specify if the <code>required</code> attribute on the 
 * <code>wsaw:UsingAddressing</code> element should
 * be <code>true</code> or <code>false</code>.  By default the
 * <code>wsdl:required</code> parameter is <code>false</code>.
 *
 * See <a href="http://www.w3.org/TR/2006/REC-ws-addr-core-20060509/">WS-Addressing</a>
 * for more information on WS-Addressing.
 * See <a href="http://www.w3.org/TR/2006/CR-ws-addr-wsdl-20060529/">WS-Addressing - WSDL 1.0
 * </a> for more information on <code>wsaw:UsingAddressing</code>.
 *
 * @since JAX-WS 2.1
 */
public final class AddressingFeature extends WebServiceFeature {
    /** 
     * Constant value identifying the AddressingFeature
     */
    public static final String ID = "http://www.w3.org/2005/08/addressing/module";
  
// todo: remove this    
//    /** 
//     * Constant ID for the <code>required</code> feature parameter
//     */
//    public static final String IS_REQUIRED = "ADDRESSING_IS_REQUIRED";
            
            
    /**
     * Property for <code>required</code> feature parameter. When Addressing 
     * is enabled, the value of this property will be set to the 
     * <code>wsdl:required</code> attribute on 
     * <code>wsaw:UsingAddressing</code> element in the WSDL.
     */
    protected boolean required = false;
    
    /**
     * Create an <code>AddressingFeature</code>.
     * The instance created will be enabled.
     */
    public AddressingFeature() {
        this.enabled = true;
    }
    
    /** 
     * Create an <code>AddressingFeature</code>
     * 
     * @param enabled specifies whether this feature should
     * be enabled or not.
     */
    public AddressingFeature(boolean enabled) {
        this.enabled = enabled;
    }

    /** 
     * Create an <code>AddressingFeature</code>
     * 
     * @param enabled specifies whether this feature should
     * be enabled or not.
     * @param isRequired specifies the value that will be used  
     * for the <code>wsdl:required</code> attribute on the
     * <code>wsaw:UsingAddressing</code> element.
     */
    public AddressingFeature(boolean enabled, boolean required) {
        this.enabled = enabled;
        this.required = required;
    }    
    
    /**
     * {@inheritDoc}
     */
    public String getID() {
        return ID;
    }
    
    /**
     * Gets the boolean value used to set the
     * <code>wsdl:required</code> attribute on 
     * <code>wsaw:UsingAddressing</code> element 
     * in the WSDL.
     *
     * @return the current required value
     */
    public boolean isRequired() {
        return required;
    }
  // todo remove this  
//    /**
//     * Set the boolean value used to set the
//     * <code>required</code> attribute on 
//     * <code>wsaw:UsingAddressing</code> element
//     * in the WSDL.
//     *
//     * @param required specifies the value that will be used  
//     * for the <code>wsdl:required</code> attribute on      
//     * <code>wsaw:UsingAddressing</code> element
//     * in the WSDL.
//     */ 
//    public void setRequired(boolean required) {
//        this.required = required;
//    }    
}
