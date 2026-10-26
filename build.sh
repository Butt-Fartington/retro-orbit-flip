#!/bin/sh
set -eu
cd "$(dirname "$0")"
SDK="${ANDROID_HOME:-/Users/ian/Library/Android/sdk}"
TOOLS="$SDK/build-tools/36.0.0"
ANDROID="$SDK/platforms/android-35/android.jar"
mkdir -p build/classes build/dex
"$TOOLS/aapt2" compile --dir res -o build/resources.zip
"$TOOLS/aapt2" link -o build/base.apk --manifest AndroidManifest.xml -I "$ANDROID" -A assets build/resources.zip
find src -name '*.java' > build/sources.txt
javac -source 8 -target 8 -classpath "$ANDROID" -d build/classes @build/sources.txt
jar cf build/classes.jar -C build/classes .
"$TOOLS/d8" --min-api 26 --lib "$ANDROID" --output build/dex build/classes.jar
cp build/base.apk build/unsigned.apk
(cd build/dex && zip -q ../unsigned.apk classes.dex)
"$TOOLS/zipalign" -f 4 build/unsigned.apk build/aligned.apk
KEYSTORE="${ORBIT_KEYSTORE:-signing/retro-orbit.keystore}"
if [ ! -f "$KEYSTORE" ]; then
 echo "Provide the existing signing key via ORBIT_KEYSTORE. Do not generate a different key for updates." >&2
 exit 1
fi
"$TOOLS/apksigner" sign --ks "$KEYSTORE" --ks-pass pass:android --out build/retro-orbit.apk build/aligned.apk
"$TOOLS/apksigner" verify build/retro-orbit.apk
