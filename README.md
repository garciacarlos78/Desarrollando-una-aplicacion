Desarrollado con 2 opciones:

 -Opción 1:
    Iniciamos la Activity ConfirmarDatos dejando que esta MainActivity quede en el stack (onPause).
    Con esta opción, para volver y encontrar los datos el botón de Editar Datos actuará el botón de volver atrás
  
 -Opción 2:
    Cuando iniciamos la Activity ConfirmarDatos destruímos MainActivity.
    Con esta opción liberamos memoria, a cambio de tener que enviar los datos de vuelta al presionar el botón Editar Datos
 
 Proyecto subido con opción 2, para utilizar opción 1:
 
    -Comentar las siguientes líneas:
        MainActivity: 65-71
        ConfirmarDatos: 57-63
        
    -Descomentar las siguientes líneas:
        MainActivity: 55-61
        ConfirmarDatos: 47-54
