package com.mininetflix.ministreaming.application.content.usecase;

import java.util.List;

import com.mininetflix.ministreaming.domain.content.VideoContent;

public interface ListVideosUseCase {
    List<VideoContent> execute();
}
