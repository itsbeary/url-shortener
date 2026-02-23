# url-shortener

A simple URL shortener REST API built using [Spring Boot](https://spring.io/projects/spring-boot) and PostgreSQL. Accepts a long URL and returns a short code that redirects to the original.

## Prerequisites

- Java 25
- Gradle
- PostgreSQL

## Installation

Clone & build the repository:
```console
git clone https://github.com/itsbeary/url-shortener.git
cd url-shortener
./gradlew build
```

## Setup

Create the PostgreSQL database
```sql
CREATE DATABASE urlshortener;
```

The application.properties reads the database login from environment variables.

```console
export DATABASE_URL=jdbc:postgresql://localhost:5432/urlshortener
export DATABASE_USERNAME=your_username
export DATABASE_PASSWORD=your_password
```

## Usage

**Shorten a URL:**

```console
curl -X POST http://localhost:8080/shorten \
  -H "Content-Type: application/json" \
  -d '{"url": "https://www.example.com"}'
```

Response: `http://localhost:8080/abc123`

**Redirect:** Open the short URL in a browser and it will redirect to the original.
