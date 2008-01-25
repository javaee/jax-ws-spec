/*
 * Copyright 2007 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javax.xml.ws.spi.http;

import java.security.Principal;

/**
 * Represents a user authenticated by HTTP Basic or Digest
 * authentication.
 *
 * @author Jitendra Kotamraju
 * @since 2.2
 */
public interface HttpPrincipal extends Principal {

    /**
     * returns the realm this object was created with.
     */
    String getRealm();

}

