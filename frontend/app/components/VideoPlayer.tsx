"use client";

import { useEffect, useState } from "react";
import { usePlayback } from "../hooks/usePlayback";

const CONTENT_ID = "1";

export default function VideoPlayer() {
  const videoRef = usePlayback(CONTENT_ID);
  const [videoUrl, setVideoUrl] = useState<string | null>(null);
  const [startAt, setStartAt] = useState<number>(0);

  useEffect(() => {
    fetch(`http://localhost:8080/playback/start/${CONTENT_ID}`, { method: "POST" })
      .then(res => res.json())
      .then(data => {
        setVideoUrl(data.videoUrl);
        setStartAt(data.startAt);
      });
  }, []);

  return (
    <div>
      {videoUrl ? (
       <video
       ref={videoRef}
       src={videoUrl}
       controls
       crossOrigin="anonymous"
       onLoadedMetadata={() => {
         if (videoRef.current) {
           videoRef.current.currentTime = startAt;
         }
       }}
     />
     
      ) : (
        <p>Carregando vídeo...</p>
      )}
    </div>
  );
}
