package com.mininetflix.ministreaming.application.dto;

public record StartPlaybackOutput(
                String videoUrl,
                double startAt) {
}