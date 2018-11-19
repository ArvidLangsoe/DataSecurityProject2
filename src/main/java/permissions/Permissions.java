package permissions;

import java.io.Serializable;

public enum Permissions implements Serializable {
    PRINT,
    QUEUE,
    TOPQUEUE,
    START,
    STOP,
    RESTART,
    STATUS,
    READCONFIG,
    SETCONFIG
}
