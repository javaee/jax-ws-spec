#!/bin/sh
#
# DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
#
# Copyright (c) 2015, 2018 Oracle and/or its affiliates. All rights reserved.
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


#
# The latest SPEC's version javadoc:
# http://docs.oracle.com/javase/8/docs/api/javax/xml/ws/spi/Provider.html
#
# in short:
#
# 1) ServiceLoader: /META-INF/services/javax.xml.ws.spi.Provider
# 2) $java.home/lib/jaxws.properties - keyd by javax.xml.ws.spi.Provider
# 3) SystemProperty: javax.xml.ws.spi.Provider
#  * OSGi (non-SPEC)
# 4) default provider


#    testcases:
#
#        1) property file: ok
#        2) property file: missing property
#        3) property file: non-existing class property
#        4) property file: invalid class property
#
#        # system property: javax.xml.bind.context.factory
#        5) system property: ok
#        6) system property: ClassNotFound
#        7) system property: incorrect class

#        # ServiceLoader:
#        8) ok
#        9) ok - no new line
#        10) ClassNotFound
#        11) incorrect class
#
#        # default
#        12) no setup
#
#        # priorities:
#        13) prop.file sys.property ServiceLoader > service loader
#        14) prop.file sys.property  > prop.file
#        15) - sys. property - > sys.property
#

export JDK_CONF_DIR=jre/lib
#export JDK_CONF_DIR=conf

export ENDORSED_DIR="`pwd`/endorsed"
export ENDORSED="-Djava.endorsed.dirs=$ENDORSED_DIR"
#export ENDORSED=

echo "JAVA_HOME: " $JAVA_HOME
echo "endorsed dirs: " $ENDORSED
ls -al $ENDORSED_DIR
javac -version

export D=
if [ "$1" = "debug" ]; then
    export D=$DEBUG
fi;

scenario() {
    echo ""
    echo "================================================"
    echo " Scenario " $1
    echo "================================================"
}

compile() {
#    javac -cp . -Djava.endorsed.dirs=../endorsed -XDignore.symbol.file  $1
    javac -cp . -XDignore.symbol.file  $1
}

#
# Each test call tests 5 different cases:
#  1) JAXBContext.newInstance( String path )
#  2) JAXBContext.newInstance( Class ... classes )
#  3) JAXBContext.newInstance( Class[] classes, Map<String,Object> properties )
#  4) JAXBContext.newInstance( String path ) + setting TCCL
#  5) JAXBContext.newInstance( Class[] classes, Map<String,Object> properties ) + setting TCCL
#

## test [expected-impl] [expected-exception-type] [JVM_OPTS]
## jaxb.test.JAXBTestContextPath [expected-impl] [expected-exception-type] [useTCCL]
test() {
    JVM_OPTS=$3

#    echo - Test ---
    echo java $JVM_OPTS $D $ENDORSED jaxws.test.Test $1 $2
    java $JVM_OPTS $D $ENDORSED jaxws.test.Test $1 $2

#    prepareCtxClassloader
#
#    echo - JAXBTestContextPath+ClassLoader ---
#    java $JVM_OPTS $D $ENDORSED -cp ../ctx-classloader-test jaxb.test.JAXBTestContextPath $1 $2 WithClassLoader
#
#    # parametrized classloader not applicable for method with classes:
#    echo - JAXBTestClasses2+ClassLoader -
#    java $JVM_OPTS $D $ENDORSED -cp ../ctx-classloader-test jaxb.test.JAXBTestClasses2 $1 $2 WithClassLoader
}

clean() {
    rm -rf META-INF
}

#
# Sets up:
#  1) ${java.home}/conf/jaxws.properties file
#  2) META-INF/services/javax.xml.ws.spi.Provider file
prepare() {
    PROPS=$1
    SVC=$2

    echo ""
    echo "- prepare/clean -"
    clean

    if [ "$SVC" != "-" ]; then
        mkdir -p META-INF/services
        echo "$SVC" > META-INF/services/javax.xml.ws.spi.Provider
    else
        rm -rf META-INF
    fi

    echo META-INF: $SVC
    if [ -f META-INF/services/javax.xml.ws.spi.Provider ]; then
      echo "   "`ls -al META-INF/services/javax.xml.ws.spi.Provider`
      echo "   "`cat META-INF/services/javax.xml.ws.spi.Provider`
      echo ""
    fi

    if [ "$PROPS" != "-" ]; then
        echo $PROPS > $JAVA_HOME/$JDK_CONF_DIR/jaxws.properties
        else rm -rf $JAVA_HOME/$JDK_CONF_DIR/jaxws.properties
    fi

    echo properties: $PROPS
    if [ -f $JAVA_HOME/$JDK_CONF_DIR/jaxws.properties ]; then
      echo "   "`ls -al $JAVA_HOME/$JDK_CONF_DIR/jaxws.properties`
      echo "   "`cat $JAVA_HOME/$JDK_CONF_DIR/jaxws.properties`
      echo ""
    fi

    #listDirectory
}

function listDirectory() {
    echo == prepared done ================================
    echo `pwd`:
    find ..
    echo =================================================
}

function compileAll() {
    echo "- compilation -"
    find . -name '*.class' -delete

    # current version of API
    compile 'jaxws/factory/*.java'
    compile 'jaxws/test/*.java'
}

#TCCL_DIR=../ctx-classloader-test

function cleanAll() {
    scenario cleanup
    prepare - -
    rm -rf ../classes
    find . -name '*.class' -delete
#    rm -rf $TCCL_DIR
}

#function prepareCtxClassloader() {
#
#    # preparation for testing TCCL method
#    # copying compiled user classes + property files
#    rm -rf $TCCL_DIR
#    mkdir -p $TCCL_DIR/jaxb/test/usr
#
#    cp jaxb/test/*.class $TCCL_DIR/jaxb/test > /dev/null 2>&1
#
#    cp jaxb/test/usr/*.class $TCCL_DIR/jaxb/test/usr > /dev/null 2>&1
#    cp jaxb/test/usr/*.properties ../ctx-classloader-test/jaxb/test/usr > /dev/null 2>&1
#
#    # debug: list firectory content
#    #find ../
#}


export DEFAULT=com.sun.xml.ws.spi.ProviderImpl

cd src

### old version of API
#FACTORY_ID=javax.xml.ws.spi.Provider
#SVC_FACTORY_ID=javax.xml.ws.spi.Provider
#FACTORY_IMPL_PREFIX=jaxws.factory.

compileAll
source ../scenarios.sh
cleanAll

## new version of API
#FACTORY_IMPL_PREFIX=jaxb.factory.jaxbctxfactory.New
#FACTORY_ID=javax.xml.bind.JAXBContextFactory
#SVC_FACTORY_ID=javax.xml.bind.JAXBContextFactory
#
#compileAll
#source ../scenarios.sh
#cleanAll

rm -rf endorsed
