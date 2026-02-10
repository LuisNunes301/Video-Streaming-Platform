import { useEffect, useRef } from "react";
import { updateProgress } from "../services/playbackService";

export function usePlayback(contentId: string) {
  const videoRef = useRef<HTMLVideoElement | null>(null);

  useEffect(() => {
    const interval = setInterval(() => {
      const video = videoRef.current;
      if (!video || video.duration === 0) return;

      updateProgress(contentId, Math.floor(video.currentTime));
    }, 10000);

    return () => clearInterval(interval);
  }, [contentId]);

  return videoRef;
}
