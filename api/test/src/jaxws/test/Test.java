/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2015, 2018 Oracle and/or its affiliates. All rights reserved.
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

package jaxws.test;

import javax.xml.bind.JAXBContext;
import javax.xml.ws.spi.Provider;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Tests creation of JAXBContext - base class for different tests
 */
public class Test {

    protected Provider provider() throws Throwable {
        try {
            Provider provider = Provider.provider();
            System.out.println("     TEST: context class = [" + provider.getClass().getName() + "]\n");
            return provider;
        } catch (Throwable t) {
            System.out.println("     TEST: Throwable [" + t.getClass().getName() + "] thrown.\n");
            throw t;
        }
    }

    protected void test(String[] args) {

//        // 3rd parameter means that TCCL should be used
//        if (args.length > 2) {
//            setContextClassLoader();
//        }

        try {
            Provider provider = provider();
            assertTrue(provider != null, "No Provider found.");
            String className = provider.getClass().getName();
            assertTrue(args[0].equals(className), "Incorrect provider: [" + className +
                    "], Expected: [" + args[0] + "]");

        } catch (Throwable throwable) {
            //throwable.printStackTrace();
            String expectedExceptionClass = args[1];
            String throwableClass = throwable.getClass().getName();
            boolean correctException = throwableClass.equals(expectedExceptionClass);
            if (!correctException) {
                throwable.printStackTrace();
            }
            assertTrue(correctException, "Got unexpected exception: [" +
                    throwableClass + "], expected: [" + expectedExceptionClass + "]");
        }
    }

    /**
     * Alloas to test loading classes using TCCL.
     */
    private void setContextClassLoader() {
        try {
            String path = new File(".").getAbsoluteFile().getParentFile().getParent() + "/src/";
//            System.out.println("    Creating URLClassLoader to load classes from: " + path);
            ClassLoader cl = new URLClassLoader(
                    new URL[]{
                            new URL("file://" + path + "/")
                    }, null
            );
            Thread.currentThread().setContextClassLoader(cl);
//            System.out.println("    ...contextClassLoader set.");
        } catch (Throwable t) {
            System.out.println("    FAILED: Problem while creating URLClassLoader: ");
            t.printStackTrace();
        }
    }

    private static void assertTrue(boolean condition, String msg) {
        if (!condition) {
            log(" FAILED -  ERROR: " + msg);
            throw new RuntimeException(msg);
        } else {
            log(" PASSED");
        }
    }

    private static void log(String msg) {
        System.out.println(msg);
    }


    public static void main(String[] args) {
        new Test().test(args);
    }
}
