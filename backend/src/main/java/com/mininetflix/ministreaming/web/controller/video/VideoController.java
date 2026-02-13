package com.mininetflix.ministreaming.web.controller.video;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mininetflix.ministreaming.application.content.usecase.ListVideosUseCase;
import com.mininetflix.ministreaming.domain.content.VideoContent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class VideoController {

    private final ListVideosUseCase listVideosUseCase;

    public VideoController(ListVideosUseCase listVideosUseCase) {
        this.listVideosUseCase = listVideosUseCase;
    }

    @GetMapping("/videos")
    public List<VideoContent> listVideos() {
        return listVideosUseCase.execute();
    }
}
