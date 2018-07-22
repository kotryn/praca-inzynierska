package com.example.kotryn.processes;

import java.io.IOException;
import java.util.Locale;

public class OSInfo {
    private static SystemType systemType = SystemType.UNKNOWN;

    static {
        try {
            String osName = System.getProperty("os.name");
            if (osName == null) {
                throw new IOException("os.name not found");
            }
            osName = osName.toLowerCase(Locale.ENGLISH);
            if (osName.contains("windows")) {
                systemType = SystemType.WINDOWS;
            } else if (osName.contains("linux")){
                systemType = SystemType.LINUX;
            } else if (osName.contains("linux")
                    || osName.contains("mpe/ix")
                    || osName.contains("freebsd")
                    || osName.contains("irix")
                    || osName.contains("digital unix")
                    || osName.contains("unix")) {
                systemType = SystemType.UNIX;
            } else if (osName.contains("mac os")) {
                systemType = SystemType.MAC;
            } else if (osName.contains("sun os")
                    || osName.contains("sunos")
                    || osName.contains("solaris")) {
                systemType = SystemType.POSIX_UNIX;
            } else if (osName.contains("hp-ux")
                    || osName.contains("aix")) {
                systemType = SystemType.POSIX_UNIX;
            } else {
                systemType = SystemType.OTHER;
            }

        } catch (Exception ex) {
            systemType = SystemType.UNKNOWN;
        }
    }

    public static SystemType getOs() {
        return systemType;
    }
}