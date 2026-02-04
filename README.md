# taskauth-api

API REST de tarefas com autenticação JWT e Spring Security.

## Stack
- Java 17
- Spring Boot
- Spring Security + JWT
- Spring Data JPA
- H2 (dev)
- Maven

## Como rodar (dev)
> PowerShell

```powershell
$env:JWT_SECRET="coloque-uma-chave-com-mais-de-32-caracteres"
.\mvnw -q clean
.\mvnw spring-boot:run
Swagger
http://localhost:8080/swagger-ui/index.html

H2 Console
http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:taskauth
User: sa
Password: (vazio)

