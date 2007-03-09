/*
 * Copyright 2007 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javax.xml.ws;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The <code>FaultAction</code> annotation is used inside an <a href="Action.html">
 * Action</a> annotation to allow an explicit association of  {@add a WS-Addressing }<code>Action</code> message
 * addressing property with the <code>fault</code> messages of the WSDL operation mapped from
 * the exception class.
  * <p>
 * {@add In this version of JAX-WS there is no standard way to specify 
 * <code>Action</code> values in a WSDL and there is no standard default value.  It is intended that, 
 * after the W3C WG on WS-Addressing has defined these items in a recommendation,
 * a future version of JAX-WS will require the new standards.}
 * <p>{@delete 
 * The <code>fault</code> message in the generated WSDL operation mapped for <code>className</code> 
 * class contains explicit <code>wsaw:Action</code> attribute.}
 *
 * <p>{@delete 
 * <b>Example 1</b>: Specify explicit values for <code>Action</code> message addressing 
 * property for the <code>input</code>, <code>output</code> and <code>fault</code> message 
 * if the Java method throws only one service specific exception.}
 * 
 * <pre>{@delete 
 * &#64;javax.jws.WebService
 * public class AddNumbersImpl {
 *     &#64;javax.xml.ws.Action(
 *         input=&quot;http://example.com/inputAction&quot;,
 *         output=&quot;http://example.com/outputAction&quot;,
 *         fault = {
 *             &#64;javax.xml.ws.FaultAction(className=AddNumbersException.class, value=&quot;http://example.com/faultAction&quot;)
 *         })
 *     public int addNumbers(int number1, int number2) 
 *         throws AddNumbersException {
 *         return number1 + number2;
 *     }
 * }}
 * </pre>
 * 
 * {@delete The generated WSDL looks like:}
 * 
 * <pre>{@delete
 *   &lt;definitions targetNamespace=&quot;http://example.com/numbers&quot; ...&gt;
 *   ...
 *     &lt;portType name=&quot;AddNumbersPortType&quot;&gt;
 *       &lt;operation name=&quot;AddNumbers&quot;&gt;
 *         &lt;input message=&quot;tns:AddNumbersInput&quot; name=&quot;Parameters&quot;
 *           wsaw:Action=&quot;http://example.com/inputAction&quot;/&gt;
 *        &lt;output message=&quot;tns:AddNumbersOutput&quot; name=&quot;Result&quot;
 *          wsaw:Action=&quot;http://example.com/outputAction&quot;/&gt;
 *        &lt;fault message=&quot;tns:AddNumbersException&quot; name=&quot;AddNumbersException&quot;
 *          wsaw:Action=&quot;http://example.com/faultAction&quot;/&gt;
 *       &lt;/operation&gt;
 *     &lt;portType&gt;
 *   ...
 *   &lt;definitions&gt;}
 * </pre>
 *
 * <p>{@delete 
 * Example 2: Here is an example that shows how to specify explicit values for <code>Action</code> 
 * message addressing property if the Java method throws only one service specific exception, 
 * without specifying the values for <code>input</code> and <code>output</code> messages.
 * }
 * <pre>{@delete 
 * &#64;javax.jws.WebService
 * public class AddNumbersImpl {
 *     &#64;javax.xml.ws.Action(
 *         fault = {
 *             &#64;javax.xml.ws.FaultAction(className=AddNumbersException.class, value=&quot;http://example.com/faultAction&quot;)
 *         })
 *     public int addNumbers(int number1, int number2) 
 *         throws AddNumbersException {
 *         return number1 + number2;
 *     }
 * }}
 * </pre>
 * 
 * {@delete The generated WSDL looks like:}
 * 
 * <pre>{@delete 
 *   &lt;definitions targetNamespace=&quot;http://example.com/numbers&quot; ...&gt;
 *   ...
 *     &lt;portType name=&quot;AddNumbersPortType&quot;&gt;
 *       &lt;operation name=&quot;AddNumbers&quot;&gt;
 *         &lt;input message=&quot;tns:AddNumbersInput&quot; name=&quot;Parameters&quot;/&gt;
 *         &lt;output message=&quot;tns:AddNumbersOutput&quot; name=&quot;Result&quot;/&gt;
 *         &lt;fault message=&quot;tns:addNumbersFault&quot; name=&quot;InvalidNumbers&quot;
 *           wsa:Action=&quot;http://example.com/addnumbers/fault&quot;/&gt;
 *       &lt;/operation&gt;
 *     &lt;portType&gt;
 *   ...
 *   &lt;definitions&gt;}
 * </pre>
 * 
 * <p>{@delete 
 * Example 3: Here is an example that shows how to specify explicit values for <code>Action</code> 
 * message addressing property if the Java method throws more than one service specific exception.
 * }
 * <pre> {@delete 
 * &#64;javax.jws.WebService
 * public class AddNumbersImpl {
 *     &#64;javax.xml.ws.Action(
 *         fault = {
 *             &#64;javax.xml.ws.FaultAction(className=AddNumbersException.class, value=&quot;http://example.com/addFaultAction&quot;)
 *             &#64;javax.xml.ws.FaultAction(className=TooBigNumbersException.class, value=&quot;http://example.com/toobigFaultAction&quot;)
 *         })
 *     public int addNumbers(int number1, int number2) 
 *         throws AddNumbersException, TooBigNumbersException {
 *         return number1 + number2;
 *     }
 * }}
 * </pre>
 * 
 * {@delete The generated WSDL looks like:}
 * 
 * <pre>{@delete 
 *   &lt;definitions targetNamespace=&quot;http://example.com/numbers&quot; ...&gt;
 *   ...
 *     &lt;portType name=&quot;AddNumbersPortType&quot;&gt;
 *       &lt;operation name=&quot;AddNumbers&quot;&gt;
 *         &lt;input message=&quot;tns:AddNumbersInput&quot; name=&quot;Parameters&quot;/&gt;
 *         &lt;output message=&quot;tns:AddNumbersOutput&quot; name=&quot;Result&quot;/&gt;
 *         &lt;fault message=&quot;tns:addNumbersFault&quot; name=&quot;AddNumbersException&quot;
 *           wsa:Action=&quot;http://example.com/addnumbers/fault&quot;/&gt;
 *         &lt;fault message=&quot;tns:tooBigNumbersFault&quot; name=&quot;TooBigNumbersException&quot;
 *           wsa:Action=&quot;http://example.com/toobigFaultAction&quot;/&gt;
 *       &lt;/operation&gt;
 *     &lt;portType&gt;
 *   ...
 *   &lt;definitions&gt;}
 * </pre>
 * 
 * @since JAX-WS 2.1
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface FaultAction {
    /**
     * Name of the exception class
     */
    Class className();

    /**
     * Value of <code>Action</code> message addressing property for the exception
     */
    String value() default "";
}
