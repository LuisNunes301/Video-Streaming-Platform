package com.mininetflix.ministreaming;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/playback")
@CrossOrigin(origins = "http://localhost:3001", allowedHeaders = "*", methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.PUT,
        RequestMethod.DELETE,
        RequestMethod.OPTIONS
})
public class PlaybackController {
    private final VideoStorageService videoStorageService;
    private final PlaybackService playbackService;

    public PlaybackController(VideoStorageService videoStorageService, PlaybackService playbackService) {
        this.playbackService = playbackService;
        this.videoStorageService = videoStorageService;
    }

    @PostMapping("/start/{contentId}")
    public ResponseEntity<?> start(@PathVariable String contentId) {

        String userId = "1"; // fake por enquanto
        // Simulando busca no banco
        String videoPath = "videoplayback.mp4";

        String videoUrl = videoStorageService.generatePresignedUrl(videoPath);

        Double startAt = Optional.ofNullable(playbackService
                .getProgress(userId, contentId))
                .map(PlaybackState::getCurrentTime)
                .orElse(0.0);

        return ResponseEntity.ok(
                Map.of(
                        "videoUrl", videoUrl,
                        "startAt", startAt));
    }

    @PostMapping("/progress")
    public ResponseEntity<Void> progress(@RequestBody PlaybackProgressRequest request) {

        System.out.println("CHEGOU NO CONTROLLER");

        String userId = "1";

        playbackService.saveProgress(
                userId,
                request.getContentId(),
                new PlaybackState(
                        userId,
                        request.getContentId(),
                        request.getCurrentTime(),
                        0.0,
                        false));

        return ResponseEntity.ok().build();
    }

}
