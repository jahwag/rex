# Rex
Rex facilitates the construction of command-oriented service layers for enterprise applications in Java.

Rex aims to simplify the design of reactive applications in a novel way which does not require message-passing.

## Using REX with Maven
1. Add the following dependency to your pom.xml:
```xml
<dependency>
    <groupId>com.github.jahwag</groupId>
    <artifactId>rex</artifactId>
    <version>0.1.0</version>
</dependency>
```

##### When using Spring

2. To instruct Spring to scan for components inside a library's jar, you must annotate your application class with the package:

```java
@ComponentScan(basePackages = "com.github.jahwag.rex")
```

##### When using Java EE / Weld / other compatible DI container
In JEE7 and later CDI should be enabled by default. Earlier versions are not supported.

## Using REX with other build tools
For Gradle or SBT see reference/plugin documentation on how to include Maven projects.

## Credits
This library owes most of its inspiration to [CRF](http://blog.sizovs.net/service-layer-design/) by @eduardsi.