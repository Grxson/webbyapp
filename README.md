# Scraper Platform

Plataforma de web scraping multi-fuente desplegada localmente con Docker Compose.

## Stack Tecnológico

- **Backend**: Spring Boot 3 (Java 21) + Arquitectura Hexagonal
- **Frontend**: React 19 + Vite + Tailwind 4
- **Base de datos**: PostgreSQL 16
- **Cola de mensajes**: RabbitMQ 3.13
- **Caché**: Redis 7

## Estructura del Proyecto

```
webbyapp/
├── backend/              # Spring Boot API
├── frontend/             # React SPA
├── scraper-configs/      # Configuraciones YAML
├── docker-compose.yml   # Orquestación
└── .env                 # Variables de entorno
```

## Levantar el Proyecto

```bash
docker-compose up -d
```

## Servicios

- API: http://localhost:8080
- Frontend: http://localhost:3000
- RabbitMQ: http://localhost:15672 (guest/guest)

## Credenciales por defecto

- Usuario: admin
- Password: admin123