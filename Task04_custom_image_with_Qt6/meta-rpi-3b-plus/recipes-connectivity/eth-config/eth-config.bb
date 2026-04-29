SUMMARY = "Ethernet DHCP configuration for RPi3B+"
DESCRIPTION = "Installs systemd-networkd config for eth0 with DHCP"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE;md5=7e1c8e5e20641a060f60770f5e59b8f9"

SRC_URI = "file://eth0.network file://LICENSE"

FILES:${PN} = "${sysconfdir}/systemd/network/eth0.network"

do_install() {
    install -d ${D}${sysconfdir}/systemd/network/
    install -m 0644 ${WORKDIR}/eth0.network ${D}${sysconfdir}/systemd/network/eth0.network
}

