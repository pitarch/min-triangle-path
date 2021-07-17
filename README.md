# Minimum Triangle Path App

This project contains the _Minimum Triangle Path Application_. It has 
been implemented in Scala and consist of a command-line application that 
reads an encoded triangle from the stdin and writes the minimum path into 
the stdout as a result.

## Tooling

This is a list of the required tools to build and run this application:

 - [JDK 1.8](https://adoptopenjdk.net/?variant=openjdk8&jvmVariant=hotspot)
 - [sbt v1.5](https://www.scala-sbt.org/)
 - [scala 2.13](https://scala-lang.org/download/2.13.0.html)

# Build an executable jar

```sh 
sbt clean assembly
```

This generates the following jar file:

```
./target/scala-2.13/MinTrianglePathApp.jar
```


# Run the executable jar

```shell
scala ./target/scala-2.13/MinTrianglePathApp.jar
```



Example:

```
scala ./target/scala-2.13/MinTrianglePathApp.jar
7
6 3
^D
Minimal path is: 7 + 3 = 10
```

`^D` means that the use has press `CTRL-D`.