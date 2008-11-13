/*
 * Copyright 2007 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package javax.xml.ws.spi;

import javax.xml.ws.Endpoint;
import javax.xml.ws.spi.http.HttpContext;
import java.util.List;
import java.util.Map;

/**
 * Capturs an application which may consists multiple endpoints.
 * For example, in servlet container a war represents an application
 * and war may contain multiple endpoints. This context allows one
 * endpoint to get information about other endpoint.
 *
 * @author Jitendra Kotamraju
 */
public abstract class ApplicationContext {
    /**
     * This gives list of endpoints in an application. For e.g in
     * servlet container, a war file may contain multiple endpoints.
     *
     * @return list of endpoints in an application
     */
    public abstract Map<Endpoint, HttpContext> getEndpoints();

    /**
     * Deploy all the endpoints in this context at their
     * respective {@link HttpContext}
     */
    public void deploy() {
        Provider.provider().deploy(this);
    }
}
