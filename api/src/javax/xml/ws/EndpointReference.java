/*
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javax.xml.ws;

import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.ws.spi.Provider;
import javax.xml.ws.Service;
import javax.xml.namespace.QName;


/**
 * This class represents an WS-Addressing EndpointReference
 * which is a remote reference to a web service endpoint. 
 * See <a href="http://www.w3.org/TR/2006/REC-ws-addr-core-20060509/">
 * WS-Addressing</a> 
 * for more information on WS-Addressing EndpointReferences.
 * <p>  
 * This class is immutable as the typical web service developer
 * need not be concerned with its contents.  The web service
 * developer should use this class strictly as a mechanism to 
 * reference a remote web service endpoint. See the {@link Service} APIs 
 * that clients can use to that utilize an <code>EndpointReference</code>. 
 * See the {@link javax.xml.ws.Endpoint}, and 
 * {@link javax.xml.ws.BindingProvider} APIs on how 
 * <code>EndpointReferences</code> can be created for published 
 * endpoints.
 * <p>
 * Concrete implementations of this class will represent
 * an <code>EndpointReference</code> for a particular version of Addressing.
 * For example the {@link W3CEndpointReference} is for use
 * with W3C WS-Addressing 1.0 - Core Recommendation. 
 * If JAX-WS implementors need to support different versions
 * of addressing, they should write their own 
 * <code>EndpointReference</code> subclass for that version.
 * This will allow a JAX-WS implementation to create
 * vendor specific <code>EndpointReferences</code> that that
 * vendor can use to flag a different version of
 * addressing.
 * <p>
 * Web service developers that wish to pass or return 
 * <code>EndpointReferences</code> in Java methods in an
 * SIE should use
 * concrete instances of an <code>EndpointReference</code> such
 * as the <code>W3CEndpointReferendce</code>.  This way the 
 * schema mapped from the SEI will be more descriptive of the
 * type of endpoint reference being passed.
 * <p>
 * JAX-WS implementors are expected to extract the XML infoset
 * from an <CODE>EndpointReferece</CODE> using the 
 * <code>{@link EndpointReference#writeTo}</code>
 * method.
 * <p>
 * JAXB will bind this class to xs:anyType. If a better binding
 * is desired, web services developers should use a concrete
 * subclass such as {@link W3CEndpointReference}.
 *
 * @see W3CEndpointReference
 * @see Service
 * @since JAX-WS 2.1
 */
//@XmlTransient // to treat this class like Object as far as databinding is concerned (proposed JAXB 2.1 feature)
public abstract class EndpointReference {
    
    /**
     * Factory method to read an EndpointReference from the infoset contained in
     * <code>eprInfoset</code>. This method delegates to the vendor specific
     * implementation of the {@link javax.xml.ws.spi.Provider#readEndpointReference} method.
     *
     * @param eprInfoset The <code>EndpointReference<code> infoset to be unmarshalled
     *
     * @return the EndpointReference unmarshalled from <code>eprInfoset</code>
     *    never <code>null</code>
     * @throws WebServiceException 
     *    if an error occurs while creating the 
     *    <code>EndpointReference</code> from the <CODE>eprInfoset</CODE>
     * @throws NullPointerException 
     *     if the null <code>eprInfoset</tt> value is given.     
     */
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
    
    /** 
     * The getPort method returns a stub/proxy.
     * The parameter  <code>serviceEndpointInterface</code> specifies
     * the service endpoint interface that is supported by the
     * returned proxy.
     * The parameter <code>endpointReference</code> specifies the
     * endpoint that will be invoked by the returned stub.
     * In the implementation of this method, the JAX-WS
     * runtime system takes the responsibility of selecting a protocol
     * binding (and a port) and configuring the proxy accordingly from
     * the WSDL Metadata from the <code>EndpointReference</code>.
     * The returned proxy should not be reconfigured by the client.

     *
     * @param serviceEndpointInterface Service endpoint interface
     * @return Object instance that supports the
     *                  specified service endpoint interface
     * @throws WebServiceException
     *                  <UL>
     *                  <LI>If there is an error during creation
     *                      of the proxy
     *                  <LI>If there is any missing WSDL metadata
     *                      as required by this method such as
     *                      the wsaw:ServiceName/@EndpointName
     *                  <LI>Optionally, if this
     *                      <code>endpointReference</code>
     *                      is illegal
     *                  <LI>Optionally, if an illegal
     *                      <code>serviceEndpointInterface</code>
     *                      is specified
     *                   </UL>
     *
     **/
    public <T> T getPort(Class<T> serviceEndpointInterface) {
        return Provider.provider().getPort(this, serviceEndpointInterface);
    }    
    
    /** 
     * Creates a <code>Dispatch</code> instance for use with objects of
     * the users choosing.
     *
     * @param type The class of object used to messages or message
     * payloads. Implementations are required to support
     * javax.xml.transform.Source and javax.xml.soap.SOAPMessage.
     * @param mode Controls whether the created dispatch instance is message
     * or payload oriented, i.e. whether the user will work with complete
     * protocol messages or message payloads. E.g. when using the SOAP
     * protocol, this parameter controls whether the user will work with
     * SOAP messages or the contents of a SOAP body. Mode must be MESSAGE
     * when type is SOAPMessage.
     *
     * @return Dispatch instance
     * @throws WebServiceException If any error in the creation of
     *                  the <code>Dispatch</code> object
     * @see javax.xml.transform.Source
     * @see javax.xml.soap.SOAPMessage
     *
     **/
    public <T> Dispatch<T> createDispatch(Class<T> type, Service.Mode mode) {
        return Provider.provider().createDispatch(this, type, mode);
    }    
    
    /** 
     * Creates a <code>Dispatch</code> instance for use with JAXB
     * generated objects.
     *
     * @param endpointReference  The <code>EndpointReference</code>
     * for the target service endpoint
     * @param context The JAXB context used to marshall and unmarshall
     * messages or message payloads.
     * @param mode Controls whether the created dispatch instance is message
     * or payload oriented, i.e. whether the user will work with complete
     * protocol messages or message payloads. E.g. when using the SOAP
     * protocol, this parameter controls whether the user will work with
     * SOAP messages or the contents of a SOAP body.
     *
     * @return Dispatch instance
     * @throws ServiceException If any error in the creation of
     *                   the <code>Dispatch</code> object
     *
     * @see javax.xml.bind.JAXBContext
     *
     **/
    public Dispatch<Object> createDispatch(JAXBContext context, Service.Mode mode) {
        return Provider.provider().createDispatch(this, context, mode);
    }
          
}
