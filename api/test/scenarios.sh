#
# DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
#
# Copyright (c) 2014-2017 Oracle and/or its affiliates. All rights reserved.
#
# The contents of this file are subject to the terms of either the GNU
# General Public License Version 2 only ("GPL") or the Common Development
# and Distribution License("CDDL") (collectively, the "License").  You
# may not use this file except in compliance with the License.  You can
# obtain a copy of the License at
# https://oss.oracle.com/licenses/CDDL+GPL-1.1
# or LICENSE.txt.  See the License for the specific
# language governing permissions and limitations under the License.
#
# When distributing the software, include this License Header Notice in each
# file and include the License file at LICENSE.txt.
#
# GPL Classpath Exception:
# Oracle designates this particular file as subject to the "Classpath"
# exception as provided by Oracle in the GPL Version 2 section of the License
# file that accompanied this code.
#
# Modifications:
# If applicable, add the following below the License Header, with the fields
# enclosed by brackets [] replaced by your own identifying information:
# "Portions Copyright [year] [name of copyright owner]"
#
# Contributor(s):
# If you wish your version of this file to be governed by only the CDDL or
# only the GPL Version 2, indicate your decision by adding "[Contributor]
# elects to include this software in this distribution under the [CDDL or GPL
# Version 2] license."  If you don't indicate a single choice of license, a
# recipient has the option to distribute your version of this file under
# either the CDDL, the GPL Version 2 or to extend the choice of license to
# its licensees as provided above.  However, if you add GPL Version 2 code
# and therefore, elected the GPL Version 2 license, then the option applies
# only if the new code is made subject to such option by the copyright
# holder.
#

#prepare props svc

# arg1 ... ${java.home}/conf/jaxws.properties content (javax.xml.ws.spi.Provider=fq class name)
# META-INF/services/javax.xml.ws.spi.Provider content (fq class name)
# prepare arg1 arg2

# arg1 ... expected provider class / -
# arg2 ... expected exception / -
# arg3 ... system property /
# test arg1 arg2 arg3

export DEFAULT=com.sun.xml.internal.ws.spi.ProviderImpl

scenario 1
prepare javax.xml.ws.spi.Provider=jaxws.factory.Valid -
test jaxws.factory.Valid -

# JAXB fails here
scenario 2
prepare something=AnotherThing -
test $DEFAULT -

# JAXB fails here
scenario 3
prepare javax.xml.ws.spi.Provider=non.existing.FactoryClass -
test $DEFAULT -

scenario 4
prepare javax.xml.ws.spi.Provider=jaxws.factory.Invalid -
test - javax.xml.ws.WebServiceException

scenario 5
prepare - -
test jaxws.factory.Valid - -Djavax.xml.ws.spi.Provider=jaxws.factory.Valid

scenario 6
prepare - -
test - javax.xml.ws.WebServiceException -Djavax.xml.ws.spi.Provider=jaxws.factory.NonExisting

scenario 7
prepare - -
test - javax.xml.ws.WebServiceException -Djavax.xml.ws.spi.Provider=jaxws.factory.Invalid

scenario 8
prepare - 'jaxws.factory.Valid\n'
test jaxws.factory.Valid -

scenario 9
prepare - jaxws.factory.Valid
test jaxws.factory.Valid -

# JAXB throws JAXBException here ...  java.util.ServiceConfigurationError
scenario 10
prepare - jaxws.factory.NonExisting
test - javax.xml.ws.WebServiceException

# JAXB throws JAXBException here ...  java.util.ServiceConfigurationError
scenario 11
prepare - jaxws.factory.Invalid
test - javax.xml.ws.WebServiceException

scenario 12
prepare - -
test $DEFAULT -

scenario 13
prepare javax.xml.ws.spi.Provider=jaxws.factory.Valid jaxws.factory.Valid2
test jaxws.factory.Valid2 - -Djavax.xml.ws.spi.Provider=jaxws.factory.Valid3

scenario 14
prepare javax.xml.ws.spi.Provider=jaxws.factory.Valid -
test jaxws.factory.Valid - -Djavax.xml.ws.spi.Provider=jaxws.factory.Valid

scenario 15
prepare - -
test jaxws.factory.Valid - -Djavax.xml.ws.spi.Provider=jaxws.factory.Valid
