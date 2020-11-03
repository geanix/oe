DESCRIPTION = "Configuration snippets for systemd"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit systemd allarch

SRC_URI = " \
    file://70-wired.network \
    file://70-br0.network \
    file://70-br0.netdev \
    file://70-usb.rules \
"

do_configure[noexex] = "1"
do_compile[noexec] = "1"

do_install_append() {
    # networkd config
    install -m0644 -D -t \
        ${D}${systemd_unitdir}/network \
        ${WORKDIR}/70-wired.network \
        ${WORKDIR}/70-br0.network \
        ${WORKDIR}/70-br0.netdev

    # udev rules
    install -m0644 -D -t \
        ${D}${base_libdir}/udev/rules.d \
        ${WORKDIR}/70-usb.rules
}

FILES_${PN} = "${systemd_unitdir} ${base_libdir}/udev/rules.d"
