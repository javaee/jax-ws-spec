/*
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javax.xml.ws.soap;

import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.spi.Provider;

/**
* This feature represents the use of WS-Addressing.
* <p>
* Enabling this feature on the server will result in the
* wsaw:UsingAddressing element being added to the wsdl:Binding for 
* the endpoint and the runtime being capable of responding to
* WS-Addressing headers.
* <p>
* Enabling this feature on the client will cause the JAX-WS runtime
* to include WS-Addressing headers in SOAP messages.
* <p>
* If the web service developer has not explicitly enabled this feature
* it may be automatically enabled if the associated WSDL enables
* WS-Addressing.
* <p>
* The following describes the affects of this feature with respect
* to be enabled or disabled:
* <ul>
*  <li> ENABLED: In this Mode, Addressing will be enabled. 
*       If there is not a WSDL associated with the Endpoint and
*       a WSDL is to be generated, it will be generated with the 
*       wsaw:UsingAddressing element.  At runtime, Addressing headers
*       will be used even if the WSDL declares otherwise.
*  <li> DISABLED: In this Mode, Addressing will be disabled
*       even if an associated WSDL specifies otherwise. At runtime, 
*       Addressing headers will not be used.
* </ul>
*
* See <a href="http://www.w3.org/TR/2006/REC-ws-addr-core-20060509/">WS-Addressing</a>
* for more information on WS-Addressing.
*
* @since JAX-WS 2.1
*/
public class AddressingFeature extends WebServiceFeature {
    /** 
     * Constant value identifying the AddressingFeature
     */
    public static final String ID = "http://www.w3.org/2005/08/addressing/module";
    
    /** 
     * Create an AddressingFeature
     * 
     * @param enabled specifies whether this feature should
     * be enabled or not.
     */
    public AddressingFeature(boolean enabled) {
        this.enabled = enabled;
    }
    
    /**
     * {@inheritDoc}
     */
    public String getID() {
        return ID;
    }
}
