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

import org.w3c.dom.Element;

/**
 * This class represents a W3C Addressing EndpointReferece.
 *
 * @since JAX-WS 2.1
 */
    
@XmlRootElement(name="EndpointReference",namespace="http://www.w3.org/2005/08/addressing")
  // XmlRootElement allows this class to be marshalled on its own
@XmlType(name="EndpointReferenceType",namespace="http://www.w3.org/2005/08/addressing")
public final class W3CEndpointReference extends EndpointReference {
    // construct an EPR from infoset representation
    public W3CEndpointReference(javax.xml.transform.Source source) {
        throw new RuntimeException("Not yet implemented");
    }
  
    /**
     * write this EndpointReference to the specified infoset format
     * throws WebServiceException if there is an error writing the
     * EndpointReference to the specified <code>result</code>.
     **/
    public void writeTo(javax.xml.transform.Result result){
        throw new RuntimeException("Not yet implemented");        
    }

    // private but necessary properties for databinding
    @XmlElement
    private Address address;
    @XmlElement
    private Elements referenceParameters;
    @XmlElement
    private Elements metadata;
  
    private abstract class AttributeExtensible {
        @XmlAnyAttribute
        Map<QName,String> attributes;
    }
  
    private class Address extends AttributeExtensible {
        @XmlValue
        String uri;
    }
  
    private class Elements extends AttributeExtensible {
        @XmlAnyElement
        List<Element> elements;
    }
    
}
