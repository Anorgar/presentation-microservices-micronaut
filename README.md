# presentation-microservices-micronaut
Pet store in micronaut micro-services


##Introduction

This is a micro-services project in micronaut for demonstration purpose.
This project contain:

<ul>
    <li>a remote configuration: config</li>
    <li>a data base connected micro-service: pet-core</li>
    <li>a user interface micro-service: pet-store-api</li>
    <li>a code shared module: common</li>
</ul>

##Start project

This project require java 8+ to run

Every project are java / gradle project, so they can be run with:
<ul>
    <li>gradle run</li>
    <li>java -jar</li>
    <li>an IDE</li>
</ul>

Config project must be started first because it contains the running configuration of the other projects
Because it's a spring project (spring-cloud-config-server), the gradle command to run it is:
```
./gradlew bootRun
```

The two other projects can be run independently. They are micronaut projects, so they can be run with gradle command:
```
./gradlew run
```

Before running these projects with java, a shadow jar must be build with
```
./gradlew shadowJar
```

Then they can be run with 
```
java -Dmicronaut.environments=dev -jar pet-store-api.jar
```