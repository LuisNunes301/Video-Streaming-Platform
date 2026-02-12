package com.mininetflix.ministreaming.web.controller.playback;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.mininetflix.ministreaming.application.playback.usecase.GetContinueWatchingUseCase;
import com.mininetflix.ministreaming.application.playback.usecase.GetPlaybackProgressUseCase;
import com.mininetflix.ministreaming.application.playback.usecase.SavePlaybackProgressUseCase;
import com.mininetflix.ministreaming.application.playback.usecase.StartPlaybackUseCase;

import com.mininetflix.ministreaming.domain.playback.PlaybackState;
import com.mininetflix.ministreaming.web.controller.playback.dto.PlaybackProgressRequest;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/playback")
@RequiredArgsConstructor
public class PlaybackController {

        private final StartPlaybackUseCase startPlaybackUseCase;
        private final SavePlaybackProgressUseCase savePlaybackProgressUseCase;
        private final GetPlaybackProgressUseCase getPlaybackProgressUseCase;
        private final GetContinueWatchingUseCase getContinueWatchingUseCase;

        // 🎬 Start playback
        @PostMapping("/start/{contentId}")
        public ResponseEntity<Map<String, Object>> start(
                        @AuthenticationPrincipal(expression = "id") String userId,
                        @PathVariable String contentId) {

                var output = startPlaybackUseCase.execute(userId, contentId);

                return ResponseEntity.ok(
                                Map.of(
                                                "videoUrl", output.videoUrl(),
                                                "startAt", output.startAt()));
        }

        // ⏱ Save progress
        @PostMapping("/progress")
        public ResponseEntity<Void> progress(
                        @AuthenticationPrincipal(expression = "id") String userId,
                        @RequestBody PlaybackProgressRequest request) {

                PlaybackState input = new PlaybackState(
                                userId,
                                request.getContentId(),
                                0);

                input.updateProgress(
                                request.getCurrentTime(),
                                0);

                savePlaybackProgressUseCase.execute(input);

                return ResponseEntity.ok().build();
        }

        @GetMapping("/continue")
        public ResponseEntity<List<PlaybackState>> continueWatching(
                        @AuthenticationPrincipal(expression = "id") String userId) {

                List<PlaybackState> result = getContinueWatchingUseCase.execute(userId);

                return ResponseEntity.ok(result);
        }

        @GetMapping("/{contentId}")
        public ResponseEntity<PlaybackState> getProgress(
                        @AuthenticationPrincipal(expression = "id") String userId,
                        @PathVariable String contentId) {

                return getPlaybackProgressUseCase
                                .execute(userId, contentId)
                                .map(ResponseEntity::ok)
                                .orElse(ResponseEntity.notFound().build());
        }
}
