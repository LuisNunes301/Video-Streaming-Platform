package com.mininetflix.ministreaming.application.playback.usecase;

import org.springframework.stereotype.Service;

import com.mininetflix.ministreaming.application.content.port.VideoCatalogRepository;
import com.mininetflix.ministreaming.application.playback.port.PlaybackRepository;
import com.mininetflix.ministreaming.domain.playback.PlaybackState;

@Service
public class SavePlaybackProgressUseCaseImpl
                implements SavePlaybackProgressUseCase {

        private final PlaybackRepository playbackRepository;
        private final VideoCatalogRepository videoCatalogRepository;

        public SavePlaybackProgressUseCaseImpl(
                        PlaybackRepository playbackRepository,
                        VideoCatalogRepository videoCatalogRepository) {
                this.playbackRepository = playbackRepository;
                this.videoCatalogRepository = videoCatalogRepository;
        }

        @Override
        public void execute(PlaybackState input) {

                var video = videoCatalogRepository
                                .findById(input.getContentId())
                                .orElseThrow(() -> new IllegalArgumentException("Video not found"));

                double officialDuration = video.getDuration();

                PlaybackState state = playbackRepository
                                .findByUserAndContent(input.getUserId(), input.getContentId())
                                .orElseGet(() -> new PlaybackState(
                                                input.getUserId(),
                                                input.getContentId(),
                                                officialDuration));

                state.updateProgress(
                                input.getCurrentTime(),
                                officialDuration);

                playbackRepository.save(state);
        }
}
