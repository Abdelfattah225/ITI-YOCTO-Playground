
# Custom Yocto Image (Python3 + tcpdump + systemd)

## Objective

Build a custom Yocto image for Raspberry Pi 3 (64-bit) with Python3, tcpdump, and systemd.

## Layer Structure
```
my-layer/
├── conf/
│   └── layer.conf
└── recipes-core/
    └── images/
        └── my-custom-image.bb
```

## Files

### layer.conf
```bash
BBPATH .= ":${LAYERDIR}"
BBFILES += "${LAYERDIR}/recipes-*/*/*.bb"
BBFILE_COLLECTIONS += "my-layer"
BBFILE_PATTERN_my-layer = "^${LAYERDIR}/"
LAYER_CONF_VERSION = "8"
LAYERSERIES_COMPAT_my-layer = "scarthgap"
```

### my-custom-image.bb
```bash
SUMMARY = "My Custom Image with Python and tcpdump"
inherit core-image
IMAGE_INSTALL:append = " python3 tcpdump"
IMAGE_ROOTFS_SIZE = "819200"
```

### local.conf (additions)
```bash
DISTRO_FEATURES:append = " systemd usrmerge"
VIRTUAL-RUNTIME_init_manager = "systemd"
VIRTUAL-RUNTIME_initscripts = "systemd-compat-units"
LICENSE_FLAGS_ACCEPTED = "synaptics-killswitch"
ENABLE_UART = "1"
```

## Required Layers
```
meta-openembedded/meta-oe          (dependency)
meta-openembedded/meta-python      (dependency)
meta-openembedded/meta-networking  (provides tcpdump)
my-layer                           (our custom layer)
```

## Build & Verify
```bash
bitbake my-custom-image
bitbake -e my-custom-image | grep ^IMAGE_INSTALL=
grep python3 tmp/deploy/images/raspberrypi3-64/my-custom-image-raspberrypi3-64.rootfs.manifest
grep tcpdump tmp/deploy/images/raspberrypi3-64/my-custom-image-raspberrypi3-64.rootfs.manifest
grep systemd tmp/deploy/images/raspberrypi3-64/my-custom-image-raspberrypi3-64.rootfs.manifest
```


