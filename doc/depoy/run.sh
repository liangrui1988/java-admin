#!/bin/bash
PATH_LIB=./lib
CLASSPATH=../etc

for jar in `ls $PATH_LIB/*.jar`
do
      CLASSPATH="$CLASSPATH:""$jar"
done

#        ARGS="$ARGS"" -Djava.rmi.server.hostname="
#        ARGS="$ARGS"" -Dcom.sun.management.jmxremote.port=8990"
#        ARGS="$ARGS"" -Dcom.sun.management.jmxremote.authenticate=false" 
#        ARGS="$ARGS"" -Dcom.sun.management.jmxremote.ssl=false" 
#        ARGS="$ARGS"" -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8014" 

exec -a gdata java -server -Xms128m -Xmx526m -XX:PermSize=128m -XX:MaxPermSize=256m -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintHeapAtGC -Xloggc:/home/web_admin/pro/server/gdata/work/gc.log $ARGS -classpath "$CLASSPATH" com.huiwan.gdata.AdminMain 9899 >> work/stdout.log

