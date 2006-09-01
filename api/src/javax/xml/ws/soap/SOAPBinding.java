/*
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javax.xml.ws.soap;


import java.util.Set;
import javax.xml.ws.Binding;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.MessageFactory;

/** The <code>SOAPBinding</code> interface is an abstraction for
 *  the SOAP binding.
 * 
 *  @since JAX-WS 2.0
**/
public interface SOAPBinding extends Binding {

  /**
   * A constant representing the identity of the SOAP 1.1 over HTTP binding.
   */
  public static final String SOAP11HTTP_BINDING = "http://schemas.xmlsoap.org/wsdl/soap/http";

  /**
   * A constant representing the identity of the SOAP 1.2 over HTTP binding.
   */
  public static final String SOAP12HTTP_BINDING = "http://www.w3.org/2003/05/soap/bindings/HTTP/";

  /**
   * A constant representing the identity of the SOAP 1.1 over HTTP binding
   * with MTOM enabled by default.
   */
  public static final String SOAP11HTTP_MTOM_BINDING = "http://schemas.xmlsoap.org/wsdl/soap/http?mtom=true";

  /**
   * A constant representing the identity of the SOAP 1.2 over HTTP binding
   * with MTOM enabled by default.
   */
  public static final String SOAP12HTTP_MTOM_BINDING = "http://www.w3.org/2003/05/soap/bindings/HTTP/?mtom=true";
  
  /**
   * A constant representing the identity of the WS-Addressing feature
   * <p>
   * Enabling this feature on the server will result in the
   * wsaw:UsingAddressing element being added to the wsdl:Binding for 
   * the endpoint and the runtime being capable of responding to
   * WS-Addressing headers.
   * <p>
   * Enabling this feature on the client will cause the JAX-WS runtime
   * to include WS-Addressing headers in SOAP messages.
   *
   * See <a href="http://www.w3.org/TR/2006/REC-ws-addr-core-20060509/">WS-Addressing</a>
   * for more information on WS-Addressing.
   *
   * @since JAX-WS 2.1
   */
  public static final String ADDRESSING_FEATURE = "http://www.w3.org/2005/08/addressing/module";

  /**
   * A constant representing the identity of the MTOM feature.
   *
   * @since JAX-WS 2.1
   */
  public static final String MTOM_FEATURE = "http://www.w3.org/2004/08/soap/features/http-optimization";
  
  
  /** Gets the roles played by the SOAP binding instance.
   *
   *  @return Set<String> The set of roles played by the binding instance.
  **/
  public Set<String> getRoles();

  /** Sets the roles played by the SOAP binding instance.
   *
   *  @param roles    The set of roles played by the binding instance.
   *  @throws WebServiceException On an error in the configuration of
   *                  the list of roles.
  **/
  public void setRoles(Set<String> roles);

  /**
   * Returns <code>true</code> if the use of MTOM is enabled.
   *
   * @return <code>true</code> if and only if the use of MTOM is enabled.
   *
   * @deprecated  {@link Binding#getFeatures} should now be used to determine
   * if MTOM is enabled.
  **/
  
  public boolean isMTOMEnabled();
  
  /**
   * Enables or disables use of MTOM.
   *
   * @param flag   A <code>boolean</code> specifying whether the use of MTOM should
   *               be enabled or disabled.
   *  @throws WebServiceException If the specified setting is not supported
   *                  by this binding instance.
   *
   * @deprecated  {@link Binding#enableFeature} should now be used to enable
   * MTOM.
   *
   **/
  public void setMTOMEnabled(boolean flag);
  
  /**
   * Gets the SAAJ <code>SOAPFactory</code> instance used by this SOAP binding.
   *
   * @return SOAPFactory instance used by this SOAP binding.
  **/
  public SOAPFactory getSOAPFactory();
  
  /**
   * Gets the SAAJ <code>MessageFactory</code> instance used by this SOAP binding.
   *
   * @return MessageFactory instance used by this SOAP binding.
  **/
  public MessageFactory getMessageFactory();
}
