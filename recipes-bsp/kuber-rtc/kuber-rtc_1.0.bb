SUMMARY = "kuber rtc"
DESCRIPTION = "rtc on kuber"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = " \
       file://rtc-i2c.conf \
       file://50-i2c_rtc.rules \
"

S = "${UNPACKDIR}"

do_install() {
    install -d ${D}${sysconfdir}/modules-load.d
    install -m 0644 ${S}/rtc-i2c.conf ${D}${sysconfdir}/modules-load.d/rtc-i2c.conf

    install -d ${D}${sysconfdir}/udev/rules.d
    install -m 0644 ${S}/50-i2c_rtc.rules ${D}${sysconfdir}/udev/rules.d/50-i2c_rtc.rules
}

FILES:${PN} = " \
    ${sysconfdir}/modules-load.d/rtc-i2c.conf \
    ${sysconfdir}/udev/rules.d/50-i2c_rtc.rules \
"
