# API

## Pasos para crear la versión inicial

1. Acceder al sitio oficial [start.spring.io](https://start.spring.io/)
2. Seleccionar las dependencias Spring Boot DevTools, Spring Session, Jersey, Spring Security, OAuth2 Client, Spring Data JPA, MS SQL Server Driver.
3. Abrir Eclipse IDE con el workspace (carpeta superior al proyecto), seleccionar File, Import, buscar Existing Maven Projects y seleccionar el directorio.
4. Instalar Spring Boot en eclipse, seleccionar el menu help, opcion marketplace, buscar spring, instalar Spring Tools 4 o Spring IDE.

## Guias de uso para integrar la aplicacion
1. [start.spring.io](https://start.spring.io/)
2. [Crear el primer controlador](https://spring.io/guides/gs/spring-boot/)
3. [Registration + Login Tutorial and Example with Spring Boot, Spring Security, Spring Data JPA, Hibernate, HSQL, JSP and Bootstrap]
4. [How to Test a Spring Boot Application](https://stackabuse.com/how-to-test-a-spring-boot-application/)

#Run tests

To run only test, the command example is the following:

"C:\software\apache-maven-3.6.1-bin\apache-maven-3.6.1\bin\mvn" test

to run the application with tests, the command example is the folling

"C:\software\apache-maven-3.6.1-bin\apache-maven-3.6.1\bin\mvn" package && java -jar target/deportes_api-0.0.1-SNAPSHOT.jar