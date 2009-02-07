/*
 * Copyright 2007 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package javax.xml.ws.spi;

import javax.xml.ws.Endpoint;
import java.util.Set;

/**
 * Represents an application which may consist multiple endpoints.
 * For example, in servlet application's war represents an application
 * and war may contain multiple endpoints. This context allows one
 * endpoint to get information about other endpoint, for example,
 * address of an endpoint. It also allows all endpoints to share any other
 * runtime information.
 *
 * Container or its extensions typically create this object during the
 * deployment of a JAX-WS application.
 *
 * @author Jitendra Kotamraju
 * @since JAX-WS 2.2
 */
public abstract class ApplicationContext {

    /**
     * This gives list of endpoints in an application. For e.g in
     * servlet container, a war file may contain multiple endpoints.
     *
     * @return list of all endpoints in an application
     */
    public abstract Set<Endpoint> getEndpoints();

}
