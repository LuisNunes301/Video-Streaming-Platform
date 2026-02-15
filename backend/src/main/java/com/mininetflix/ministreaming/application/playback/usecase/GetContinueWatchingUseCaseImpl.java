package com.mininetflix.ministreaming.application.playback.usecase;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mininetflix.ministreaming.application.playback.port.PlaybackRepository;
import com.mininetflix.ministreaming.domain.playback.PlaybackState;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetContinueWatchingUseCaseImpl implements GetContinueWatchingUseCase {

    private final PlaybackRepository playbackRepository;

    @Override
    public List<PlaybackState> execute(String userId) {

        return playbackRepository.findByUserAndNotCompleted(userId).stream()
                .sorted(Comparator.comparing(PlaybackState::getLastUpdated,
                        Comparator.nullsLast(Comparator.reverseOrder())))
                .limit(10)
                .toList();
    }
}
