# [UDEA-PS] Programa 3 [![Build Status](https://travis-ci.org/yoinergomez/Pruebas_PS2.svg?branch=develop)](https://travis-ci.org/yoinergomez/Pruebas_PS2) [![Coverage Status](https://coveralls.io/repos/github/yoinergomez/Pruebas_PS2/badge.svg?branch=develop)](https://coveralls.io/github/yoinergomez/Pruebas_PS2?branch=develop) [![trello](https://img.shields.io/badge/trello-Pruebas__PS2-blue.svg)](https://trello.com/b/UfBGgtWV/pruebasps2)

Aplicación que calcula los parámetros de una regresión lineal, los coeficientes de correlación y r cuadrado para un conjunto de n pares de datos.

## Desarrolladores
Jhonatan Alexánder Orozco Blandón.  
Frank Alexis Castrillón Giraldo.  
Yoiner Esteban Gómez Ayala.

## Documentación
Todos los informes generados para este programa se encuentran en la carpeta doc de este repositorio. Ahí se puede encontrar los siguientes archivos: 
- [Diseño de software](https://github.com/yoinergomez/Pruebas_PS2/raw/master/doc/Plantilla%20de%20dise%C3%B1o.doc.docx)
- [PIP (Retrospectiva)](https://github.com/yoinergomez/Pruebas_PS2/raw/master/doc/Retrospectiva.docx)
- [Ejemplo de archivo de entrada del programa](https://github.com/yoinergomez/Pruebas_PS2/raw/master/src/main/resources/datosRegresion.xls)


### Reporte de estimaciones de tareas
El desarrollo del proyecto requirió una inversión de tiempo de 23.5 horas con relación a la estimación que se realizó en la fase de planificación.

#### Wakatime
Se utilizó la herramienta de wakatime la cual nos permitió el conteo automático del tiempo invertido por cada archivo y el total trabajado por persona:

**Frank**
![frank-wakatime](https://image.ibb.co/dcw5UF/f.png)

**Yoiner**
![yoiner-wakatime](https://image.ibb.co/mQZg3a/y.png)

**Jhonatan**
![jhonatan-wakatime](https://image.ibb.co/b3ZEOa/j.png)

## Metodología [![trello](https://img.shields.io/badge/trello-Pruebas__PS2-blue.svg)](https://trello.com/b/UfBGgtWV/pruebasps2)

**- Análisis:** Los miembros del equipo comprendieron el documento donde se describía el problema y sus requerimientos. Posteriormente, se establece un estándar de codificación que nos permite abordar de una manera correcta el desarrollo de software.

**- Planeación:** Se asignaron y estimaron las tareas por medio de la técnica de _planning poker_, además para lograr una mayor claridad se describieron las tareas que presentaban ambigüedad o no tenían un alcance bien definido.

**- Implementación:** El equipo implemento la metodología de desarrollo TDD y con relación a esto se resolvieron las tareas estipuladas en las etapas anteriores.

**- Verificación:** Se comprobó la ejecución del programa y el nivel de cobertura del código fuera mayor o igual al 95%.


## Tecnologías usadas
- Java v1.8
- NetBeans v8.2
- Maven v3.0.5


## Ejecución del proyecto
Teniendo el proyecto cargado en NetBeans se procede a instalar las dependencias con MAVEN debido a que el proyecto las necesita para un correcto funcionamiento.

- Digite la ruta donde se encuentra ubicado el archivo excel que contiene los datos para calcular la regresión y predecir los nuevos valores.   
Es importante que el archivo posea un formato indicado para su correcta lectura:

  - La primer columna debe tener todos los valores X.
  - La segunda columna debe tener todos los valores Y.
  - La tercer columna debe tener todos los valores de los X de prueba.

El anterior formato lo cumple [este archivo de prueba](https://github.com/yoinergomez/Pruebas_PS2/raw/master/src/main/resources/datosRegresion.xls).
