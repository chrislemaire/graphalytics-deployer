# Installs powergraph after getting the git-repo
# and checking it out on the right commit.

# 1. Remove singleton directories
# 2. Run build distribution script
# 3. Run compile benchmark script

INSTALL_DIR="%install_dir%"
POWERGRAPH_DRIVER="%powergraph_dir%"

# Move into the appropriate install directory
echo -e "[POWERGRAPH-INSTALL]:\tMoving into powergraph driver directory"
cd ${INSTALL_DIR}

# Call build distribution script
echo -e "[POWERGRAPH-INSTALL]:\tStarting build-distribution.sh script..."
${POWERGRAPH_DRIVER}/bin/sh/build-distribution.sh

# Call compile benchmark script
echo -e "[POWERGRAPH-INSTALL]:\tStarting compile-benchmark.sh script..."
${POWERGRAPH_DRIVER}/bin/sh/compile-benchmark.sh


