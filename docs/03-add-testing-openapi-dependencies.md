# Setup Testing and OpenAPI (Swagger) dependencies

## Unit testing: JUnit and Mockito

Both JUnit and Mockito are required dependencies for creating unit tests. 
Add the following dependencies to the `pom.xml` file:

```xml  
<!-- Junit 5 -->
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-engine</artifactId>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.junit.platform</groupId>
    <artifactId>junit-platform-launcher</artifactId>
    <scope>test</scope>
</dependency>
<!-- Mockito extention -->
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-junit-jupiter</artifactId>
    <scope>test</scope>
</dependency>
```

## Code coverage: JaCoCo

Add the JaCoCo plugin inside `<plugins></plugins>` section from `pom.xml` file.

```xml
        <!-- JaCoCo plugin -->
        <plugin>
            <groupId>org.jacoco</groupId>
            <artifactId>jacoco-maven-plugin</artifactId>
            <version>0.8.12</version>
            <executions>
                <execution>
                    <id>prepare-agent</id>
                    <goals>
                        <goal>prepare-agent</goal>
                    </goals>
                </execution>
                <execution>
                    <id>report</id>
                    <phase>verify</phase>
                    <goals>
                        <goal>report</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
```

**Usage**
1. Run tests and generate report with the command:
```
mvn clean verify
```
2. Open the coverage report generated in `target/site/jacoco/index.html`.

**Versioning**:
- [JaCoCo Maven Plugin - mvnrepository](https://mvnrepository.com/artifact/org.jacoco/jacoco-maven-plugin)

## OpenAPI and Swagger UI Documentation

Add the following dependency to the `pom.xml` file:

```xml
<!-- SpringDoc OpenAPI -->
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.7.0</version>
</dependency> 
```

**Usage**: 

Start the application and view the API documentation at:
- http://localhost:8080/swagger-ui/index.html

**Versioning**:
- [SpringDoc OpenAPI Starter WebMVC UI - mvnrepository](https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-starter-webmvc-ui)
