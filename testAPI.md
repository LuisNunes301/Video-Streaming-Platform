ENDPOINTS COMPLETOS DE PLAYBACK

Base URL:

http://localhost:8080/playback


Todos exigem JWT no header:

Authorization: Bearer SEU_TOKEN

▶ 1.1 START PLAYBACK
Endpoint
POST /playback/start/{contentId}

Exemplo
POST /playback/start/movie-1

Resposta
{
  "videoUrl": "http://localhost:9000/videos/movie-1.mp4?...assinatura...",
  "startAt": 350.0
}

O que acontece internamente

Busca vídeo no catálogo

Gera presigned URL no MinIO

Busca progresso salvo no Redis

Retorna:

URL assinada

Tempo salvo

1.2 SALVAR PROGRESSO
Endpoint
POST /playback/progress

Body
{
  "contentId": "movie-1",
  "currentTime": 420.5
}


duration NÃO precisa vir do frontend

O que acontece

Backend busca duração oficial no catálogo

Atualiza entidade

Marca completed se >= 95%

Salva no Redis (TTL 7 dias)

1.3 BUSCAR PROGRESSO ESPECÍFICO
GET /playback/{contentId}

Exemplo
GET /playback/movie-1

Resposta
{
  "userId": "1",
  "contentId": "movie-1",
  "currentTime": 420.5,
  "duration": 7200,
  "completed": false,
  "lastUpdated": "2026-02-11T15:20:00Z"
}

1.4 CONTINUE WATCHING
GET /playback/continue

Resposta
[
  {
    "contentId": "movie-1",
    "currentTime": 420.5,
    "duration": 7200,
    "completed": false
  }
]


Apenas não concluídos

Ordenado por mais recente