SUMMARY = "The default rootfs image"
LICENSE = "MIT"

inherit image extrausers

IMAGE_FSTYPES = "ext4 wic.gz wic.bmap"
IMAGE_FEATURES += "allow-root-login"
IMAGE_LINGUAS = ""

EXTRA_USERS_PARAMS += "usermod -P root root;"

WKS_FILE ?= "${MACHINE}.wks"
WIC_CREATE_EXTRA_ARGS += "--no-fstab-update"

# Packages to install
IMAGE_INSTALL = " \
    ${MACHINE_EXTRA_RDEPENDS} \
    packagegroup-core-boot \
    kernel-image \
    kernel-modules \
    mcp23s17-overlay \
    systemd-config \
    openssh \
    libgpiod-tools \
    python3-gpiorest \
    ser2net \
    ser2net-config \
    iproute2 \
    util-linux \
    usbutils \
"
