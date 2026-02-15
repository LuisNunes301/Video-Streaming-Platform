# Video-Streaming-Platform (MinIO + Spring + Redis + Events)

Plataforma de streaming de vídeos com controle de progresso de playback, URLs temporárias via Object Storage (MinIO/S3), cache de estado em Redis, persistência histórica e arquitetura orientada a eventos.

Structure:
## 1. High-Level Architecture


```mermaid
graph TD
    %% Camadas principais
    subgraph Domain
        DC[content.VideoContent]
        DP[playback.PlaybackState]
        DU[user.User, user.UserRole]
        DE[exceptions: VideoNotFoundException, EmailAlreadyExistsException...]
    end

    subgraph Application
        AC[content.usecase: ListVideosUseCase, UploadVideoUseCase]
        AD[content.port: VideoCatalogRepository, VideoStorageService]
        AP[playback.usecase: GetPlaybackProgressUseCase, StartPlaybackUseCase, SavePlaybackProgressUseCase]
        AU[user.usecase: AuthenticateUserUseCase, RegisterUserUseCase]
    end

    subgraph Infrastructure
        IC[content.repository: JpaVideoCatalogRepository, DataVideoJpaRepository]
        IS[content.storage: MinioVideoStorageService]
        IP[playback.repository: PlaybackRepositoryImpl, RedisPlaybackRepository]
        IU[user.persistence: UserJpaRepositoryAdapter, UserJpaRepository]
        ISec[user.security: JwtTokenService, BCryptPasswordEncoderAdapter]
    end

    subgraph Web
        WC[controller.content.VideoUploadController]
        WP[controller.playback.PlaybackController]
        WU[controller.auth.AuthController]
    end

    %% Dependências
    AC -->|usa| DC
    AD -->|porta para| IC
    AP -->|usa| DP
    AU -->|usa| DU

    WC --> AC
    WP --> AP
    WU --> AU

    IC --> AC
    IS --> AC
    IP --> AP
    IU --> AU
    ISec --> AU
```