# MiniStreaming

MiniStreaming é uma aplicação backend desenvolvida em **Java + Spring
Boot**, projetada com **Clean Architecture + DDD + Event-Driven
Architecture**, simulando a base estrutural de uma plataforma de
streaming moderna.

### DIAGRAMA 1: CAMADA DE DOMÍNIO 
``` mermaid
graph TB
    title["CAMADA DE DOMÍNIO<br/>Regras de Negócio e Entidades"]
    style title fill:#fff,stroke:none,font-family:Arial,font-size:16px,font-weight:bold

    subgraph Dominio["ENTIDADES CENTRAIS"]
        direction TB
        
        Video["VideoContent"]
        Status["VideoStatus<br/>UPLOADING | PROCESSING | READY | FAILED"]
        Playback["PlaybackState"]
        Usuario["User"]
        Perfil["UserRole"]
        
        Video --> Status
        Usuario --> Perfil
    end

    subgraph Excecoes["EXCEÇÕES DE NEGÓCIO"]
        Ex1["VideoNotFoundException"]
        Ex2["EmailAlreadyExistsException"]
        Ex3["BusinessException"]
    end

    subgraph Responsabilidades["RESPONSABILIDADES"]
        R1["• Define entidades e estados"]
        R2["• Implementa transições válidas de status"]
        R3["• Não depende de framework"]
        R4["• Núcleo da aplicação"]
    end

    classDef entidades fill:#f3e5f5,stroke:#7b1fa2,stroke-width:3px
    classDef excecoes fill:#ffebee,stroke:#c62828,stroke-width:2px
    classDef responsabilidades fill:#f5f5f5,stroke:#9e9e9e,stroke-width:1px,stroke-dasharray: 3 3
    
    class Video,Status,Playback,Usuario,Perfil entidades
    class Ex1,Ex2,Ex3 excecoes
    class R1,R2,R3,R4 responsabilidades

```
### DIAGRAMA 2: CAMADA DE APLICAÇÃO (Casos de Uso)
```mermaid
graph TB
    title["CAMADA DE APLICAÇÃO<br/>Casos de Uso e Ports"]
    style title fill:#fff,stroke:none,font-family:Arial,font-size:16px,font-weight:bold

    subgraph UseCases["CASOS DE USO"]
        direction TB
        
        UC1["UploadVideoUseCase"]
        UC2["ListVideosUseCase"]
        UC3["ProcessVideoUseCase"]
        
        UP1["StartPlaybackUseCase"]
        UP2["GetPlaybackProgressUseCase"]
        UP3["SavePlaybackProgressUseCase"]
        
        UU1["RegisterUserUseCase"]
        UU2["AuthenticateUserUseCase"]
    end

    subgraph Ports["PORTS (INTERFACES)"]
        direction TB
        P1["VideoCatalogRepository"]
        P2["VideoStorageService"]
        P3["VideoMetadataExtractor"]
        P4["VideoProcessingPublisher"]
        P5["PlaybackRepository"]
        P6["UserRepository"]
    end

    UC1 --> P1 & P2 & P4
    UC2 --> P1
    UC3 --> P1 & P3
    
    UP1 --> P5
    UP2 --> P5
    UP3 --> P5
    
    UU1 --> P6
    UU2 --> P6

    classDef usecase fill:#e3f2fd,stroke:#1565c0,stroke-width:3px
    classDef ports fill:#fff3e0,stroke:#ff6f00,stroke-width:2px,stroke-dasharray: 5 5
    
    class UC1,UC2,UC3,UP1,UP2,UP3,UU1,UU2 usecase
    class P1,P2,P3,P4,P5,P6 ports


```
### DIAGRAMA 3: CAMADA DE INFRAESTRUTURA (Implementações)
```mermaid
graph TB
    title["CAMADA DE INFRAESTRUTURA<br/>Implementações Técnicas"]
    style title fill:#fff,stroke:none,font-family:Arial,font-size:16px,font-weight:bold

    subgraph Persistencia["PERSISTÊNCIA"]
        IR1["JpaVideoCatalogRepository"]
        IR2["PlaybackRepositoryImpl"]
        IR3["UserJpaRepositoryAdapter"]
        DB["PostgreSQL"]
    end

    subgraph Storage["ARMAZENAMENTO"]
        S1["MinioVideoStorageService"]
        S2["MinIO Server"]
    end

    subgraph Mensageria["MENSAGERIA"]
        M1["RabbitMQPublisher"]
        M2["RabbitMQConsumer"]
        M3["RabbitMQ Broker"]
    end

    subgraph Processamento["PROCESSAMENTO"]
        F1["FFmpeg (ProcessBuilder)"]
        F2["VideoMetadataExtractorImpl"]
    end

    IR1 --> DB
    IR2 --> DB
    IR3 --> DB
    
    S1 --> S2
    
    M1 --> M3
    M3 --> M2
    
    M2 --> F1
    F1 --> S1

    classDef infra fill:#e8f5e8,stroke:#2e7d32,stroke-width:3px
    classDef externo fill:#fff3e0,stroke:#e65100,stroke-width:3px
    
    class IR1,IR2,IR3,S1,M1,M2,F1,F2 infra
    class DB,S2,M3 externo

``` 
### DIAGRAMA 4: CAMADA WEB (Interface com Usuário)
```mermaid
graph TB
    title["CAMADA WEB<br/>Controllers REST"]
    style title fill:#fff,stroke:none,font-family:Arial,font-size:16px,font-weight:bold

    subgraph Controllers["CONTROLLERS"]
        C1["VideoUploadController"]
        C2["VideoCatalogController"]
        C3["PlaybackController"]
        C4["AuthController"]
    end

    subgraph Endpoints["ENDPOINTS"]
        
        subgraph Content["Conteúdo"]
            E1["POST /api/videos/upload"]
            E2["GET /api/videos"]
            E3["GET /api/videos/{id}"]
        end
        
        subgraph Playback["Reprodução"]
            E4["POST /api/playback/start"]
            E5["GET /api/playback/progress/{id}"]
            E6["PUT /api/playback/progress"]
        end
        
        subgraph Auth["Autenticação"]
            E7["POST /api/auth/register"]
            E8["POST /api/auth/login"]
        end
    end

    C1 --> E1
    C2 --> E2 & E3
    C3 --> E4 & E5 & E6
    C4 --> E7 & E8

    classDef web fill:#fff3e0,stroke:#e65100,stroke-width:3px
    classDef endpoints fill:#e1f5fe,stroke:#01579b,stroke-width:2px
    
    class C1,C2,C3,C4 web
    class E1,E2,E3,E4,E5,E6,E7,E8 endpoints

```
### DIAGRAMA 5: VISÃO GERAL DO FLUXO (Resumo)
```mermaid
graph TB
    title["PLATAFORMA DE VÍDEOS - ARQUITETURA FINAL EM DOCKER"]
    style title fill:#fff,stroke:none,font-family:Arial,font-size:20px,font-weight:bold

    User(("👤 Usuário"))

    subgraph Docker["DOCKER CONTAINERS"]

        App["Spring Boot App<br/>Swagger + FFmpeg<br/>Porta 8080"]

        DB["PostgreSQL<br/>Porta 5432"]

        Queue["RabbitMQ<br/>5672 / 15672"]

        Storage["MinIO<br/>9000 / 9001"]

        User <-->|HTTP REST + Swagger| App
        App <-->|JPA| DB
        App <-->|Mensageria| Queue
        App <-->|S3 API| Storage
        Queue -.->|Evento Assíncrono| App
    end

    classDef app fill:#e3f2fd,stroke:#1565c0,stroke-width:3px
    classDef infra fill:#e8f5e8,stroke:#2e7d32,stroke-width:3px
    classDef externo fill:#fff3e0,stroke:#e65100,stroke-width:3px

    class App app
    class DB,Queue,Storage infra


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

# Playback

-   Início de reprodução
-   Salvamento de progresso
-   Recuperação de estado

------------------------------------------------------------------------

# Stack Tecnológica

-   Java 17+
-   Spring Boot
-   Spring Data JPA
-   RabbitMQ   
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
-   MinIO
-   App( Java + ffmpeg)


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
