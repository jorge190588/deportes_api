# Application Programming Interface (API) o Interfaz de Programación de Aplicaciones

El api desarrollada en este repositorio tiene compo objetivo crear una inferzar para un sistema que gestiona los procesos de eventos deportivos para todo el pais.   Los elementos fundamentales de este proyecto son los siguientes:

1. Codificación: La escritura de codigo esta fundamentada en la programación orientada a objetos, las buenas practicas de código limpio, el concepto de error first, manejo de excepciones, entre otros.
2. Pruebas unitarias: se usa MockMvc y Teste Driven Development-TDD
3. Cada de datos con Hibernate:
4. Documentación de servicios con swagger:
5. Registro de logs:  [Logger](https://stackoverflow.com/questions/21881846/where-does-the-slf4j-log-file-get-saved)
6. Seguridad con Oauth2

## Requerimientos

1. Eclipse IDE for Enterprise Java Developers (Version: 2019-03 (4.11.0))
2. J2SE-1.5 (jdk-12.0.1), dirección física "C:\Program Files\Java\jdk-12.0.1"
3. Versión de java 12


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

## Ejecución de pruebas unitarias 

La ejecución de las pruebs unitarias del proyecto se realiza con la sentencia siguiente:
```sh
"C:\software\apache-maven-3.6.1-bin\apache-maven-3.6.1\bin\mvn" test
```

La ejecución de las pruebas unitarias y la ejecución de la aplicacion se realiza con la sentencia siguientes:
```sh
"C:\software\apache-maven-3.6.1-bin\apache-maven-3.6.1\bin\mvn" package && java -jar target/deportes_api-0.0.1-SNAPSHOT.jar
```

La dirección C:\software\apache-maven-3.6.1-bin\apache-maven-3.6.1\bin es donde se encuentra instalado maven-mvn


## Instalación de JBoss Tools, herramientas para hibernate

### Pasos de instalación

1. Hacer clic en la opción Eclipse Marketplace del menú Help
2. Buscar JBoss Tools 4.11.0.Final
3. Hacer clic en instalar
4. Seleccionar todos los elementos
5. Hacer clic en Confimar
6. Aceptar los terminos de los acuerdos de la licencia
7. Hacer clic en Finish, esperar hasta que instale el software

### Abrir la perspectiva

1. Seleccionar la opcion "Open Perspective" de la opción "Perspective" del Menu "Windows"
2. Seleccionar la opcion "Other.."
3. Seleccionar la opcion "Hibernate" 
4. Hacer clic en "Open"

### Creación de la conexión a la base de datos

La configuración de la cadena de conexión el primer ejemplo se ha obtenido del articulo [Configuring Spring Boot for Microsoft SQL Server](https://springframework.guru/configuring-spring-boot-for-microsoft-sql-server/) los datos de configuración se deben asignar en el archivo application.properties con los datos siguientes:

```sh
spring.datasource.url=jdbc:sqlserver://localhost;databaseName=sportsdata
spring.datasource.username=sa
spring.datasource.password=Web45666
spring.datasource.driverClassName=com.microsoft.sqlserver.jdbc.SQLServerDriver
spring.jpa.show-sql=true
spring.jpa.hibernate.dialect=org.hibernate.dialect.SQLServer2012Dialect
spring.jpa.hibernate.ddl-auto = create
```

## Crear repositorios, entidades y componentes con Hibernate

Para crear las tablas, sincronizarlas y realiar un CRUD usando Hibernate use la guia [Spring Boot Web Application – Part 3 – Spring Data JPA](https://springframework.guru/spring-boot-web-application-part-3-spring-data-jpa/) y para extender la interfaz CrudRepository con el uso de transacciones use la guia [Spring Boot CrudRepository Example](https://www.concretepage.com/spring-boot/spring-boot-crudrepository-example)


## SWAGGER

Los documentos utilizados de guia para la implementación de swagger en el proyecto es [Swagger documentando nuestro API REST](https://www.arquitecturajava.com/swagger-documentando-nuestro-api-rest/) y [Setting Up Swagger 2 with a Spring REST API](https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api)

## oAuth 2.0

El documento de referencia para usar un servidor o un saas es el siguiente: [A Quick Guide to OAuth 2.0 with Spring Security ](https://developer.okta.com/blog/2019/03/12/oauth2-spring-security-guide?utm_campaign=text_website_all_multiple_dev_dev_oauth2-spring-security_null&utm_source=oauthio&utm_medium=cpc#build-your-client-app)


## ORM Methods.
1. [Spring Data JPA - Extending PagingAndSortingRepository](https://www.logicbig.com/tutorials/spring-framework/spring-data/using-paging-and-sorting-repository.html)
2. [Spring Boot CrudRepository Example](https://www.concretepage.com/spring-boot/spring-boot-crudrepository-example)
3. [Spring JPA dynamic query example](https://javadeveloperzone.com/spring/spring-jpa-dynamic-query-example/)
4. [Simplification of Data Access Layer Implementation using JPA Specification](https://medium.com/monstar-lab-bangladesh-engineering/simplification-of-data-access-layer-implementation-using-jpa-specification-43dda2da485)