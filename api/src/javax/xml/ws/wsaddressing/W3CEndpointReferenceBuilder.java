/*
 * Copyright 2007 Sun Microsystems, Inc. All rights reserved.
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
 * instances. The intended use of this clsss is for
 * an application component, for example a factory component,
 * to create an <code>W3CEndpointReference</code> for a
 * web service endpoint published by the same 
 * Java EE application. It can also be used to create
 * <code>W3CEndpointReferences</code> for an Java SE based
 * endpoint by providing the <code>address</code> property.
 * <p>
 * When creating a <code>W3CEndpointReference</code> for an
 * endpoint that is not published by the same Java EE application,
 * the <code>address</code> property MUST be specified.  
 * <p>
 * When creating a <code>W3CEndpointReference</code> for an endpoint 
 * published by the same Java EE application, the <code>address</code>
 * property MAY be <code>null</code> but then the <code>serviceName</code>
 * and <code>endpointName</code> MUST specify an endpoint published by
 * the same Java EE application.
 * <p>
 * When the <code>wsdlDocumentLocation</code> is specified it MUST refer
 * to a valid WSDL document and the <code>serviceName</code> and
 * <code>endpointName</code> (if specified) MUST match a service and port 
 * in the WSDL document.
 *
 * @since JAX-WS 2.1
 */
public final class W3CEndpointReferenceBuilder {
    /**
     * Creates a new <code>W3CEndpointReferenceBuilder</code> instance.
     */
    public W3CEndpointReferenceBuilder() {
        referenceParameters = new ArrayList<Element>();
        metadata = new ArrayList<Element>();
    }
    
    /**
     * Sets the <code>address</code> to the
     * <code>W3CEndpointReference</code> instance's
     * <code>wsa:Address</code>.
     * <p>
     * The <code>address</code> MUST be set to a non-<code>null</code>
     * value when building a <code>W3CEndpointReference</code> for a 
     * web service endpoint that is not published by the same
     * Java EE application or when running on Java SE.
     *
     * @param address The address of the endpoint to be targeted
     *      by the returned <code>W3CEndpointReference<code>.
     *
     * @return A <code>W3CEndpointReferenceBuilder</code> instance with
     *   the <code>address</code> set to the <code>wsa:Address</code>.
     */
    public W3CEndpointReferenceBuilder address(String address) {
        this.address = address;
        return this;
    }
    
    /**
     * Sets the <code>serviceName</code> as the
     * <code>wsam:ServiceName</code> element in the
     * <code>wsa:Metadata</code> element.
     *
     * See <a href="http://www.w3.org/TR/2007/REC-ws-addr-metadata-20070904/#refmetadatfromepr">
     * Web Services Addressing 1.0 - Metadata</a> for more details.
     *
     * @param serviceName The service name of the endpoint to be targeted
     *      by the returned <code>W3CEndpointReference<code>.  This property
     *      may also be used with the <code>endpointName</code> (portName) 
     *      property to lookup the <code>address</code> of a web service 
     *      endpoint that is published by the same Java EE application.
     *
     * @return A <code>W3CEndpointReferenceBuilder</code> instance with
     *   the <code>serviceName</code> as <code>wsam:ServiceName</code>
     *   element added to the <code>wsa:Metadata</code> element
     *
     */
    public W3CEndpointReferenceBuilder serviceName(QName serviceName) {
        this.serviceName = serviceName;
        return this;
    }
    
    /**
     * Sets the <code>endpointName</code> as
     * <code>wsam:ServiceName/@EndpointName</code> in the
     * <code>wsa:Metadata</code> element. This method can only be called
     * after the {@link #serviceName} method has been called.
     * <p>
     * See <a href="http://www.w3.org/TR/2007/REC-ws-addr-metadata-20070904/#refmetadatfromepr">
     * Web Services Addressing 1.0 - Metadata</a> for more details.
     *
     * @param endpointName The name of the endpoint to be targeted
     *      by the returned <code>W3CEndpointReference<code>. The 
     *      <code>endpointName</code> (portName) property may also be
     *      used with the <code>serviceName</code> property to lookup 
     *      the <code>address</code> of a web service 
     *      endpoint published by the same Java EE application.
     *
     * @return A <code>W3CEndpointReferenceBuilder</code> instance with
     *   the <code>endpointName</code> as
     * <code>wsam:ServiceName/@EndpointName</code> in the
     * <code>wsa:Metadata</code> element.
     *
     * @throws IllegalStateException, if the <code>serviceName</code>
     * has not been set.
     * @throws IllegalArgumentException, if the <code>endpointName</code>'s
     * Namespace URI doesn't match <code>serviceName</code>'s Namespace URI
     *
     */
    public W3CEndpointReferenceBuilder endpointName(QName endpointName) {
        if (serviceName == null) {
            throw new IllegalStateException("The W3CEndpointReferenceBuilder's serviceName must be set before setting the endpointName: "+endpointName);
        }
        
        this.endpointName = endpointName;
        return this;
    }
    
    /**
     * Sets the <code>wsdlDocumentLocation</code> that will be inlined
     * as <code>wsa:Metadata/@wsdli:wsdlLocation</code>.
     *
     * <p>
     * See <a href="http://www.w3.org/TR/2007/REC-ws-addr-metadata-20070904/#refmetadatfromepr">
     * Web Services Addressing 1.0 - Metadata</a> for more details.
     *
     * @param wsdlDocumentLocation The location of the WSDL document to
     *      be inlined in the <code>wsa:Metadata</code> of the
     *     <code>W3CEndpointReference<code>.
     *
     * @return A <code>W3CEndpointReferenceBuilder</code> instance with
     *   the <code>wsdlDocumentLocation</code> that is to be inlined.
     *
     */
    public W3CEndpointReferenceBuilder wsdlDocumentLocation(String wsdlDocumentLocation) {
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
     * Builds a <code>W3CEndpointReference</code> from the accumulated
     * properties set on this <code>W3CEndpointReferenceBuilder</code>
     * instance.
     * <p>
     * This method can be used to create a <code>W3CEndpointReference</code>
     * for any endpoint by specifying the <code>address</code> property along
     * with any other desired properties.  This method
     * can also be used to create a <code>W3CEndpointReference</code> for
     * an endpoint that is published by the same Java EE application.
     * This method can automatically determine the <code>address</code> of 
     * an endpoint published by the same Java EE application that is identified by the 
     * <code>serviceName</code> and 
     * <code>endpointName</code> properties.  If the <code>address</code> is 
     * <code>null</code> and the <code>serviceName</code> and 
     * <code>endpointName</code> 
     * do not identify an endpoint published by the same Java EE application, a 
     * <code>java.lang.IllegalStateException</code> MUST be thrown.
     * 
     *
     * @return <code>W3CEndpointReference</code> from the accumulated
     * properties set on this <code>W3CEndpointReferenceBuilder</code>
     * instance. This method never returns <code>null</code>.
     *
     * @throws IllegalStateException
     *     <ul>
     *        <li>If the <code>address</code>, <code>serviceName</code> and
     *            <code>endpointName</code> are all <code>null</code>.
     *        <li>If the <code>serviceName</code> service is <code>null</code> and the
     *            <code>endpointName</code> is NOT <code>null</code>.
     *        <li>If the <code>address</code> property is <code>null</code> and
     *            the <code>serviceName</code> and <code>endpointName</code> do not
     *            specify a valid endpoint published by the same Java EE
     *            application.
     *        <li>If the <code>serviceName</code> is NOT <code>null</code>
     *             and is not present in the specified WSDL.
     *        <li>If the <code>endpointName</code> port is not <code>null<code> and it
     *             is not present in <code>serviceName</code> service in the WSDL.
     *        <li>If the <code>wsdlDocumentLocation</code> is NOT <code>null</code>
     *            and does not represent a valid WSDL.
     *     </ul>
     * @throws WebServiceException If an error occurs while creating the 
     *                             <code>W3CEndpointReference</code>.
     *       
     */
    public W3CEndpointReference build() {
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
