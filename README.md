# Spaghetti (eHotels Backend)

> uOttawa CSI 2132 Winter 2024 Course Project  
> Group Members: Aiden Garrett (300293059), Kelly Song (300287703), Andy Yep (300291757)

## About

eHotels is a very rudimentary hotel booking system.

This is the backend (REST API server and JDBC interface) for the eHotels system.
Click [here](https://github.com/GentlyTech/sauce) for the frontend source code.

## Configuration

Before building/starting the backend, make sure you have a DBMS user created for use in PostgreSQL.

Then, make a copy of
the `application.properties.template` file, fill out the empty fields as outlined, and rename the file
to `application.properties`. This file is located in the resources folder (`src/main/resources`).

## Compiling

Ensure you have **JDK 17** and **PostgreSQL** installed. You'll also need **gradle**, though you can opt to use the copy that's bundled with the source code (gradlew).

### IntelliJ IDEA

The easiest way to run the backend is probably through the IDE.

If you're reading this README file in IntelliJ IDEA, you can simply start the application by clicking the play button below:

`Spaghetti`

If the button above didn't work, simply open the **Spaghetti** class and click on the "Run" button that appears beside the following line:

```java
    public static void main(String[] args) {...}
```

(located at `src/main/java/ca/uottawa/csi2132/group196/spaghetti/Spaghetti.java`)

### Command Line

Alternatively, if you don't have IntelliJ IDEA installed or don't feel like running it through that, you can do it through the command line.

#### Running Directly From Source
In the project root (where the `build.gradle` file is), run the following command:

```shell
gradle bootRun
```

#### Running From Jar

You can also build a jar with

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

(if you end up changing the hostname, then use the respective hostname)
