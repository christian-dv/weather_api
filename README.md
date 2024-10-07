# Weather API

API desarrollada con spring boot utilizando la fuente externa OpenWeather.

## Tecnologias utilizadas

- Java 17
- Maven 4.0.0
- MySQL 8.0
- IDE (IntelliJ)
- Redis
- Docker y Docker compose
- Spring boot + security + cache
- Limitacion de tasa de solicitudes (100 requests por hora)

## Configuración del Proyecto

1. **Clona el repositorio:**

   ```bash
   git clone https://github.com/christian-dv/weather_api.git
   cd tu-ubicacion/proyecto
   
2. **Abrir el proyecto con algun IDE y ejecutar el comando mvn clean install para generar el .jar
     de la app)**

3. **Luego ejecutar el siguiente comando en la ubicacion del proyecto:**
    ```bash
   docker-compose up -d

4. **Con ese comando se descargarán las imagenes y posteriormente iniciará el contenedor
     (APP puerto 8080, MYSQL puerto 3606 y REDIS puerto 6379)**
5. **Antes de probar los endpoints debemos insertar ROL_ADMIN y ROL_USER en la tabla Rol**
6. **Por ultimo, podemos ir a Postman y primero iniciar sesion o crear usuarios:**
    ```bash
   (POST) http://localhost:8080/auth/login (iniciar sesion)
   Requiere BODY : 
   { "nombreUsuario":"usuario",
    "password":"clave" }
   http://localhost:8080/auth/newuser (crear usuario)
   Requiere BODY: 
   { "email":"ejemplo@ejemplo.com",
    "nombre":"ejemplo",
    "nombreUsuario":"ejemplo",
    "password":"ejemplo"}
7. **Generará un token que debemos usar para ejecutar los endpoints (en Postman ir a Authenticated y
     seleccionar Bearer token y pegarlo) los endpoints son:**
    ```bash
   (GET)(Clima actual por ciudad) 
   http://localhost:8080/clima/current?ciudad=Madrid,ES&apiKey=0e798aa363c21ca421facd736f6a1125
   donde ciudad y apiKey son parametros necesarios (apikey de OpenWeather API)
   
   (GET)(Pronostico de cinco dias por ciudad)
   http://localhost:8080/clima/forecast?ciudad=Madrid,ES&apiKey=0e798aa363c21ca421facd736f6a1125

   (GET)(Contaminacion del aire por ciudad)
   http://localhost:8080/clima/airpollution?ciudad=Madrid,ES&apiKey=0e798aa363c21ca421facd736f6a1125

## Desarrollado por Christian Barrera