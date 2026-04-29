SUMMARY = "WiFi configuration for RPi3B+"
DESCRIPTION = "Installs wpa_supplicant config, systemd-networkd \
               network file, and wifi systemd service"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE;md5=7e1c8e5e20641a060f60770f5e59b8f9"

SRC_URI = "file://wpa_supplicant-wlan0.conf file://wlan0.network file://wifi-setup.service file://LICENSE"

# tell ycoto this recipe use systemd
inherit systemd

# which service to enable at boot?
SYSTEMD_SERVICE:${PN} = "wifi-setup.service"

# start at boot
SYSTEMD_AUTO_ENABLE = "enable"

# tell yocto which this  recipe puts on target
FILES:${PN} = " ${sysconfdir}/wpa_supplicant/wpa_supplicant-wlan0.conf   ${sysconfdir}/systemd/network/wlan0.network ${systemd_system_unitdir}/wifi-setup.service"

do_install () {
	# create /etc/wpa_supplicant/
	install -d ${D}${sysconfdir}/wpa_supplicant/
	# transfer the file under /etc/wpa_supplicant/
	install -m 0644 ${WORKDIR}/wpa_supplicant-wlan0.conf ${D}${sysconfdir}/wpa_supplicant/wpa_supplicant-wlan0.conf

	# create the network folder and transfer wlan0.network file
	install -d ${D}${sysconfdir}/systemd/network/
	install -m 0644 ${WORKDIR}/wlan0.network ${D}${sysconfdir}/systemd/network/wlan0.network

	# create the wifi-setup folder and transfer wifi-setup file
	install -d ${D}${systemd_system_unitdir}
	install -m 0644 ${WORKDIR}/wifi-setup.service ${D}${systemd_system_unitdir}/wifi-setup.service
}

