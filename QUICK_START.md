# 🚀 Quick Start - Scraper Platform

**Estado:** ✅ Completamente funcional y documentado

---

## ⚡ Inicio Rápido (5 minutos)

### 1️⃣ Iniciar los servicios
```bash
docker-compose up -d
```

### 2️⃣ Acceder a la aplicación
```
Frontend:     http://localhost:3000
Backend API:  http://localhost:8081/api
pgAdmin:      http://localhost:5050
```

### 3️⃣ Verificar salud del API
```bash
curl http://localhost:8081/api/health
```

---

## 📚 Documentación Completa

La documentación completa está en la carpeta **`Documentación/`**:

- **`Documentación/INDEX.md`** ← 👈 Comienza aquí
- **`Documentación/RESUMEN_FINAL.md`** ← Resumen ejecutivo
- **`Documentación/Fase1-Frontend/README.md`** ← Detalles Fase 1
- **`Documentación/Fase2-Backend/README.md`** ← Detalles Fase 2
- **`Documentación/Fase3-Database/README.md`** ← Detalles Fase 3

---

## 🔐 Credenciales

### pgAdmin (Gestión de Base de Datos)
```
URL:       http://localhost:5050
Email:     admin@scraper.dev
Contraseña: admin123
```

### Base de Datos PostgreSQL
```
Host:       localhost
Puerto:     5433
Usuario:    scraper_admin
Contraseña: E6gbWLh9qBUSuatTzMcf
Base Datos: scraper
```

### Cómo Conectar en pgAdmin
1. Abre http://localhost:5050
2. Login con: admin@scraper.dev / admin123
3. Right-click "Servers" → "Create" → "Server"
4. Rellena:
   - Host: `scraper-db`
   - Port: `5432`
   - Username: `scraper_admin`
   - Password: `E6gbWLh9qBUSuatTzMcf`
5. Click "Save"

---

## 🧪 Tests Rápidos

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

## 📊 Stack Completo

| Servicio | URL/Puerto | Status |
|----------|-----------|--------|
| Frontend | http://localhost:3000 | ✅ |
| Backend | http://localhost:8081/api | ✅ |
| pgAdmin | http://localhost:5050 | ✅ |
| PostgreSQL | localhost:5433 | ✅ |
| RabbitMQ | localhost:5672 | ✅ |
| Redis | localhost:6379 | ✅ |

---

## 🛑 Detener servicios
```bash
docker-compose down
```

---

## 🔄 Reiniciar todo (hard reset)
```bash
docker-compose down -v
docker-compose up -d
```

---

## 📖 Para Más Información

Consulta **`Documentación/INDEX.md`** para:
- Detalles técnicos de cada fase
- Cambios realizados
- Métricas y validaciones
- Arquitectura del sistema

---

**Última actualización:** 03/05/2026  
**Status:** ✅ Listo para usar
