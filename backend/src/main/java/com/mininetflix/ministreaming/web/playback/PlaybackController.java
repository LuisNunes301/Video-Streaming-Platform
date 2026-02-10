package com.mininetflix.ministreaming.web.playback;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mininetflix.ministreaming.application.playback.usecase.SavePlaybackProgressUseCase;
import com.mininetflix.ministreaming.application.playback.usecase.StartPlaybackUseCase;

import com.mininetflix.ministreaming.domain.playback.PlaybackState;
import com.mininetflix.ministreaming.web.playback.dto.PlaybackProgressRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import java.util.Map;

@RestController
@RequestMapping("/playback")
// @CrossOrigin(origins = "http://localhost:3001", allowedHeaders = "*", methods
// = {
// RequestMethod.GET,
// RequestMethod.POST,
// RequestMethod.PUT,
// RequestMethod.DELETE,
// RequestMethod.OPTIONS
// })
public class PlaybackController {

        private final StartPlaybackUseCase startPlaybackUseCase;
        private final SavePlaybackProgressUseCase savePlaybackProgressUseCase;

        public PlaybackController(
                        StartPlaybackUseCase startPlaybackUseCase,
                        SavePlaybackProgressUseCase savePlaybackProgressUseCase) {
                this.startPlaybackUseCase = startPlaybackUseCase;
                this.savePlaybackProgressUseCase = savePlaybackProgressUseCase;
        }

        @PostMapping("/start/{contentId}")
        public ResponseEntity<?> start(
                        @PathVariable String contentId) {
                String userId = "1"; // fake

                var output = startPlaybackUseCase.execute(userId, contentId);

                return ResponseEntity.ok(
                                Map.of(
                                                "videoUrl", output.videoUrl(),
                                                "startAt", output.startAt()));
        }

        @PostMapping("/progress")
        public ResponseEntity<Void> progress(
                        @RequestBody PlaybackProgressRequest request) {
                String userId = "1";

                PlaybackState state = new PlaybackState(
                                userId,
                                request.getContentId(),
                                request.getCurrentTime(),
                                0.0,
                                false);

                savePlaybackProgressUseCase.execute(state);

                return ResponseEntity.ok().build();
        }
}