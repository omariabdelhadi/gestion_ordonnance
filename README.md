# Gestion d'Ordonnances

Application web de gestion d'ordonnances en **architecture microservices**, avec un **agent IA** qui interroge chaque microservice via le protocole **MCP** (Model Context Protocol) pour répondre aux questions des utilisateurs.

Tous les microservices, y compris le frontend Angular, se trouvent dans ce dépôt.

## Objectif

- créer et gérer des ordonnances pour des patients ;
- gérer les patients et les médicaments dans des services indépendants ;
- poser des questions en langage naturel à un agent IA, qui récupère les informations en appelant les outils (tools) exposés par chaque microservice.

## Architecture

| Service | Rôle | Port |
|---|---|---|
| `frontend-angulare-service` | Interface web Angular | 4200 |
| `gateway-server` | Passerelle d'entrée (gateway) | 8886 |
| `discovery-service` | Registre de services Eureka | 8761 |
| `config-service` | Configuration centralisée | 9090 |
| `patient-service` | Gestion des patients (serveur MCP) | 8090 |
| `medicament-service` | Gestion des médicaments (serveur MCP), 2 instances | 8093 – 8094 |
| `ordonnance-service` | Gestion des ordonnances (serveur MCP) | 8092 |
| `ordonnance-bot` | Agent IA (Spring AI) | 8899 |

Les microservices s'enregistrent dans Eureka et récupèrent leur configuration auprès du `config-service`.

### Agent IA et MCP

Chaque microservice métier expose un serveur MCP (endpoint `/mcp`). L'agent IA s'y connecte pour découvrir et appeler leurs outils :

```
ordonnance-bot (agent IA)
   ├── MCP ──► patient-service
   ├── MCP ──► ordonnance-service
   └── MCP ──► medicament-service
```

L'agent utilise Spring AI avec OpenRouter (API compatible OpenAI) et le modèle `openrouter/free`.

## Technologies

| Domaine | Technologies |
|---|---|
| Backend | Spring Boot, microservices |
| Infrastructure | Spring Cloud (Eureka, Config Server, Gateway) |
| Agent IA | Spring AI, MCP, OpenRouter |
| Frontend | Angular |
| Base de données | H2 (en mémoire) |
| Conteneurisation | Docker, Docker Compose |

## Aperçu

### Création d'une ordonnance pour un patient

<img width="1919" height="954" alt="image" src="https://github.com/user-attachments/assets/1bd5e13c-caec-47cd-993d-c682e5943f72" />

## Lancer le projet

### Prérequis

- Docker et Docker Compose (Docker Desktop sur Windows ou macOS)

### Démarrage

```bash
git clone https://github.com/omariabdelhadi/gestion_ordonnance.git
cd gestion_ordonnance
docker compose up
```

Les services démarrent dans l'ordre (Eureka, configuration, passerelle, puis les microservices métier et l'agent IA). Attends une à deux minutes que tous soient prêts.

| Accès | Adresse |
|---|---|
| Application | http://localhost:4200 |
| Registre Eureka | http://localhost:8761 |
| Passerelle | http://localhost:8886 |

### Arrêt

```bash
docker compose down
```

Les données sont stockées dans H2, en mémoire : elles sont effacées à chaque arrêt.
