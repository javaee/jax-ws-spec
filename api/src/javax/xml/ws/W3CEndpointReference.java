/*
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javax.xml.ws;


import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.namespace.QName;
import javax.xml.transform.Result;
import javax.xml.transform.Source;

import org.w3c.dom.Element;


/**
 * This class represents a W3C Addressing EndpointReferece which is
 * a remote reference to a web service endpoint that supports the
 * W3C WS-Addressing 1.0 - Core Recommendation.
 * <p>
 * Developers should use this class in their SEIs if they want to 
 * pass/return endpoint references that represent the W3C WS-Addressing
 * recommendation.
 * <p>
 * JAXB will use the JAXB annotations and bind this class to XML infoset
 * that is consistent with that defined by WS-Addressing.  See 
 * http://www.w3.org/TR/2006/REC-ws-addr-core-20060509/
 * for more information on WS-Addressing EndpointReferences.
 *
 * @since JAX-WS 2.1
 */
    
// XmlRootElement allows this class to be marshalled on its own
@XmlRootElement(name="EndpointReference",namespace=W3CEndpointReference.NS)
@XmlType(name="EndpointReferenceType",namespace=W3CEndpointReference.NS)
public final class W3CEndpointReference extends EndpointReference {
    /**
     * construct an EPR from infoset representation
     * 
     * @param source A source object containing valid XmlInfoset
     * instance consistent with the W3C WS-Addressing Core 
     * recommendation.
     *
     * @throws WebServiceException 
     *   if the source does not contain a valid W3C WS-Addressing
     *   EndpointReference.
     * @throws NullPointerException
     *   if the <code>null</code> <code>source</code> value is given
     */
    public W3CEndpointReference(Source source) {
        throw new RuntimeException("Not yet implemented");
    }
  
    /**
     * {@inheritDoc}
     **/
    public void writeTo(Result result){
        throw new RuntimeException("Not yet implemented");        
    }

    // private but necessary properties for databinding
    @XmlElement(name="Address",namespace=NS)
    private Address address;
    @XmlElement(name="ReferenceParameters",namespace=NS)
    private Elements referenceParameters;
    @XmlElement(name="Metadata",namespace=NS)
    private Elements metadata;
  
    private class Address {
        @XmlValue
        String uri;
        @XmlAnyAttribute
        Map<QName,String> attributes;
    }

  
    private class Elements {
        @XmlAnyElement
        List<Element> elements;
        @XmlAnyAttribute
        Map<QName,String> attributes;
    }

    protected static final String NS = "http://www.w3.org/2005/08/addressing";
}
