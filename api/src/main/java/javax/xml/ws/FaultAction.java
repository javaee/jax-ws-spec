/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2005-2017 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://oss.oracle.com/licenses/CDDL+GPL-1.1
 * or LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package javax.xml.ws;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code FaultAction} annotation is used inside an {@link Action}
 * annotation to allow an explicit association of a WS-Addressing
 * {@code Action} message addressing property with the {@code fault}
 * messages of the WSDL operation mapped from the exception class.
 * <p>
 * The {@code wsam:Action} attribute value in the {@code fault}
 * message in the generated WSDL operation mapped for {@code className}
 * class is equal to the corresponding value in the {@code FaultAction}.
 * For the exact computation of {@code wsam:Action} values for the
 * fault messages, refer to the algorithm in the JAX-WS specification.
 *
 * <p>
 * <b>Example 1</b>: Specify explicit values for {@code Action} message addressing
 * property for the {@code input}, {@code output} and {@code fault} message
 * if the Java method throws only one service specific exception.
 *
 * <pre>
 * {@literal @}WebService(targetNamespace="http://example.com/numbers")
 *  public class AddNumbersImpl {
 *    {@literal @}Action(
 *         fault = {
 *             <b>{@literal @}FaultAction(className=AddNumbersException.class, value="http://example.com/faultAction")</b>
 *         })
 *     public int addNumbers(int number1, int number2)
 *         throws AddNumbersException {
 *         return number1 + number2;
 *     }
 * }
 * </pre>
 *
 * The generated WSDL looks like:
 *
 * <pre> {@code
 *   <definitions targetNamespace="http://example.com/numbers" ...>
 *     ...
 *     <portType name="AddNumbersPortType">
 *       <operation name="AddNumbers">
 *         ...
 *         <fault message="tns:AddNumbersException" name="AddNumbersException"}
 *           <b>wsam:Action="http://example.com/faultAction"</b>{@code />
 *       </operation>
 *     </portType>
 *     ...
 *   </definitions> }
 * </pre>
 *
 * <p>
 * Example 2: Here is an example that shows if the explicit value for {@code Action}
 * message addressing property for the service specific exception is not present.
 *
 * <pre>
 * {@literal @}WebService(targetNamespace="http://example.com/numbers")
 *  public class AddNumbersImpl {
 *     public int addNumbers(int number1, int number2)
 *         throws AddNumbersException {
 *         return number1 + number2;
 *     }
 *  }
 * </pre>
 *
 * The generated WSDL looks like:
 *
 * <pre>{@code
 *   <definitions targetNamespace="http://example.com/numbers" ...>
 *     ...
 *     <portType name="AddNumbersPortType">
 *       <operation name="AddNumbers">
 *         ...
 *         <fault message="tns:addNumbersFault" name="InvalidNumbers"}
 *           <b>wsam:Action="http://example.com/numbers/AddNumbersPortType/AddNumbers/Fault/AddNumbersException"</b>{@code />
 *       </operation>
 *     </portType>
 *     ...
 *   </definitions>
 * }</pre>
 *
 * <p>
 * Example 3: Here is an example that shows how to specify explicit values for {@code Action}
 * message addressing property if the Java method throws more than one service specific exception.
 *
 * <pre>
 * {@literal @}WebService(targetNamespace="http://example.com/numbers")
 *  public class AddNumbersImpl {
 *    {@literal @}Action(
 *         fault = {
 *             <b>{@literal @}FaultAction(className=AddNumbersException.class, value="http://example.com/addFaultAction"),
 *             {@literal @}FaultAction(className=TooBigNumbersException.class, value="http://example.com/toobigFaultAction")</b>
 *         })
 *     public int addNumbers(int number1, int number2)
 *         throws AddNumbersException, TooBigNumbersException {
 *         return number1 + number2;
 *     }
 *  }
 * </pre>
 *
 * The generated WSDL looks like:
 *
 * <pre> {@code
 *   <definitions targetNamespace="http://example.com/numbers" ...>
 *     ...
 *     <portType name="AddNumbersPortType">
 *       <operation name="AddNumbers">
 *         ...
 *         <fault message="tns:addNumbersFault" name="AddNumbersException"}
 *           <b>wsam:Action="http://example.com/addFaultAction"</b>{@code />
 *         <fault message="tns:tooBigNumbersFault" name="TooBigNumbersException"}
 *           <b>wsam:Action="http://example.com/toobigFaultAction"</b>{@code />
 *       </operation>
 *     </portType>
 *     ...
 *   </definitions>
 * }</pre>
 *
 * @since 1.6, JAX-WS 2.1
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface FaultAction {
    /**
     * Name of the exception class.
     *
     * @return the name of the exception class
     */
    Class<? extends Exception> className();

    /**
     * Value of WS-Addressing {@code Action} message addressing property for the exception.
     *
     * @return WS-Addressing {@code Action} message addressing property for the exception
     */
    String value() default "";
}
