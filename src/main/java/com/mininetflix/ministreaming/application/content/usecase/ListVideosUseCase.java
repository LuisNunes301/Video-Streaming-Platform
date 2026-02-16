package com.mininetflix.ministreaming.application.content.usecase;

import java.util.List;

import com.mininetflix.ministreaming.application.content.dto.VideoResponse;

public interface ListVideosUseCase {
    List<VideoResponse> execute();
}
