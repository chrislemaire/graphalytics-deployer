# After cloning the graphalytics graphmat driver...
# and after GraphMat is already installed...

# Product settings
GRAPHMAT_HOME="%graphmat_dir%"

# 1. Move the config-template directory to config
echo -e "[GRAPHMAT-INSTALL]:\tMoving config-template directory to config"
mv ./config-template ./config

# 2. Configure platform.properties
echo -e "[GRAPHMAT-INSTALL]:\tConfiguring the platform.properties file"
sed -i "s/.*\(platform\.graphmat\.home\s*=\).*/\1 $GRAPHMAT_HOME/" ./config/platform.properties

# 3. Package the graphalytics platform
echo -e "[GRAPHMAT-INSTALL]:\tBuilding maven package..."
mvn clean package -DskipTests=true -Dmaven.buildNumber.skip

PACKAGE=`ls -tp | grep -v / | head -n1`
mv ${PACKAGE} ../

cd ..
rm -rf


