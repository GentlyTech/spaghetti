# Spaghetti (eHotels Backend)

> uOttawa CSI 2132 Winter 2024 Course Project  
> Group Members: Aiden Garrett (300293059), Kelly Song (300287703), Andy Yep (300291757)

## About

eHotels is a very rudimentary hotel booking system.

This is the backend (REST API server and JDBC interface) for the eHotels system.
Click [here](https://github.com/GentlyTech/sauce) for the frontend source code.

## Compiling

Ensure you have **JDK 17** and **PostgreSQL** installed.

Before building/starting the backend, make sure you have a DBMS user created for use in PostgreSQL. Then, make a copy of
the `application.properties.template` file, fill out the empty fields as outlined, and rename the file
to `application.properties`. This file is located in the resources folder (`src/main/resources`).

### Running Directly From Source
In the project root (where the `build.gradle` file is), run the following command:

```shell
gradle bootRun
```

### Running From Jar

Alternatively, you can build a jar with

```shell
gradle bootJar
```

which will be output to the `build/libs` folder.

Then run the jar with the java command directly:

```shell
cd build/libs
java -jar spaghetti-0.0.1-SNAPSHOT.jar
```

## Swagger

You can view and interact with the endpoints all in one convenient spot with Swagger. To use Swagger, simply go to the following URL:

```text
http://localhost:8080/swagger-ui/index.html
```

(if you end up changing the hostname or protocol, then use the respective hostname)