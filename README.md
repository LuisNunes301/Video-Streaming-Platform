# MiniStreaming

MiniStreaming é uma aplicação backend desenvolvida em **Java + Spring
Boot**, projetada com **Clean Architecture + DDD + Event-Driven
Architecture**, simulando a base estrutural de uma plataforma de
streaming moderna.

``` mermaid
graph TD
    %% =========================
    %% DOMAIN
    %% =========================
    subgraph Domain
        DC[content.VideoContent]
        DS[content.VideoStatus]
        DP[playback.PlaybackState]
        DU[user.User]
        DR[user.UserRole]
        DE[exceptions: VideoNotFoundException, EmailAlreadyExistsException, BusinessException...]
    end

    %% =========================
    %% APPLICATION
    %% =========================
    subgraph Application
        AC1[content.usecase.UploadVideoUseCase]
        AC2[content.usecase.ListVideosUseCase]
        AC3[content.usecase.ProcessVideoUseCase]

        AD1[content.port.VideoCatalogRepository]
        AD2[content.port.VideoStorageService]
        AD3[content.port.VideoMetadataExtractor]
        AD4[content.port.VideoProcessingPublisher]

        AP1[playback.usecase.StartPlaybackUseCase]
        AP2[playback.usecase.GetPlaybackProgressUseCase]
        AP3[playback.usecase.SavePlaybackProgressUseCase]

        AU1[user.usecase.RegisterUserUseCase]
        AU2[user.usecase.AuthenticateUserUseCase]
    end

    %% =========================
    %% INFRASTRUCTURE
    %% =========================
    subgraph Infrastructure
        IC1[content.repository.JpaVideoCatalogRepository]
        IC2[content.repository.DataVideoJpaRepository]
        IE1[content.entity.VideoEntity]

        IS1[content.storage.MinioVideoStorageService]

        IM1[content.metadata.FFmpegMetadataExtractor]

        IR1[messaging.RabbitVideoProcessingPublisher]
        IR2[messaging.VideoProcessingConsumer]

        IP1[playback.repository.PlaybackRepositoryImpl]
        IP2[playback.repository.RedisPlaybackRepository]

        IU1[user.persistence.UserJpaRepositoryAdapter]
        IU2[user.persistence.UserJpaRepository]

        ISec1[user.security.JwtTokenService]
        ISec2[user.security.BCryptPasswordEncoderAdapter]
    end

    %% =========================
    %% WEB
    %% =========================
    subgraph Web
        WC1[controller.content.VideoUploadController]
        WC2[controller.content.VideoCatalogController]
        WP1[controller.playback.PlaybackController]
        WU1[controller.auth.AuthController]
    end

    %% =========================
    %% DOMAIN RELATIONS
    %% =========================
    DC --> DS

    %% =========================
    %% APPLICATION DEPENDENCIES
    %% =========================
    AC1 --> DC
    AC1 --> AD1
    AC1 --> AD2
    AC1 --> AD4

    AC3 --> DC
    AC3 --> AD1
    AC3 --> AD3

    AC2 --> AD1

    AP1 --> DP
    AP2 --> DP
    AP3 --> DP

    AU1 --> DU
    AU2 --> DU

    %% =========================
    %% WEB → APPLICATION
    %% =========================
    WC1 --> AC1
    WC2 --> AC2
    WP1 --> AP1
    WP1 --> AP2
    WP1 --> AP3
    WU1 --> AU1
    WU1 --> AU2

    %% =========================
    %% INFRASTRUCTURE IMPLEMENTS PORTS
    %% =========================
    IC1 --> AD1
    IS1 --> AD2
    IM1 --> AD3
    IR1 --> AD4

    IC2 --> IC1
    IE1 --> IC1

    IR2 --> AC3

    IP1 --> AP1
    IP2 --> AP3

    IU1 --> AU1
    IU2 --> IU1

    ISec1 --> AU2
    ISec2 --> AU1
```
------------------------------------------------------------------------

# Funcionalidades

-   Upload de vídeos
-   Processamento assíncrono com RabbitMQ
-   Extração de metadata (ex: duração, resolução)
-   Geração de HLS
-   Catálogo com controle de status
-   Autenticação com JWT
-   Persistência com JPA
-   Cache de playback com Redis
-   Armazenamento de objetos com MinIO (S3 compatible)

------------------------------------------------------------------------

# Arquitetura

O projeto segue separação clara de responsabilidades:

    Domain
    Application (Use Cases + Ports)
    Infrastructure (Adapters)
    Web (Controllers)

## Princípios aplicados

-   Clean Architecture
-   Domain-Driven Design (DDD)
-   Ports and Adapters
-   Event-Driven Architecture
-   Separação entre regra de negócio e infraestrutura

------------------------------------------------------------------------

# Ciclo de Vida do Vídeo

Fluxo de estados:

    UPLOADING → PROCESSING → READY
                          ↘ FAILED

### Estados:

-   **UPLOADING** → vídeo recebido e armazenado
-   **PROCESSING** → evento publicado no RabbitMQ
-   **READY** → metadata extraída + HLS gerado
-   **FAILED** → erro no pipeline

O `VideoStatus` é a única fonte de verdade do estado do vídeo.

------------------------------------------------------------------------

# Fluxo Assíncrono

1.  Upload do vídeo via endpoint
2.  Armazenamento no MinIO
3.  Publicação de evento no RabbitMQ
4.  Consumer processa o vídeo
5.  Atualização de status no banco

------------------------------------------------------------------------

# Segurança

-   Autenticação via JWT
-   Senhas criptografadas com BCrypt
-   Controle de acesso baseado em roles

------------------------------------------------------------------------

# ▶Playback

-   Início de reprodução
-   Salvamento de progresso
-   Recuperação de estado
-   Persistência em Redis

------------------------------------------------------------------------

# Stack Tecnológica

-   Java 17+
-   Spring Boot
-   Spring Data JPA
-   RabbitMQ
-   Redis
-   MinIO
-   JWT
-   FFmpeg
-   Docker

------------------------------------------------------------------------

#  Executando com Docker (exemplo)

``` bash
docker-compose up -d
```

Serviços esperados:

-   PostgreSQL
-   RabbitMQ
-   Redis
-   MinIO

------------------------------------------------------------------------

#  Estrutura do Projeto

    com.mininetflix.ministreaming
    │
    ├── domain
    ├── application
    ├── infrastructure
    └── web

------------------------------------------------------------------------

#  Objetivo do Projeto

Este projeto foi desenvolvido como laboratório arquitetural para:

-   Explorar arquitetura limpa na prática
-   Modelar agregados corretamente
-   Trabalhar com processamento assíncrono
-   Simular padrões usados por plataformas reais de streaming
-   Evoluir para possível arquitetura de microsserviços

------------------------------------------------------------------------

#  Autor

Desenvolvido por Luis Nunes.
