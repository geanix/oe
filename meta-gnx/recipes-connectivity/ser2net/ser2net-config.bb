SUMMARY = "Systemd unit and configuration file for ser2net"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit systemd allarch

SRC_URI = " \
    file://ser2net.service \
    file://ser2net.yaml \
"
S = "${WORKDIR}"

SYSTEMD_SERVICE_${PN} = "ser2net.service"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -m 644 -D -t ${D}${systemd_system_unitdir} ${S}/ser2net.service
    install -m 644 -D -t ${D}${sysconfdir}/ser2net ${S}/ser2net.yaml
}

FILES_${PN} = " \
    ${systemd_system_unitdir} \
    ${sysconfdir}/ser2net \
"
