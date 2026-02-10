import axios from "axios";

export const startPlayback = async (contentId: string) => {
  const res = await axios.post(`/playback/start/${contentId}`);
  return res.data;
};

export function updateProgress(
  contentId: string,
  currentTime: number,
  completed = false
) {
  fetch("http://localhost:8080/playback/progress", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      contentId,
      currentTime,
      completed
    })
  });
}
