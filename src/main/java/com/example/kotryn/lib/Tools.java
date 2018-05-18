package com.example.kotryn.lib;

import java.time.Duration;

public class Tools {
    public static String formatDuration(Duration duration) {
        long seconds = duration.getSeconds();
        long absSeconds = Math.abs(seconds);
        String formattedAbsSeconds = String.format("%02d:%02d:%02d",
                absSeconds / 3600, (absSeconds % 3600) / 60, (absSeconds % 60));
        return seconds < 0 ? "-" + formattedAbsSeconds : formattedAbsSeconds;
    }
}
