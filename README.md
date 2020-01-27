# Rex
Rex facilitates the construction of reactive command-oriented service layers for enterprise applications in Java.

## Compatibility
Rex is compatible with Java >= 1.8. It also works with GraalVM.

## Using Rex with Maven
1. Add the following dependency to your pom.xml*:
```xml
<dependency>
    <groupId>com.github.jahwag.rex</groupId>
    <artifactId>rex</artifactId>
    <version>0.1.0-SNAPSHOT</version>
</dependency>
```

* Rex is not yet available in Maven central i.e. you will have to install it on your own repository before use.


##### When using Spring

2. To instruct Spring to scan for components inside rex jars, you may create a configuration as follows:

```java
@ComponentScan("com.github.jahwag.rex")
@Configuration
public class RexConfiguration {
}
```

##### When using Java EE / Weld / other compatible DI container
In JEE7 and later CDI should be enabled by default. Earlier versions are not supported.

## Using Rex with other build tools
For Gradle or SBT see reference/plugin documentation on how to include Maven projects.

## Further reading
[Rex wiki](./wiki.md)
