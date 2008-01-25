/*
 * Copyright 2007 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javax.xml.ws.spi.http;

import javax.net.ssl.SSLSession;

/**
 * This class encapsulates a HTTPS request received and a 
 * response to be generated in one exchange and defines
 * the extensions to {@link HttpExchange} that are specific to
 * the HTTPS protocol.
 *
 * @author Jitendra Kotamraju
 * @since 2.2
 */

public interface HttpsExchange extends HttpExchange {

    /**
     * Get the SSLSession for this exchange.
     *
     * @return the SSLSession
     */
    SSLSession getSSLSession ();
}
