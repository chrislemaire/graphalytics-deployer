# Unpacks a given file of any format.

PACKAGE_FILE=$1

if [[ "$PACKAGE_FILE" == "*.tar.gz" ]]; then

    # Unpack like a tarball
    echo -e "[PACKAGE-UNPACK]:\tUnpacking tarball $PACKAGE_FILE..."
    tar -xzvf ${PACKAGE_FILE}

elif [[ "$PACKAGE_FILE" == "*.zip" ]]; then

    # Unpack like a zip
    echo -e "[PACKAGE-UNPACK]:\tUnzipping zip file $PACKAGE_FILE..."
    unzip ${PACKAGE_FILE}

else

    # Not a recognized package file-extension
    echo -e "[PACKAGE-UNPACK]:\tPackage type not recognized!"
    echo -e "[PACKAGE-UNPACK]:\tQuitting install procedure for $PRODUCT"

fi



