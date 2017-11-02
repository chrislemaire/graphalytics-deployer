# This script packages the graphalytics distribution
# and after that it is moved to the install directory
# for that graphalytics install.

# System information
INSTALL_DIR="%install_dir%"      # The absolute path to the directory where installs are located.

# Package the install, assuming we're already in the
# right directory (the platform driver clone)
echo -e "[GRAPHALYTICS-PACKAGE]:\tBuilding the Maven package of the current directory..."
mvn clean package -DskipTests=true -Dmaven.buildNumber.skip

# Move the package to the install directory.
PACKAGE_FILE=`ls -tp | grep -v / | head -n1`
echo -e "[GRAPHALYTICS-PACKAGE]:\tMoving package $PACKAGE_FILE to install directory..."
mv ${PACKAGE_FILE} ${INSTALL_DIR}

# Unpack the package and remove it afterwards.
echo -e "[GRAPHALYTICS-PACKAGE]:\tUnpacking package $PACKAGE_FILE..."
${UTIL}/unpack.sh ${PACKAGE_FILE}
rm -f ${PACKAGE_FILE}

