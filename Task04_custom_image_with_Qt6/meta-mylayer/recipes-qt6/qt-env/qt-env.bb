SUMMARY = "Qt6 EGLFS environment configuration"
DESCRIPTION = "Sets QT_QPA_PLATFORM=eglfs for all user sessions"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE;md5=7e1c8e5e20641a060f60770f5e59b8f9"

SRC_URI = "file://qt-env.sh file://LICENSE"

FILES:${PN} = " ${sysconfdir}/profile.d/qt-env.sh"


do_install () {
	 # transfer the script
    install -d ${D}${sysconfdir}/profile.d/
    install -m 0755 ${WORKDIR}/qt-env.sh ${D}${sysconfdir}/profile.d/qt-env.sh
}