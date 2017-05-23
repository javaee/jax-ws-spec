/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2005-2017 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://oss.oracle.com/licenses/CDDL+GPL-1.1
 * or LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package javax.xml.ws;

import java.util.Map;
import javax.xml.ws.wsaddressing.W3CEndpointReference;

/**
 * The {@code BindingProvider} interface provides access to the
 * protocol binding and associated context objects for request and
 * response message processing.
 *
 * @since 1.6, JAX-WS 2.0
 *
 * @see javax.xml.ws.Binding
 **/
public interface BindingProvider {
    /**
     * Standard property: User name for authentication.
     * <p>Type: {@code java.lang.String}
     **/
    public static final String USERNAME_PROPERTY =
            "javax.xml.ws.security.auth.username";

    /**
     * Standard property: Password for authentication.
     * <p>Type: {@code java.lang.String}
     **/
    public static final String PASSWORD_PROPERTY =
            "javax.xml.ws.security.auth.password";

    /**
     * Standard property: Target service endpoint address. The
     * URI scheme for the endpoint address specification MUST
     * correspond to the protocol/transport binding for the
     * binding in use.
     * <p>Type: {@code java.lang.String}
     **/
    public static final String ENDPOINT_ADDRESS_PROPERTY =
            "javax.xml.ws.service.endpoint.address";

    /**
     * Standard property: This boolean property is used by a service
     * client to indicate whether or not it wants to participate in
     * a session with a service endpoint. If this property is set to
     * {@code true}, the service client indicates that it wants the session
     * to be maintained. If set to {@code false}, the session is not maintained.
     * The default value for this property is {@code false}.
     * <p>Type: {@code java.lang.Boolean}
     **/
    public static final String SESSION_MAINTAIN_PROPERTY =
            "javax.xml.ws.session.maintain";

    /**
     * Standard property for SOAPAction. This boolean property
     * indicates whether or not the value of the
     * {@code javax.xml.ws.soap.http.soapaction.uri} property
     * is used for the value of the SOAPAction. The
     * default value of this property is {@code false} indicating
     * that the
     * {@code javax.xml.ws.soap.http.soapaction.uri} property
     * is not used for the value of the SOAPAction, however,
     * if WS-Addressing is enabled, the default value is
     * {@code true}.
     *
     * <p>Type: {@code java.lang.Boolean}
     **/
    public static final String SOAPACTION_USE_PROPERTY =
            "javax.xml.ws.soap.http.soapaction.use";

    /**
     * Standard property for SOAPAction. Indicates the SOAPAction
     * URI if the {@code javax.xml.ws.soap.http.soapaction.use}
     * property is set to {@code true}. If WS-Addressing
     * is enabled, this value will also be used for the value of the
     * WS-Addressing Action header.  If this property is not set,
     * the default SOAPAction and WS-Addressing Action will be sent.
     *
     * <p>Type: {@code java.lang.String}
     **/
    public static final String SOAPACTION_URI_PROPERTY =
            "javax.xml.ws.soap.http.soapaction.uri";

    /**
     * Get the context that is used to initialize the message context
     * for request messages.
     *
     * Modifications to the request context do not affect the message context of
     * either synchronous or asynchronous operations that have already been
     * started.
     *
     * @return The context that is used in processing request messages.
     **/
    Map<String, Object> getRequestContext();

    /**
     * Get the context that resulted from processing a response message.
     *
     * The returned context is for the most recently completed synchronous
     * operation. Subsequent synchronous operation invocations overwrite the
     * response context. Asynchronous operations return their response context
     * via the Response interface.
     *
     * @return The context that resulted from processing the latest
     * response messages.
     **/
    Map<String, Object> getResponseContext();

    /**
     * Get the Binding for this binding provider.
     *
     * @return The Binding for this binding provider.
     **/
    Binding getBinding();



    /**
     * Returns the {@code EndpointReference} associated with
     * this {@code BindingProvider} instance.
     * <p>
     * If the Binding for this {@code bindingProvider} is
     * either SOAP1.1/HTTP or SOAP1.2/HTTP, then a
     * {@code W3CEndpointReference} MUST be returned.
     *
     * @return EndpointReference of the target endpoint associated with this
     * {@code BindingProvider} instance.
     *
     * @throws java.lang.UnsupportedOperationException If this
     * {@code BindingProvider} uses the XML/HTTP binding.
     *
     * @see W3CEndpointReference
     *
     * @since 1.6, JAX-WS 2.1
     */
    public EndpointReference getEndpointReference();


    /**
     * Returns the {@code EndpointReference} associated with
     * this {@code BindingProvider} instance.  The instance
     * returned will be of type {@code clazz}.
     *
     * @param <T> the type of {@code EndpointReference}
     * @param clazz Specifies the type of {@code EndpointReference}
     * that MUST be returned.

     * @return EndpointReference of the target endpoint associated with this
     * {@code BindingProvider} instance. MUST be of type
     * {@code clazz}.

     * @throws WebServiceException If the Class {@code clazz}
     * is not supported by this implementation.
     * @throws java.lang.UnsupportedOperationException If this
     * {@code BindingProvider} uses the XML/HTTP binding.
     *
     * @since 1.6, JAX-WS 2.1
     */
    public <T extends EndpointReference> T getEndpointReference(Class<T> clazz);
}
