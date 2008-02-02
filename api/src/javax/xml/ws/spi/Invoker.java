/*
 * Copyright 2007 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * $Id: Invoker.java,v 1.1.2.1 2008-02-02 01:52:27 jitu Exp $
 */
package javax.xml.ws.spi;

import javax.xml.ws.WebServiceContext;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

/**
 * Invoker hides the detail of calling into application endpoint
 * implementation. Container hands over an implementation of Invoker
 * to JAX-WS runtime, and jax-ws runtime calls {@link #invoke}
 * for a web service invocation. Finally, Invoker does the actual
 * invocation of web service on endpoint instance.
 *
 * @see Provider#createEndpoint(String, Object, Invoker, javax.xml.ws.WebServiceFeature[])
 * @author Jitendra Kotamraju
 * @since 2.2
 */

public interface Invoker {

    /**
     * JAX-WS runtimes calls this method to ask container to inject
     * WebServiceContext on the endpoint instance.
     *
     * @param webServiceContext a holder for MessageContext
     * @throws IllegalAccessException if the injection done
     *         by reflection API throws this exception
     * @throws IllegalArgumentException if the injection done
     *         by reflection API throws this exception
     * @throws InvocationTargetException if the injection done
     *         by reflection API throws this exception
     */
    void inject(WebServiceContext webServiceContext)
    throws IllegalAccessException, IllegalArgumentException, InvocationTargetException;
    
    /**
     * JAX-WS runtime calls this method to do the actual web service
     * invocation on endpoint instance.
     *
     * @param m Method to be invoked on the service
     * @param args Method arguments
     * @return return value of the method
     * @throws IllegalAccessException if the invocation done
     *         by reflection API throws this exception
     * @throws IllegalArgumentException if the invocation done
     *         by reflection API throws this exception
     * @throws InvocationTargetException if the invocation done
     *         by reflection API throws this exception

     * @see Method#invoke
     */
    Object invoke(Method m, Object... args )
    throws  IllegalAccessException, IllegalArgumentException, InvocationTargetException;

}