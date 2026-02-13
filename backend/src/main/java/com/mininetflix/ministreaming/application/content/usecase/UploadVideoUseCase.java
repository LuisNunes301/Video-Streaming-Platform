package com.mininetflix.ministreaming.application.content.usecase;

import com.mininetflix.ministreaming.domain.content.UploadVideoInput;
import com.mininetflix.ministreaming.domain.content.UploadVideoOutput;

public interface UploadVideoUseCase {
    UploadVideoOutput execute(UploadVideoInput input);
}
