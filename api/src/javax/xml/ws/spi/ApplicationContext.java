/*
 * Copyright 2007 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package javax.xml.ws.spi;

import javax.xml.ws.Endpoint;
import java.util.Set;

/**
 * Capturs an application which may consists multiple endpoints.
 * For example, in servlet container a war represents an application
 * and war may contain multiple endpoints. This context allows one
 * endpoint to get information about other endpoint, for example,
 *  address. It also allows all endpoints to share any other
 * runtime information.
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
