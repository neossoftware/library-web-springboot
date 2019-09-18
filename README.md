# Library-Web-SpringBoot 2.x

----------------------------------
Pasos para la ejecucion del proyecto
----------------------------------

1.- clonar o descargar el proyecto

2.- ejecutar el siguiente comando usando Maven 3.x
```ruby
$ mvn clean package
```
```ruby
$ java -jar target\demo-0.0.1-SNAPSHOT.jar
```
3.- Abrir un browser y ejecutar la siguiente URL
http://localhost:8080/customer

![Image of version](https://github.com/neossoftware/library-web-springboot/blob/spring-jdbc-template/src/main/resources/images/customer.JPG)

4.- Para la ejecucion de la consola de H2 ejecutar
http://localhost:8080/h2-console

5.- JDBC-URL
```ruby
jdbc:h2:mem:testdb
```
![Image of version](https://github.com/neossoftware/library-web-springboot/blob/spring-jdbc-template/src/main/resources/images/consola_h2.JPG)
