/*
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javax.xml.ws.wsaddressing;


import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.spi.Provider;


/**
 * This class is used to build <code>W3CEndpointReference</code>
 * instances. The intended use of this clss is for one
 * web service hosted on a given service provider(container)
 * to create an <code>W3CEndpointReference</code> for a 
 * different web service co-hosted by the same service
 * provider.
 *
 * @since JAX-WS 2.1
 */
public final class W3CEndpointReferenceBuilder {

    public W3CEndpointReferenceBuilder() {
        referenceParameters = new ArrayList<Element>();
        metadata = new ArrayList<Element>();
    }
    
    /**
     * Sets the <code>address</code> to the 
     * <code>W3CEndpointReference</code> instance's
     * <code>wsa:Address</code>.
     *
     * @param address The address of the endpoint to be targeted
     *      by the returned <code>W3CEndpointReference<code>.
     *
     * @return A <code>W3CEndpointReferenceBuilder</code> instance with
     *   the <code>address</code> set to the <code>wsa:Address</code>.
     *
     * @throws java.lang.IllegalArgumentException if <code>address</code>
     * is <code>null</code>.
     */
    public W3CEndpointReferenceBuilder address(String adddress) {
        if (address == null)
            throw new java.lang.IllegalArgumentException("The address cannot be null.");
        this.address = address;
        return this;
    }
    
    /**
     * Sets the <code>serviceName</code> as the 
     * <code>wsaw:ServiceName</code> element in the
     * <code>wsa:Metadata</code> element.
     *
     * @param serviceName The service name of the endpoint to be targeted
     *      by the returned <code>W3CEndpointReference<code>.
     *
     * @return A <code>W3CEndpointReferenceBuilder</code> instance with
     *   the <code>serviceName</code> element added to the
     *  <code>wsa:Metadata</code> element.
     *
     * @throws java.lang.IllegalArgumentException if <code>serviceName</code>
     * is <code>null</code>.
     */
    public W3CEndpointReferenceBuilder serviceName(QName serviceName) {
        if (serviceName == null)
            throw new java.lang.IllegalArgumentException("The serviceName cannot be null.");
        this.serviceName = serviceName;
        return this;
    }    
    
    /**
     * Sets the <code>endpointName</code> as and attribute on 
     * <code>wsaw:ServiceName</code> element in the
     * <code>wsa:Metadata</code> element. This method can only
     * be called after the {@link #serviceName} method has been called.
     *
     * @param endpointName The name of the endpoint to be targeted
     *      by the returned <code>W3CEndpointReference<code>.
     *
     * @return A <code>W3CEndpointReferenceBuilder</code> instance with
     *   the <code>endpointName</code> atrribute added to the
     *  <code>wsaw:ServiceName</code> element in the 
     *  <code>wsa:Metadata</code> element.
     *
     * @throws WebServiceException If the <code>serviceName</code> has not
     *  been set.
     * @throws java.lang.IllegalArgumentException If <code>endpointName</code>
     * is <code>null</code>.
     */
    public W3CEndpointReferenceBuilder endpointName(QName endpointName) {
        if (serviceName == null) {
            throw new WebServiceException("The W3CEndpointReferenceBuilder's serviceName must be set before setting the endpointName: "+endpointName);
        }
        if (endpointName == null)
            throw new java.lang.IllegalArgumentException("The endpointName cannot be null.");
        
        this.endpointName = endpointName;
        return this;
    }    
    
     /**
     * Sets the <code>wsdlDocumentLocation</code> that will be inlined
     * inlined in the <code>W3CEndpointReferenc</code> instance's
     * <code>wsa:Metadata</code>.
     *
     * @param wsdlDocumentLocation The location of the WSDL document to 
     *      be inlined in the <code>wsa:Metadata</code> of the
      *     <code>W3CEndpointReference<code>.
     *
     * @return A <code>W3CEndpointReferenceBuilder</code> instance with
     *   the <code>wsdlDocumentLocation</code> that is to be inlined.
     *
     * @throws java.lang.IllegalArgumentException if <code>wsdlDocumentLocation</code>
     * is <code>null</code>.
     */
    public W3CEndpointReferenceBuilder wsdlDocumentLocation(String wsdlDocumentLocation) {
        if (wsdlDocumentLocation == null)
            throw new java.lang.IllegalArgumentException("The wsdlDocumentLocation cannot be null.");
        this.wsdlDocumentLocation = wsdlDocumentLocation;
        return this;
    } 
    
    /**
     * Adds the <code>referenceParameter</code> to the 
     * <code>W3CEndpointReference</code> instance
     * <code>wsa:ReferenceParameters</code> element.
     *
     * @param referenceParameter The element to be added to the
     *      <code>wsa:ReferenceParameters</code> element.
     *
     * @return A <code>W3CEndpointReferenceBuilder</code> instance with
     *   the <code>referenceParameter</code> added to the 
     *   <code>wsa:ReferenceParameters</code> element.
     *
     * @throws java.lang.IllegalArgumentException if <code>referenceParameter</code>
     * is <code>null</code>.
     */
    public W3CEndpointReferenceBuilder referenceParameter(Element referenceParameter) {
        if (referenceParameter == null)
            throw new java.lang.IllegalArgumentException("The referenceParameter cannot be null.");
        referenceParameters.add(referenceParameter);
        return this;
    }
    
    /**
     * Adds the <code>metadataElement</code> to the 
     * <code>W3CEndpointReference</code> instance's
     * <code>wsa:Metadata</code> element.
     *
     * @param metadataElement The element to be added to the
     *      <code>wsa:Metadata</code> element.
     *
     * @return A <code>W3CEndpointReferenceBuilder</code> instance with
     *   the <code>metadataElement</code> added to the 
     *    <code>wsa:Metadata</code> element.
     *
     * @throws java.lang.IllegalArgumentException if <code>metadataElement</code>
     * is <code>null</code>.
     */
    public W3CEndpointReferenceBuilder metadata(Element metadataElement) {
        if (metadataElement == null)
            throw new java.lang.IllegalArgumentException("The metadataElement cannot be null.");
        metadata.add(metadataElement);
        return this;
    }

    /**
     * Gets a <code>W3CEndpointReference</code> from the accumulated
     * properties set on this <code>W3CEndpointReferenceBuilder</code>
     * instance.
     *
     * @return <code>W3CEndpointReference</code> from the accumulated
     * properties set on this <code>W3CEndpointReferenceBuilder</code>
     * instance.
     *
     * @throws WebServiceException
     *         <UL>
     *             <li>If the <code>address</code> is <code>null</code>.
     *             <li>If the <code>serviceName</code> service is <code>null</code> and the
     *             <code>portName> is NOT <code>null</code>.
     *             <li>If the <code>serviceName</code>is NOT <code>null</code> 
     *             and is not present in the specified WSDL.
     *             <li>If the <code>portName</code> port is not <code>null<code> and it
     *             is not present in <code>serviceName</code> service in the WSDL.
     *             <li>If the <code>wsdlDocumentLocation</code> is NOT <code>null</code> 
     *                 and does not represent a valid WSDL.
     *             <li>If an error occurs while creating the <code>W3CEndpointReference</code>.
     *         </UL>
     * @throws java.lang.IllegalArgumentException
     *     if the <code>address</code> is <code>null</code>.
     */
    public W3CEndpointReference createW3CEndpointReference() {
        if (address == null) {
            throw new WebServiceException("W3CEndpointReferenceBuilder.address MUST not be null when invoking createW3CEndpointReference.");
        }
        return Provider.provider().createW3CEndpointReference(address, 
                serviceName, endpointName, metadata, wsdlDocumentLocation, 
                referenceParameters); 
    }
    
    private String address;
    private List<Element> referenceParameters;
    private List<Element> metadata;
    private QName serviceName;
    private QName endpointName;
    private String wsdlDocumentLocation;
}
