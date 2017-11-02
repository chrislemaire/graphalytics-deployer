# System information
INSTALL_DIR="%install_dir%"      # The absolute path to the directory where installs are located.

# Package information
CLEAN_INSTALL="%clean_install%"
URL="%download_url%"
PRODUCT="%product%"
VERSION="%version%"

# Echo the install purpose.
echo -e "[APACHE-INSTALL]:\tInstalling $PRODUCT version $VERSION"

# Move into the install directory.
echo -e "[APACHE-INSTALL]:\tMoving into $INSTALL_DIR for install"
cd ${INSTALL_DIR}

# Store the product directory.
PRODUCT_DIR=./${PRODUCT}/${VERSION}

# If the clean option is supplied, first remove the install directory.
if [[ ${CLEAN_INSTALL} = "true" && -d "$PRODUCT_DIR" ]]
then

    # Remove the old install directory
    echo -e "[APACHE-INSTALL]:\tRemoving old install directory..."
    rm -rf ${PRODUCT_DIR}

fi

# Check if the file directories already exist and if not, go on.
if [[ ! -d "" ]]
then

    # Create the directories for the install
    echo -e "[APACHE-INSTALL]:\tCreating directory for $PRODUCT version $VERSION"
    mkdir -p ${PRODUCT_DIR}

    # Move into install directory
    echo -e "[APACHE-INSTALL]:\tMoving into relative install directory $INSTALL_DIR"
    cd ${INSTALL_DIR}

    # Download the product from apache fileserver
    echo -e "[APACHE-INSTALL]:\tDownloading package from $URL..."
    wget ${URL}

    # Get the downloaded package file
    PACKAGE_FILE=`ls -tp | grep -v / | head -n1`
    echo -e "[APACHE-INSTALL]:\tFound package to be $PACKAGE_FILE"

    ${UTIL}/unpack.sh ${PACKAGE_FILE}

    # Remove the singleton directories in this directory
    echo -e "[APACHE-INSTALL]:\tMoving out the install directory from singleton directories."
    while [[ `ls -1 | wc -l` -le 1 ]]; do
        DIR=`ls`
        mv ${DIR}/* ./
        rm -rf ${DIR}
    done

else

    # Do nothing, the install directory already existed.
    echo -e "[APACHE-INSTALL]:\tThe install directory already existed. Doing nothing."
    echo -e "[APACHE-INSTALL]:\tYou can clean install using the CLEAN_INSTALL option."

fi


