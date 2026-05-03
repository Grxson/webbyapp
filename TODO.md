# Scraper Platform - Pendientes

## Estado Actual
- Frontend (UI): Funcionando en `localhost:3000`
- Backend (API): No iniciar - faltan implementaciones de servicios
- DB: PostgreSQL corriendo en docker
- Cache: Redis corriendo en docker
- Queue: RabbitMQ corriendo en docker

## Problema
Faltan implementaciones de servicios en el backend:
- ExportService ✅ (creado)
- ProxyService ❌ (falta implementación)
- DataService ❌ (falta implementación)
- JobService ❌ (falta implementación)
- ScraperService ❌ (falta implementación)

## Puertos En Uso
- API: 8081 (cambiado de 8080 por conflicto con Windows)
- UI: 3000
- DB: 5432
- Redis: 6379
- RabbitMQ: 5672 / 15672 (management)

## Para Mañana
1. [ ] Crear implementaciones faltantes de servicios o
2. [ ] Correr backend localmente (sin docker) con `mvn spring-boot:run` en puerto 8081
3. [ ] Testear la app completa

## Notas
- El proyecto parece tener la estructura pero faltan implementaciones de servicios
- Posiblemente hay un branch o commit anterior más completo
- Verificar antes de continuar