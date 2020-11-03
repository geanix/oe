SUMMARY = "Boot script to load kernel and dtb from a/b slots"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit allarch deploy

DEPENDS = "u-boot-tools-native"

SRC_URI = "file://boot.txt"
S = "${WORKDIR}"

do_configure[noexec] = "1"

do_compile() {
    mkimage -T script -C none -n 'bootslot selection' -d ${S}/boot.txt ${B}/boot.scr
}

do_install() {
    install -m 644 -D -t ${D}/boot ${B}/boot.scr
}

do_deploy() {
    install -m 644 -D -t ${DEPLOYDIR} ${D}/boot/boot.scr
}

addtask deploy before do_build after do_install

FILES_${PN} = "/boot/boot.scr"
