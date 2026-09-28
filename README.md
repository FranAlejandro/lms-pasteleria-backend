LMS Pastelería — Backend

Plataforma privada de e-learning para cursos de pastelería. Backend en Java/Spring Boot, pensado para consumirse desde un frontend en React (repositorio separado).

Proyecto desarrollado de forma incremental en 4 fases. Este repo cubre actualmente la Fase 1 (MVP).

Stack técnico
Backend: Java 21 · Spring Boot 3.x · Spring Security (JWT) · Spring Data JPA
Base de datos: PostgreSQL 16 (contenedor Docker en desarrollo)
Frontend (repo separado): React + Vite
Infraestructura Fase 1: Docker Compose (Postgres + Adminer)
Futuro (fases posteriores): Mercado Pago (pagos, Fase 2) · Bunny.net CDN (video, Fase 3)
Arquitectura

El backend sigue una arquitectura en capas clásica de Spring Boot:

Frontend (React)
│  HTTP/JSON + JWT
▼
Controller   → recibe la petición, valida el token
│
Service      → lógica de negocio
│
Repository   → acceso a datos (Spring Data JPA)
│
PostgreSQL

Los archivos (video/PDF) se sirven aparte de la base de datos relacional; en Fase 1 desde almacenamiento simple (local o S3 básico), migrando a Bunny.net en Fase 3 sin cambiar el modelo de datos.

Cómo levantar el entorno local
Requisitos: Java 21, Maven, Docker Desktop.
Levantar Postgres + Adminer:
bash
docker compose up -d
Adminer (GUI de base de datos): http://localhost:8081
Configurar src/main/resources/application.yml (ver application.yml de ejemplo en el repo — usa las mismas credenciales que docker-compose.yml).
Correr la aplicación:
bash
./mvnw spring-boot:run

Hibernate creará automáticamente las tablas a partir de las entidades (ddl-auto: update, solo para desarrollo).

Estructura del proyecto
src/main/java/com/pastelitosclau/lmspasteleria/
├── config/       # configuración (seguridad, JWT)
├── controller/   # capa HTTP
├── service/      # lógica de negocio
├── repository/   # acceso a datos (JPA)
├── entity/       # modelo de datos (tablas)
├── dto/          # objetos expuestos al frontend
├── security/     # filtros y utilidades JWT
└── exception/    # manejo centralizado de errores
Modelo de datos (Fase 1)

Course → Module → Lesson (con video, material descargable y quiz opcional). Enrollment conecta User con Course y es la base de la autorización de acceso. LessonProgress registra avance por alumno/lección. Quiz → Question → Option, con QuizAttempt guardando resultados.

Roadmap
Fase	Contenido	Estado
Fase 1	MVP: auth, catálogo, cursos, inscripción manual, progreso, quiz	🚧 En desarrollo
Fase 2	Pagos (Mercado Pago) + webhooks + inscripción automática	⏳ Pendiente
Fase 3	CDN de video (Bunny.net), precios CLP/USD	⏳ Pendiente
Fase 4	(Opcional) Next.js, SEO, dashboard de analíticas	⏳ Pendiente
Estado actual (Fase 1)
Modelo de datos — entidades JPA
Entorno local con Docker (Postgres + Adminer)
Autenticación (registro/login con JWT)
CRUD de cursos/módulos/lecciones (admin)
Catálogo público de cursos
Inscripción manual (admin)
Consumo de contenido + progreso (alumno)
Quiz de lección