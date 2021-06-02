# BACKEND
##  API REST

* DB: MongoDB
* Puerto: 8085

# Usuario Prueba:
    {"username":"usuario","password":"prueba"}

# Nota Prueba:
    {"user":"usuario","asignatura":"arquitectura de datos","ciclo":4,"date":"2021-05-30","nota":19}

# ENDPOINTS:

## USUARIO
1. /login (POST)
    * Permite autenticarse, enviando un objeto del tipo User.
    * Devuelve el token necesario para poder utilizar cualquier otro endpoint, este JWT deberá ser enviado en el header dentro del key token.

<div align="center" >
[![Ejemplo POSTMAN](https://github.com/fixtse/backend-rest-api/raw/main/token.png)]    
</div>


    
2. /newuser (POST)
    * Permite crear un usuario nuevo,recibe un objeto del tipo User.
    * Se valida que el usuario tenga un JWT válido
    
## NOTAS
1.  /newgrade (POST)
    * Permite crear una nota, recibe un objeto del tipo Grade.
    * Se valida que el usuario tenga un JWT válido
2.  /byUser/{user} (GET)
    * Permite listar todas las notas de un usuario.
    * Se valida que el JWT pertenezca al usuario a consultar, de tal manera que cada usuario solo pueda ver sus propias notas.
3.  /allGrades (GET)
    * Permite listar todas las notas registradas en la base de datos
    * Se valida que el usuario tenga un JWT válido

##### La validación de estructura se realiza de acuerdo al objeto aceptado por cada endpoint. 
1. User
    * El nombre de usuario no debe ser vacío y debe ser mayor a 3 dígitos
    * La contraseña no puede estar vacía ni contener el nombre de usuario ni ser menor a 6 dígitos.
2. Grade
    * La asignatura no puede estar vacía
    * El ciclo no puede estar vacío
    * La fecha no puede estar vacía
    * La nota no puede estar vacía ni ser menor a 0
    * El usuario no puede estar vacío

**La llave utilizada para cifrar los JWT se genera aleatoriamente en cada ejecución, por lo que al reiniciar el API, es necesario volver a autenticarse.**
