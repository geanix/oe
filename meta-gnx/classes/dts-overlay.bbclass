DTS_OVERLAY ?= "${PN}.dts"
DTB_OVERLAY ?= "${PN}.dtb"

inherit deploy

do_configure[noexec] = "1"

do_compile[depends] += " \
    dtc-native:do_populate_sysroot \
    virtual/kernel:do_deploy \
"
do_compile() {
    dtc -@ -O dtb -o ${B}/${DTB_OVERLAY} ${S}/${DTS_OVERLAY}

    for dtb in ${KERNEL_DEVICETREE}; do
        dtb_base=$(basename $dtb | sed 's,\.dts$,.dtb,g')
        mkdir -p ${B}/$(dirname $dtb)
        fdtoverlay -v -i ${DEPLOY_DIR_IMAGE}/$dtb_base -o ${B}/$dtb ${B}/${DTB_OVERLAY}
    done
}

do_install() {
    install -d ${D}/boot

    for dtb in ${KERNEL_DEVICETREE}; do
        d=${D}/boot/$(dirname $dtb)
        install -d $d
        install -m0644 ${B}/$dtb $d
    done
}

do_deploy() {
    install -d ${D}/boot/$dtb ${DEPLOYDIR}/overlay

    for dtb in ${KERNEL_DEVICETREE}; do
        install -m0644 ${D}/boot/$dtb ${DEPLOYDIR}/overlay
    done
}

addtask deploy before do_build after do_install

FILES_${PN} += "/boot"
