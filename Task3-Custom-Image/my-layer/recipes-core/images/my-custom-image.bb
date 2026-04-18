SUMMARY = "My Custom Image with Python and tcpdump"

inherit core-image

IMAGE_INSTALL:append = " python3 tcpdump"

IMAGE_ROOTFS_SIZE = "819200"