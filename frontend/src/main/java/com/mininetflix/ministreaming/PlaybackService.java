package com.mininetflix.ministreaming;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class PlaybackService {

    private final RedisTemplate<String, PlaybackState> redisTemplate;

    public PlaybackService(RedisTemplate<String, PlaybackState> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveProgress(String userId, String contentId, PlaybackState state) {

        String key = "playback:" + userId + ":" + contentId;

        redisTemplate.opsForValue().set(
                key,
                state,
                Duration.ofDays(7) // depois refinamos com TTL inteligente
        );
    }

    public PlaybackState getProgress(String userId, String contentId) {
        String key = "playback:" + userId + ":" + contentId;
        return redisTemplate.opsForValue().get(key);
    }
}
