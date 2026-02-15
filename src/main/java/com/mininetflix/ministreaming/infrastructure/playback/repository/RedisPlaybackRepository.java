// package com.mininetflix.ministreaming.infrastructure.playback.repository;

// import java.time.Duration;
// import java.time.Instant;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;
// import java.util.Set;

// import org.springframework.data.redis.core.RedisTemplate;
// import org.springframework.stereotype.Component;

// import
// com.mininetflix.ministreaming.application.playback.port.PlaybackRepository;
// import com.mininetflix.ministreaming.domain.playback.PlaybackState;

// import lombok.RequiredArgsConstructor;

// @Component
// @RequiredArgsConstructor
// public class RedisPlaybackRepository implements PlaybackRepository {

// private final RedisTemplate<String, PlaybackState> redisTemplate;

// private static final Duration TTL = Duration.ofDays(7);

// @Override
// public Optional<PlaybackState> findByUserAndContent(
// String userId,
// String contentId) {

// String key = buildKey(userId, contentId);
// return Optional.ofNullable(
// redisTemplate.opsForValue().get(key));
// }

// @Override
// public List<PlaybackState> findByUser(String userId) {

// String pattern = "playback:" + userId + ":*";
// Set<String> keys = redisTemplate.keys(pattern);

// if (keys == null || keys.isEmpty()) {
// return List.of();
// }

// List<PlaybackState> result = new ArrayList<>();

// for (String key : keys) {
// PlaybackState state = redisTemplate.opsForValue().get(key);

// if (state != null) {
// result.add(state);
// }
// }

// return result;
// }

// @Override
// public PlaybackState save(PlaybackState state) {

// String key = buildKey(state.getUserId(), state.getContentId());

// redisTemplate.opsForValue()
// .set(key, state, TTL);

// return state;
// }

// private String buildKey(
// String userId,
// String contentId) {

// return "playback:" + userId + ":" + contentId;
// }
// }