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
 * This class represents a W3C Addressing EndpointReferece.
 *
 * @since JAX-WS 2.1
 */
    
// XmlRootElement allows this class to be marshalled on its own
@XmlRootElement(name="EndpointReference",namespace=W3CEndpointReference.NS)
@XmlType(name="EndpointReferenceType",namespace=W3CEndpointReference.NS)
public final class W3CEndpointReference extends EndpointReference {
    // construct an EPR from infoset representation
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
