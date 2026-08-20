SUMMARY = "eltt2"
DESCRIPTION = "Infineon Embedded Linux TPM Toolbox 2 - 直接對 /dev/tpm0 下 TPM 2.0 命令的測試工具"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://License.txt;md5=1e492cfcb05c60002d4bee800bd9c296"

SRC_URI = "git://github.com/Infineon/eltt2;protocol=https;branch=master"
SRCREV  = "3d55476179da9bd61c2df1ba1ef010afe27e7776"
#因為eltt2綁定gcc
EXTRA_OEMAKE = "'CC=${CC}'"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/eltt2 ${D}${bindir}/eltt2
}
