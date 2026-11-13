# Plan de pruebas - Veterinaria San Marcos

## Objetivo
Validar el correcto funcionamiento de los modelos, servicios y de la autenticación JWT del backend.

## Herramientas
* Java 21
* Spring Boot
* JUnit 5
* Mockito (repositorios falsos, no se necesita MySQL)
* DataFaker (datos de prueba, ver `DatosFalsos.java`)

## Cómo ejecutarlas
`./mvnw test` (o botón "Run" en IntelliJ sobre la carpeta `src/test`).
`GestionvetApplicationTests` (contextLoads) es la única que necesita MySQL encendido.

| Código | Clase de test | Método | Qué verifica |
|--------|---------------|--------|--------------|
| CP-01 | GestionVetTest | registrarDuenio | Un dueño se crea con datos válidos |
| CP-02 | GestionVetTest | registrarMascota | Una mascota se crea con nombre y especie |
| CP-03 | GestionVetTest | registrarServicio | Un servicio se crea con precio mayor a 0 |
| CP-04 | GestionVetTest | consultaRelacionaDuenioMascotaYServicio | La consulta queda unida a dueño, mascota y servicio |
| CP-05 | DuenioServiceTest | guardarDuenio, listarDuenios, buscarDuenioPorId, eliminarDuenio | CRUD del servicio de dueños |
| CP-06 | ConsultaServiceTest | guardarConsultaConSusRelaciones, listarConsultas, eliminarConsulta | CRUD del servicio de consultas |
| CP-07 | JwtUtilTest | generaTokenYRecuperaElEmail | Un token generado es válido y contiene el email |
| CP-08 | JwtUtilTest | tokenInventadoNoEsValido, tokenExpiradoNoEsValido, tokenFirmadoConOtraClaveNoEsValido | Tokens falsos, vencidos o con otra firma son rechazados |
| CP-09 | AuthServiceTest | loginCorrectoEntregaToken | Login con credenciales correctas entrega un JWT |
| CP-10 | AuthServiceTest | loginConClaveIncorrectaFalla, loginConEmailInexistenteFalla | Login inválido es rechazado (401) |
