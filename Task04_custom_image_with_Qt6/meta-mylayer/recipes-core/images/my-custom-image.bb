SUMMARY = "Custom RPi3B+ Image with WiFi, SSH, and Development Tools"

DESCRIPTION = "A custom Yocto image for Raspberry Pi 3B+ with \
               WiFi connectivity, OpenSSH server, and basic \
               development utilities. Built with systemd as \
               init manager using ITI_distro."

inherit core-image

IMAGE_FEATURES:append = " ssh-server-openssh"

IMAGE_INSTALL:append = " python3 tcpdump bash wpa-supplicant iw linux-firmware-rpidistro-bcm43455"

IMAGE_INSTALL:append = " wifi-config"

IMAGE_ROOTFS_SIZE = "819200"


# add abdelfattah user
inherit extrausers
EXTRA_USERS_PARAMS = "\
    useradd -m -s /bin/bash abdelfattah;\
    usermod -p '\$6\$C5ElC9fUBrZ78I/w\$ZHZsYWKW9AbaW6hTFTBMCOarvr/Qo/74vE.ECBGGGHsTYEVWm1Au1/c7GzSJeGFaFbR4Lb.JwYfj08fdRJNnp0' abdelfattah;"
EXTRA_USERS_PARAMS += "usermod -p '\$6\$b8zAv1XO6hGrCgTS\$cahXImbfJazA9.IgFRlftbMKF3KGRSbQxJgrFvzs/eZXYdttn4V0CBVl/be660h76Lcri3IKF3YvqbopbXaDK0' root;"
IMAGE_INSTALL += "sudo"
# add qt & graphcis
IMAGE_INSTALL:append = " qtbase qtdeclarative qtshadertools"
IMAGE_INSTALL:append = " qtsvg qtimageformats"
IMAGE_INSTALL:append = " qt-env" 
IMAGE_INSTALL:append = " iproute2"

# add eth 
IMAGE_INSTALL:append = " eth-config avahi-daemon"
