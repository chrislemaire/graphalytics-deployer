# Only a single version of a git product is allowed
# as most of the time only a single version is needed.

# To install, we first clone the repository and then we
# checkout the requested commit/version of the repo.

# System information
INSTALL_DIR="%install_dir%"      # The absolute path to the directory where installs are located.

# Product information
URL="%download_url%"
PRODUCT="%product%"
VERSION="%version%"

# Print the install script indicator
echo -e "[GIT_INSTALL]:\tInstalling git product $PRODUCT at commit $VERSION"

# Move into install directory
echo -e "[GIT_INSTALL]:\tMoving into general product install directory $INSTALL_DIR"
cd ${INSTALL_DIR}

# Clone the git repository
echo -e "[GIT_INSTALL]:\tCloning the Git repository from $URL"
git clone ${URL}

# Checkout the appropriate commit
echo -e "[GIT_INSTALL]:\tCheckout commit with has $VERSION"
git checkout ${VERSION}


