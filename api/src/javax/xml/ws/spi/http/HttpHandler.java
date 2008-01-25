/*
 * Copyright 2007 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javax.xml.ws.spi.http;

import java.io.IOException;

/**
 * A handler which is invoked to process HTTP exchanges. Each
 * HTTP exchange is handled by one of these handlers.
 *
 * @author Jitendra Kotamraju
 * @since 2.2
 */
public interface HttpHandler {
    /**
     * Handle the given request and generate an appropriate response.
     * See {@link HttpExchange} for a description of the steps
     * involved in handling an exchange.
     *
     * @param exchange the exchange containing the request from the 
     *      client and used to send the response
     * @throws IOException when an I/O error happens during request
     *      handling
     */
    void handle(HttpExchange exchange) throws IOException;
}
