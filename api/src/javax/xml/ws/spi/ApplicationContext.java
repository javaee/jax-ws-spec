/*
 * Copyright 2007 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package javax.xml.ws.spi;

import javax.xml.ws.Endpoint;
import java.util.List;

/**
 * @author Jitendra Kotamraju
 */
public interface ApplicationContext {
    /**
     * This gives list of endpoints in an application. For e.g in
     * servlet container, a war file may contain multiple endpoints.
     *
     * @return list of endpoints in an application
     */
    List<Endpoint> getEndpoints();
}
