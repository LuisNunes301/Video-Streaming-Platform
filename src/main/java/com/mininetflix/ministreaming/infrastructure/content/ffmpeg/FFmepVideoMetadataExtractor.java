package com.mininetflix.ministreaming.infrastructure.content.ffmpeg;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.web.multipart.MultipartFile;

import com.mininetflix.ministreaming.application.content.port.VideoMetadataExtractor;

public class FFmepVideoMetadataExtractor implements VideoMetadataExtractor {

    @Override
    public double extractDuration(MultipartFile file) {

        try {
            File tempFile = Files.createTempFile("video-", ".mp4").toFile();
            file.transferTo(tempFile);

            ProcessBuilder builder = new ProcessBuilder(
                    "ffprobe",
                    "-v", "error",
                    "-show_entries", "format=duration",
                    "-of", "default=noprint_wrappers=1:nokey=1",
                    tempFile.getAbsolutePath());

            Process process = builder.start();
            String output = new String(process.getInputStream().readAllBytes());

            tempFile.delete();

            return Double.parseDouble(output.trim());

        } catch (IOException e) {
            throw new RuntimeException("Error extracting video metadata", e);
        }
    }
}