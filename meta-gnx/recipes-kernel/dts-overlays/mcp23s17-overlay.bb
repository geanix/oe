DESCRIPTION = "Device tree overlay for PiFace Digital 2"
LICENSE = "GPL-2.0+"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

inherit dts-overlay

SRC_URI = "file://mcp23s17-overlay.dts"
S = "${WORKDIR}"
