DESCRIPTION = "REST API to control GPIO chips"
HOMEPAGE = "https://github.com/prevas-dk/labgrid-powerrelay"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=41850a1cff5f0dc654c3b937c03f626e"

inherit setuptools3 systemd

SYSTEMD_SERVICE_${PN} = "gpiorest.service"

SRC_URI = "git://git.geanix.com/geanix/gpiorest.git;protocol=https"
SRCREV = "08096f300bda76c87afeb0be75e852746233188b"
S = "${WORKDIR}/git"

FILES_${PN} += "${datadir} ${base_libdir}/gpiorest"

RDEPENDS_${PN} = " \
    python3-flask \
    libgpiod-python \
"
