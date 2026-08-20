SUMMARY = "eltt2"
DESCRIPTION = "Infineon Embedded Linux TPM Toolbox 2 - 直接對 /dev/tpm0 下 TPM 2.0 命令的測試工具"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://License.txt;md5=1e492cfcb05c60002d4bee800bd9c296"

SRC_URI = "git://github.com/Infineon/eltt2;protocol=https;branch=master"
SRCREV  = "3d55476179da9bd61c2df1ba1ef010afe27e7776"
# 上游 Makefile 把 CC 和 CFLAGS 都寫死，而且連結那行根本沒用 $(LDFLAGS)：
#     CC=gcc / CFLAGS=-Wall -Wextra -std=c99 -g / $(CC) $(CFLAGS) eltt2.c -o eltt2
# make 的優先序是「命令列 > Makefile > 環境變數」，所以只能從命令列覆寫。
#   CC       不覆寫會編出 x86 執行檔，建置階段不報錯，板子上才 Exec format error
#   CFLAGS   Yocto 的含 DEBUG_PREFIX_MAP，不帶會讓 TMPDIR 絕對路徑進除錯資訊（破壞可重現建置）
#   LDFLAGS  含 --hash-style=gnu，不帶會缺 GNU_HASH
# 後兩者由 do_package_qa 的 [buildpaths] / [ldflags] 抓出來。
# 編譯與連結是同一行指令，所以 LDFLAGS 併進 CFLAGS 一起傳；Yocto 的擺後面才會贏。
EXTRA_OEMAKE = "'CC=${CC}' 'CFLAGS=-Wall -Wextra -std=c99 ${CFLAGS} ${LDFLAGS}'"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/eltt2 ${D}${bindir}/eltt2
}
