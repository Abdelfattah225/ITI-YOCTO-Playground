#!/bin/bash
# flash-sd.sh - Flash Yocto initramfs image to SD card for RPi3-64
# Usage: sudo ./flash-sd.sh /dev/sdX

set -e

if [ -z "\$1" ]; then
    echo "Usage: sudo \$0 /dev/sdX"
    echo "Example: sudo \$0 /dev/sda"
    exit 1
fi

if [ "$EUID" -ne 0 ]; then
    echo "Please run with sudo"
    exit 1
fi

DEVICE=\$1
DEPLOY_DIR="$(dirname \$0)/../../../Embedded_Linux_env/buildSystems/yocto/shared/tmp/deploy/images/raspberrypi3-64"

echo "WARNING: This will ERASE all data on ${DEVICE}"
echo "Press Enter to continue or Ctrl+C to cancel..."
read

# Unmount any existing partitions
umount ${DEVICE}* 2>/dev/null || true

# Partition the SD card
echo "Partitioning ${DEVICE}..."
fdisk ${DEVICE} << EOF
d
2
d
n
p
1


t
c
w
EOF

# Format as FAT32
echo "Formatting as FAT32..."
mkfs.vfat -F 32 -n BOOT ${DEVICE}1

# Mount and copy files
echo "Copying boot files..."
mount ${DEVICE}1 /mnt

cp ${DEPLOY_DIR}/Image-initramfs-raspberrypi3-64.bin /mnt/kernel8.img
cp ${DEPLOY_DIR}/bcm2710-rpi-3-b.dtb /mnt/
cp ${DEPLOY_DIR}/bcm2710-rpi-3-b-plus.dtb /mnt/
cp ${DEPLOY_DIR}/bootfiles/bootcode.bin /mnt/
cp ${DEPLOY_DIR}/bootfiles/start.elf /mnt/
cp ${DEPLOY_DIR}/bootfiles/fixup.dat /mnt/
cp ${DEPLOY_DIR}/bootfiles/config.txt /mnt/
cp ${DEPLOY_DIR}/bootfiles/cmdline.txt /mnt/

mkdir -p /mnt/overlays
cp ${DEPLOY_DIR}/bootfiles/overlays/* /mnt/overlays/ 2>/dev/null || true

# Enable UART
echo "enable_uart=1" >> /mnt/config.txt
echo "dwc_otg.lpm_enable=0 console=serial0,115200 net.ifnames=0" > /mnt/cmdline.txt

sync
umount /mnt

echo ""
echo "✅ SD card ready! Files on boot partition:"
mount ${DEVICE}1 /mnt
ls -lh /mnt/
umount /mnt
echo ""
echo "Insert SD card into RPi3 and power on!"
