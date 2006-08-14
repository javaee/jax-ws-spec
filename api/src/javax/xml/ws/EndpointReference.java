/*
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javax.xml.ws;

import java.util.Map;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.ws.spi.Provider;
import javax.xml.namespace.QName;


/**
 * This class represents an Addressing EndpointReference.
 * Concrete implementations of this class will represent
 * an EndpointReference for a particular version of Addressing
 *
 * @see W3CEndpointReference
 * @since JAX-WS 2.1
 **/
//@XmlTransient // to treat this class like Object as far as databinding is concerned (proposed JAXB 2.1 feature)
public abstract class EndpointReference {
    
    /**
     * Factory method to read an EndpointReference from the infoset contained in
     * <code>eprInfoset</code>. This method delegates to the vendor specific
     * implementation of the <code>Provider.readEndpointReference</code> method.
     *
     * @param eprInfoset The <code>EndpointReference<code> infoset to be unmarshalled
     * @return the EndpointReference unmarshalled from <code>eprInfoset</code>
     *    never <code>null</code>
     * @throws WebServiceException 
     *    if an error occurs while creating the 
     *    <code>EndpointReference</code> from the <CODE>eprInfoset</CODE>
     *
     **/
    public static EndpointReference readFrom(Source eprInfoset) {
        return Provider.provider().readEndpointReference(eprInfoset);
    }
    
    /**
     * write this EndpointReference to the specified infoset format
     * @throws WebServiceException 
     *   if there is an error writing the
     *   EndpointReference to the specified <code>result</code>.
     *
     * @throws NullPointerException
     *      If the null <code>result</tt> value is given.
     */
    public abstract void writeTo(Result result);
      
}