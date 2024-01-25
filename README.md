# Informacion del servicio

Proyecto que expone una API RESTFull para la creación de usuarios

## Prerequisitos

Se debe tener instalado las siguientes herramientas

* *H2* Versión mas reciente
* *Maven* versión 3.8.x o superior
* *Java* version 17
* *Lombok*

### Instalación

Para instalar en el repositorio local se realiza el siguiente comando en Maven

```
mvn install
```

Ejecutar la clase Main (*OpenApiGeneratorApplication*)

## Consideraciones

* La clase Main se encontrará en el siguiente directorio
*target/generated-source/src/gen/java/main...*

* Ruta de Scripth BD = *resource/schema.sql*

* Para detalles del contrato (swagger), dirigirse al archivo *resources/openapi.yaml*  
o pegarle al endpoint **localhost:9090/openapi**


