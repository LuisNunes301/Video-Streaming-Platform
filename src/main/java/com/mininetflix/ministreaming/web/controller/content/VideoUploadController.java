package com.mininetflix.ministreaming.web.controller.content;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mininetflix.ministreaming.application.content.dto.UploadVideoOutput;
import com.mininetflix.ministreaming.application.content.port.VideoCatalogRepository;
import com.mininetflix.ministreaming.application.content.usecase.UploadVideoUseCase;

import com.mininetflix.ministreaming.domain.content.VideoContent;

@RestController
@RequestMapping("/videos")
public class VideoUploadController {

    private final UploadVideoUseCase uploadVideoUseCase;
    private final VideoCatalogRepository catalogRepository;

    public VideoUploadController(
            UploadVideoUseCase uploadVideoUseCase,
            VideoCatalogRepository catalogRepository) {

        this.uploadVideoUseCase = uploadVideoUseCase;
        this.catalogRepository = catalogRepository;
    }

    @PostMapping(value = "/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @PreAuthorize("")
    public ResponseEntity<UploadVideoOutput> upload(
            @RequestParam String title,
            @RequestParam String bucket,
            @RequestParam double duration,
            @RequestParam MultipartFile file) {

        return ResponseEntity.ok(
                uploadVideoUseCase.execute(title, bucket, file));
    };

    // 🔥 Listar catálogo
    @GetMapping
    public ResponseEntity<List<VideoContent>> list() {
        return ResponseEntity.ok(
                catalogRepository.findAll());
    }
}
