# use sed to modify the existing file to remove root ssh login
do_install:append() {
    sed -i 's/^#*PermitRootLogin.*/PermitRootLogin yes/' \
        ${D}${sysconfdir}/ssh/sshd_config
}