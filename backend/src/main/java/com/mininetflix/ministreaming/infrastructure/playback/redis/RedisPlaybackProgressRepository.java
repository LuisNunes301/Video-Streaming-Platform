package com.mininetflix.ministreaming.infrastructure.redis;

import java.time.Duration;
import java.util.Optional;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.mininetflix.ministreaming.application.playback.port.PlaybackProgressRepository;
import com.mininetflix.ministreaming.domain.playback.PlaybackState;

@Component
public class RedisPlaybackProgressRepository implements PlaybackProgressRepository {

    private final RedisTemplate<String, PlaybackState> redisTemplate;

    public RedisPlaybackProgressRepository(
            RedisTemplate<String, PlaybackState> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void save(String userId, String contentId, PlaybackState state) {
        String key = buildKey(userId, contentId);

        redisTemplate.opsForValue().set(
                key,
                state,
                Duration.ofDays(7));
    }

    @Override
    public Optional<PlaybackState> find(String userId, String contentId) {
        String key = buildKey(userId, contentId);
        return Optional.ofNullable(redisTemplate.opsForValue().get(key));
    }

    private String buildKey(String userId, String contentId) {
        return "playback:" + userId + ":" + contentId;
    }
}
