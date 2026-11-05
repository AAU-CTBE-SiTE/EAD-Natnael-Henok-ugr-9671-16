# Spring Core Lab: IoC and Bean Management

Day 1 lab covering the Spring IoC container using XML configuration.

## What is covered
1. Tight coupling with `new` (the problem)
2. Letting the IoC container create a bean (`ClassPathXmlApplicationContext`, `getBean`)
3. Setter injection
4. Constructor injection (by name and by index)
5. Object dependencies with `ref`
6. Collection injection (`list`, `set`, `array`, `map`, `props`)
7. Bean scopes: singleton vs prototype

## Project layout
```
src/main/java/com/craft/       Address, Subject, Student, CollectionBean, App
src/main/resources/            beans-*.xml (one config per lab section)
```

## Requirements
- JDK 21
- Maven (or IntelliJ IDEA, which bundles it)

## Run
Open the project in IntelliJ, wait for Maven to load, then run `App.main()`.

Or from a terminal:
```
mvn compile exec:java -Dexec.mainClass=com.craft.App
```
