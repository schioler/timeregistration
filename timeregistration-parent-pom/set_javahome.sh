#! /bin/bash -x 
JAVA_VERSION=jdk1.7.0_79.jdk
export JAVA_HOME="/Library/Java/JavaVirtualMachines/${JAVA_VERSION}/Contents/Home/"
#PATH=$JAVA_HOME/bin:$PATH;


export PATH=${JAVA_HOME}/bin:/usr/local/mysql/bin/:${PATH}:/Applications/apache-maven-3.3.3/bin


