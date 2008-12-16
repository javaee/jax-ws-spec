/*
 * Copyright 2007 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javax.xml.ws;

import javax.xml.ws.spi.WebServiceFeatureAnnotation;
import javax.xml.ws.soap.Addressing;
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
 * {@link WebServiceFeatureAnnotation} annotations
 * such as {@link Addressing} MAY be used in
 * conjunction with the <code>WebServiceRefs</code> annotation.  
 * When this happens, only the enclosed {@link WebServiceRef} 
 * annotations that specify a service endpoint interface (SEI)
 * proxy <code>type</code> will be affected.  In this 
 * case the SEI proxies MUST honor the specified
 * <code>WebServiceFeatures</code>.  
 * <p>
 * For example, in the code below, the <code>StockQuoteProvider</code> proxy MUST
 * have WS-Addressing enabled as specifed by the {@link Addressing}
 * annotation.  However, the <code>StockQuoteService</code> service object will be 
 * unaffected by the <code>Addressing</code> annotation.
 *
 * <code>
 * <pre>
 *    &#64;Addressing
 *    &#64;WebServiceRefs({&#64;WebServiceRef(name="service/stockquoteservice", value=StockQuoteService.class),
 *                     &#64;WebServiceRef(name="service/stockquoteprovider", type=StockQuoteProvider.class, value=StockQuoteService.class})
 *    public class MyClient {
 *        void init() {
 *            Context ic = new InitialContext();
 *            StockQuoteService service = (StockQuoteService) ic.lookup("java:comp/env/service/stockquoteservice");
 *            StockQuoteProvider port = (StockQuoteProvider) ic.lookup("java:comp/env/service/stockquoteprovider");
 *            ...
 *       }
 *       ...
 *    }
 * </pre>
 * </code>
 *
 * @see WebServiceRef
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
