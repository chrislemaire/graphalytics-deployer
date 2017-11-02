# This script prepares the install directories.

# Product install information.
INSTALL_DIR="%install_dir%"

# Make the frameworks directory if not created yet.
echo -e "[INSTALL-PREPARE]:\tCreate the frameworks directories: $INSTALL_DIR"
mkdir -p ${INSTALL_DIR}



