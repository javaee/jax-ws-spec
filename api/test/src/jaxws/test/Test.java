/*
 * Copyright (c) 2015, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
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
