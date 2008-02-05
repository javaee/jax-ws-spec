/*
 * Copyright 2007 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javax.xml.ws.spi.http;

import javax.xml.ws.spi.ApplicationContext;
import java.util.*;

/**
 * HttpContext represents a mapping between the root URI path of a web
 * service to a {@link HttpHandler} which is invoked to handle requests
 * destined for that path on the associated container.
 * <p>
 * It is the responsibility of a container to match request URIs to
 * HttpContext objects.
 * <p>
 *
 * @author Jitendra Kotamraju
 * @since 2.2
 */
public interface HttpContext {

    /**
     * Returns the handler for this context
     *
     * @return the HttpHandler for this context
     */
    HttpHandler getHandler();

    /**
     * Sets the handler for this context.
     *
     * @param h the handler to set for this context
     */
    void setHandler(HttpHandler h);

    /**
     * Returns the path for this context.
     * 
     * @return this context's path
     */
    String getPath();

    /**
     * Returns a bag of properties for container's configuration
     * and other data that can be used by jax-ws runtime. Every
     * property stored in this Map will be visible to
     * every {@link HttpExchange} processed by this context
     *
     * @return property bag of the context
     */
    Map<String,Object> getProperties();

    /**
     * Returns the application context in which this http context is part of.
     *
     * @return application context in which this context is part of
     */
    ApplicationContext getApplicationContext();

}
