# Quick Start - Scraper Platform

**Estado:** Completamente funcional y documentado

---

## Inicio Rapido (5 minutos)

### 1. Iniciar los servicios
```bash
docker-compose up -d
```

### 2. Acceder a la aplicacion
```
Frontend:     http://localhost:3000
Backend API:  http://localhost:8081/api
pgAdmin:      http://localhost:5050
```

### 3. Verificar salud del API
```bash
curl http://localhost:8081/api/health
```

---

## Documentacion Completa

La documentacion completa esta en la carpeta **`Documentacion/`**:

- **`Documentacion/INDEX.md`** -- Comienza aqui
- **`Documentacion/RESUMEN_FINAL.md`** -- Resumen ejecutivo
- **`Documentacion/Fase1-Frontend/README.md`** -- Detalles Fase 1
- **`Documentacion/Fase2-Backend/README.md`** -- Detalles Fase 2
- **`Documentacion/Fase3-Database/README.md`** -- Detalles Fase 3

---

## Credenciales

### pgAdmin (Gestion de Base de Datos)
```
URL:       http://localhost:5050
Email:     admin@scraper.dev
Contrasena: admin123
```

### Base de Datos PostgreSQL
```
Host:       localhost
Puerto:     5433
Usuario:    scraper_admin
Contrasena: E6gbWLh9qBUSuatTzMcf
Base Datos: scraper
```

### Como Conectar en pgAdmin
1. Abre http://localhost:5050
2. Login con: admin@scraper.dev / admin123
3. Right-click "Servers" -> "Create" -> "Server"
4. Rellena:
   - Host: `scraper-db`
   - Port: `5432`
   - Username: `scraper_admin`
   - Password: `E6gbWLh9qBUSuatTzMcf`
5. Click "Save"

---

## Tests Rapidos

### Verificar Frontend
```bash
curl http://localhost:3000
```

### Verificar Backend
```bash
curl http://localhost:8081/api/health
```

### Verificar Base de Datos
```bash
docker exec webbyapp-scraper-db-1 psql -U scraper_admin -d scraper -c "SELECT 1;"
```

---

## Stack Completo

| Servicio | URL/Puerto | Status |
|----------|-----------|--------|
| Frontend | http://localhost:3000 | Activo |
| Backend | http://localhost:8081/api | Activo |
| pgAdmin | http://localhost:5050 | Activo |
| PostgreSQL | localhost:5433 | Activo |
| RabbitMQ | localhost:5672 | Activo |
| Redis | localhost:6379 | Activo |

---

## Detener servicios
```bash
docker-compose down
```

---

## Reiniciar todo (hard reset)
```bash
docker-compose down -v
docker-compose up -d
```

---

## Para Mas Informacion

Consulta **`Documentacion/INDEX.md`** para:
- Detalles tecnicos de cada fase
- Cambios realizados
- Metricas y validaciones
- Arquitectura del sistema

---

**Ultima actualizacion:** 03/05/2026  
**Status:** Listo para usar  
**Documentado por:** Grxson
