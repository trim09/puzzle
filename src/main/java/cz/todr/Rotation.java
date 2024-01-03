package cz.todr;

enum Rotation {
    XY {
        public int withIncrement(Point p, int mainAxis, int secondaryAxis) {
            return Point.indexAt(p.x() + mainAxis, p.y() + secondaryAxis, p.z());
        }
    },
    XZ {
        public int withIncrement(Point p, int mainAxis, int secondaryAxis) {
            return Point.indexAt(p.x() + mainAxis, p.y(), p.z() + secondaryAxis);
        }
    },
    YX {
        public int withIncrement(Point p, int mainAxis, int secondaryAxis) {
            return Point.indexAt(p.x() + secondaryAxis, p.y() + mainAxis, p.z());
        }
    },
    YXm {
        public int withIncrement(Point p, int mainAxis, int secondaryAxis) {
            return Point.indexAt(p.x() - secondaryAxis, p.y() + mainAxis, p.z());
        }
    },
    YZ {
        public int withIncrement(Point p, int mainAxis, int secondaryAxis) {
            return Point.indexAt(p.x(), p.y() + mainAxis, p.z() + secondaryAxis);
        }
    },
    ZX {
        public int withIncrement(Point p, int mainAxis, int secondaryAxis) {
            return Point.indexAt(p.x() + secondaryAxis, p.y(), p.z() + mainAxis);
        }
    },
    ZXm {
        public int withIncrement(Point p, int mainAxis, int secondaryAxis) {
            return Point.indexAt(p.x() - secondaryAxis, p.y(), p.z() + mainAxis);
        }
    },
    ZY {
        public int withIncrement(Point p, int mainAxis, int secondaryAxis) {
            return Point.indexAt(p.x(), p.y() + secondaryAxis, p.z() + mainAxis);
        }
    },
    ZYm {
        public int withIncrement(Point p, int mainAxis, int secondaryAxis) {
            return Point.indexAt(p.x(), p.y() - secondaryAxis, p.z() + mainAxis);
        }
    };

    public abstract int withIncrement(Point base, int mainAxis, int secondaryAxis);
}
