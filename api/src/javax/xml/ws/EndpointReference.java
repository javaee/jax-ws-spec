/*
 * Copyright 2004 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javax.xml.ws;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.ws.spi.Provider;

/**
 * This class represents an Addressing EndpointReferece.
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
    * <code>eprInfoset</code>.
    * throws WebServiceException if there is an error creating the
    * EndpointReference from the specified <code>eprInfoset</code>.
    **/
    public static EndpointReference readFrom(Source eprInfoset) {
        return Provider.provider().readEndpointReference(eprInfoset);
    }

    /**
    * write this EndpointReference to the specified infoset format
    * throws WebServiceException if there is an error writing the
    * EndpointReference to the specified <code>result</code>.
    **/
    public abstract void writeTo(Result result);
}