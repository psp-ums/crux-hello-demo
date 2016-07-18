#!/bin/sh

JAVA_OPTIONS="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"

if [ ! -z "$CRUX_VERSION" ]; then
    CRUX_HOME="$CRUX/core_$CRUX_VERSION"
    JAVA_OPTIONS="$JAVA_OPTIONS -Dcrux.core.home=$CRUX_HOME"
    CLASS_PATH="$CRUX/core_$CRUX_VERSION/boot/*:"
    echo "Starting Crux $CRUX_VERSION located at $CRUX_HOME"
else
    CLASS_PATH="../../../core/boot/*:../../core/boot/*:../core/boot/*"
    echo   "Starting Crux using default versions"
fi

echo   "Starting Crux"
echo   ""
echo   "JAVA_HOME: ${JAVA_HOME}"
echo   ""
echo   "JAVA_OPTIONS: ${JAVA_OPTIONS}"
echo   ""

${JAVA_HOME}/bin/java -Dcrux-hello -showversion -classpath ${CLASS_PATH} ${JAVA_OPTIONS} cellvision.crux.startup.CruxStarter
