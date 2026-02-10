package com.mininetflix.ministreaming.domain.playback;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaybackState {
    private String userId;
    private String contentId;
    private double currentTime;
    private double duration;
    private boolean completed;

}
