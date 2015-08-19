#!/bin/sh

rm -rf endorsed
mkdir endorsed
cd ..
mvn clean package
cp target/jaxws-api*.jar test/endorsed

echo "endorsed dir: "`pwd`/test/endorsed
ls -al test/endorsed