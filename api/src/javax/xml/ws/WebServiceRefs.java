/*
 * Copyright 2007 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javax.xml.ws;

import java.lang.annotation.Documented;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

/**
 * The <code>WebServiceRefs</code> annotation allows
 * multiple web service references to be declared at the
 * class level.
 *
 * <p>
 * <u>{@link javax.xml.ws.spi.WebServiceFeatureAnnotation} annotations
 * such as {@link javax.xml.ws.soap.Addressing} MAY be used in 
 * conjunction with the <code>WebServiceRefs</code> annotation.  
 * When this happens, only the enclosed {@link WebServiceRef} 
 * annotations that specify a service endpoint interface (SEI)
 * proxy <code>type</code> will be affected.  In this 
 * case the SEI proxies MUST honor the specified
 * <code>WebServiceFeatures</code>.  
 * <p>
 * For example, in the code below, the <code>StockQuoteProvider</code> proxy MUST
 * have WS-Addressing enabled as specifed by the {@link javax.xml.ws.soap.Addressing}
 * annotation.  However, the <code>StockQuoteService</code> service object will be 
 * unaffected by the <code>Addressing</code> annotation.
 *
 * <code>
 * <pre>
 *    &#64;Addressing
 *    &#64;WebServiceRefs({&#64;WebServiceRef(name="service/stockquoteservice", value=StockQuoteService.class),
 *                         &#64;WebServiceRef(name="service/stockquoteprovider", type=StockQuoteProvider.class, type=StockQuoteService.class})
 *    public class MyClient {
 *    static StockQuoteService service;
 *    static StockQuoteProvider port;
 *
 *       protected void init() {
 *          try {
 *             Context ic = new InitialContext();
 *             service = (StockQuoteService) ic.lookup("java:comp/env/service/stockquoteservice");
 *             port = (StockQuoteProvider) ic.lookup("java:comp/env/service/stockquoteprovider");
 *          } catch(Throwable t) {
 *             ...
 *          }   
 *       }
 *       ...
 *    }
 * </pre>
 * </code>
 * <p>
 * <code>WebServiceFeature</code> annotations such as {@link javax.xml.ws.soap.Addressing}
 * MAY also be present on the service endpoint interface (SEI).  When this happens, 
 * the <code>WebServiceFeature</code> annotations from the SEI and the ones specified
 * with the <code>WebServiceRef</code> annotatSion MUST be honored.  If there is a
 * conflict between the two sets of annotations, the annotations 
 * used in conjunction with the <code>WebServiceRef</code> take precedence over
 * the annotations used on the SEI.
 * </u>
 *
 * @see javax.xml.ws.WebServiceRef
 * @since 2.0
 */

@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface WebServiceRefs {
   /**
    * Array used for multiple web service reference declarations.
    */
   WebServiceRef[] value();
}
