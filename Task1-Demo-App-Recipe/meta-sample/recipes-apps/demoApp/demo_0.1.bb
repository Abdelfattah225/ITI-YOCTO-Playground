
SUMMARY = "Simple Hello World Application"
DESCRIPTION = "demo app Created by Abdelfattah"
LICENSE = "Closed"

# fetch
SRC_URI = "git://github.com/Abdelfattah225/Demo-App.git;protocol=https;branch=main"

SRCREV = "bb0d01c3d540a93e76fa35177892956982bea740"

S = "${WORKDIR}/git"

do_configure() {
        :
}

do_compile() {
        ${CC} ${CFLAGS} ${LDFLAGS} -o DemoAppbin ${S}/main.c
}



