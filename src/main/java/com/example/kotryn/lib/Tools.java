package com.example.kotryn.lib;

import com.example.kotryn.repository.ContextRepository;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;

import java.time.Duration;

public class Tools {

    /*public static void createEmptyRepositories(JobRepository jobRepository, ContextRepository contextRepository, ProcessDescriptorRepository processDescriptorRepository) {
        JobRepository.createRepository();
        ContextRepository.createRepository();
        ProcessDescriptorRepository.createRepository();
    }*/

    public static String formatDuration(Duration duration) {
        long seconds = duration.getSeconds();
        long absSeconds = Math.abs(seconds);
        String formattedAbsSeconds = String.format("%02d:%02d:%02d",
                absSeconds / 3600, (absSeconds % 3600) / 60, (absSeconds % 60));
        return seconds < 0 ? "-" + formattedAbsSeconds : formattedAbsSeconds;
    }
}
