SUMMARY = "Mainline kernel package"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

inherit kernel

DEPENDS += "lzop"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git;branch=linux-5.9.y"
SRCREV = "bbf5c979011a099af5dc76498918ed7df445635b"
S = "${WORKDIR}/git"

SRC_URI += "file://0001-pinctrl-mcp23s08-Use-full-chunk-of-memory-for-regmap.patch"

# The kernel class expects a defconfig in ${WORKDIR}, so tell it which one to use
DEFAULT_KERNEL_DEFCONFIG_arm     = "multi_v7_defconfig"
DEFAULT_KERNEL_DEFCONFIG_aarch64 = "defconfig"
DEFAULT_KERNEL_DEFCONFIG_x86     = "i386_defconfig"
DEFAULT_KERNEL_DEFCONFIG_x86_64  = "x86_64_defconfig"
KERNEL_DEFCONFIG ?= "${DEFAULT_KERNEL_DEFCONFIG}"

KERNEL_OPTIONS ?= ""

# enable support for device tree overlays
EXTRA_OEMAKE += "DTC_FLAGS=-@"

# Pick up configured default configurations from the linux source tree
do_configure_prepend() {
    if [ -n "${KERNEL_DEFCONFIG}" ]; then
        cp ${S}/arch/${ARCH}/configs/${KERNEL_DEFCONFIG} ${B}/.config
    fi

    for line in ${KERNEL_OPTIONS}; do
        option=$(echo $line | cut -d= -f1)
        setting=$(echo $line | cut -d= -f2)

        case "$setting" in
            y) setting=e;;
            n) setting=d;;
        esac

        bbdebug 1 "option: $option, setting: $setting"
        ${S}/scripts/config --file ${B}/.config -$setting $option
    done
}

# install devicetree blobs where those are configured
RRECOMMENDS_kernel-image += "kernel-devicetree"
