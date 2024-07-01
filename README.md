# Java-Selenium-Cucumber-Test-Framework

## Importante para evitar errores se debe instalar jdk 11 https://www.oracle.com/cl/java/technologies/javase/jdk11-archive-downloads.html y editar las variables de entorno JAVA_HOME con el directorio del jdk11

## Se debe incluir carpeta bin en resources dentro debe contener las carpetas apk/chromewin64/windows32/zap junto con los respectivos drivers



Framework para pruebas con Selenium/Cucumber Codigo Java y Maven (Udemy 2024)

### Para correr pruebas Web usando la suite de TestNG


**`mvn clean test -DsuiteFile='EmergenciasWebRunner.xml'`**

**`mvn clean test -DsuiteFile='OrangeWebRunner.xml'`**


### Para correr pruebas Mobile  usando la suite de TestNG

**`mvn clean test -DsuiteFile='MobileTestYapeRunner.xml'`**


** Aclaración: ** deben tener instalado un dispositivo movil (virtual o fisico), esto puede hacerse usando Android Virtual Devices de android Studio (https://developer.android.com/studio) y también debe instalarse Appium desktop o Appium server (https://appium.io/docs/en/2.0/quickstart/install/)


### Para correr pruebas Api

**`mvn clean test -DsuiteFile='ApiTestPetStoreRunnerNoParams.xml'`**

### [Tools que necesito para instalar y configurar este framework, haz click](https://medium.com/@waynemervin/como-configurar-tu-entorno-para-empezar-a-programar-en-java-a64337863d98)



