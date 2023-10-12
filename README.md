# Api-Rest-Testing

| API REST |  SpringBoot | Spring JPA | Hibernate | PostgreSQL | Swagger | JUnit | Mockito | TDD |

![API REST TESTING](https://github.com/Julian1699/Api-Rest-Testing/assets/114323630/8f7411fd-bbe2-48ef-aec3-580d1d770e0d)

# Configuración de la Base de Datos:

Antes de ejecutar la API sin problemas, es importante configurar la base de datos. En este proyecto, hemos utilizado H2 como gestor de base de datos, y el usuario es 'root' con la contraseña también establecida como 'root'. Sin embargo, el proyecto está diseñado para admitir conexiones de otros gestores de bases de datos como MySQL, Oracle SQL y PostgreSQL ya que las dependencias necesarias están definidas en el archivo pom.xml.

![image](https://github.com/Julian1699/Api-Rest-Testing/assets/114323630/1eadafc3-1fae-4c35-9877-8879b334b617)

# Capas testeadas:

![image](https://github.com/Julian1699/Api-Rest-Testing/assets/114323630/f8972de9-588c-4a46-bef1-81d2f7830d8a)

# Empleo de Junit y Mockito:

![image](https://github.com/Julian1699/Api-Rest-Testing/assets/114323630/988abbb0-6e03-49db-a871-803beefe834c)

# Ejecución de TDD en la capa del controlador (TEST DRIVEN DEVELOPMENT)

![image](https://github.com/Julian1699/Api-Rest-Testing/assets/114323630/e2f12ac5-cab1-4cda-a084-fd3c1d1b72bb)

# API de Productos
  
Esta API RESTful proporciona una manera de gestionar datos de productos utilizando Spring Boot. Está diseñada para ser utilizada por desarrolladores que necesitan crear, leer, actualizar y eliminar productos.

# La API proporciona las siguientes características:

- Obtener todos los productos: Recupera una lista de todos los productos disponibles en la base de datos.
- Agregar un nuevo producto: Agrega un nuevo producto a la base de datos.
- Actualizar un producto existente: Actualiza un producto existente en la base de datos.
- Eliminar un producto existente: Elimina un producto existente de la base de datos.
- Obtener un producto por ID: Recupera un producto por su identificador único.
- Buscar productos: Busca productos por nombre, referencia o categoría.
- Tecnologías utilizadas

# La API está construida utilizando las siguientes tecnologías:

- Java 17: La versión de Java.
- Spring Boot 3.0.10: Un potente marco de trabajo para construir aplicaciones web basadas en Java.
- Spring Data JPA: Simplifica el acceso y la gestión de bases de datos.
- Swagger: Proporciona documentación interactiva de la API.
- Hibernate Validator: Para la validación de los datos enviados en las solicitudes.
- Lombok: Reduce el código repetitivo.
- Cross-Origin Resource Sharing (CORS): Permite solicitudes entre dominios desde aplicaciones web.

# Documentación

La documentación de la API está disponible en Swagger: http://localhost:8080/swagger-ui/index.html#/

![image](https://github.com/Julian1699/Api-Rest-Intermedian/assets/114323630/2cfe3ae7-b943-49fa-8749-b208f9501bf5)

# Endpoints

Los endpoints de la API son los siguientes:

- PUT /api/v1/product/put/{id}: Actualiza un producto existente.
- POST /api/v1/product/post: Agrega un nuevo producto.
- GET /api/v1/product/search/{search}: Busca productos por nombre, referencia o categoría.
- GET /api/v1/product/id/{id}: Obtiene un producto por ID.
- GET /api/v1/product/all: Obtiene todos los productos.
- DELETE /api/v1/product/delete/{id}: Elimina un producto existente.
