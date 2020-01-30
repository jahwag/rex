# Rex
## Introduction
Rex facilitates the construction of reactive command-oriented service layers for enterprise applications in Java.

## Quick start
Rex is compatible with Java >= 1.8. It also works with GraalVM.

1. Add the following dependency to your Maven project pom.xml*:
```xml
<dependency>
    <groupId>com.github.jahwag.rex</groupId>
    <artifactId>rex</artifactId>
    <version>0.1.0-SNAPSHOT</version>
</dependency>
```
* Rex is not yet available in Maven central i.e. you will have to install it on your own repository before use.

### Using Spring Boot
1. To instruct Spring to scan for components inside rex jars, you may create a configuration as follows:

```java
@ComponentScan("com.github.jahwag.rex")
@Configuration
public class RexConfiguration {
}
```

### Using Java EE / Jakarta EE / MicroProfile / Weld (CDI)
* In CDI >= 1.1, by default all beans are discovered (including Rex).  
* For older versions, consult framework documentation on how to configure CDI bean-discovery-mode.

## How to use
Instructions below are provided with CDI annotations to define and inject enterprise beans. When using Spring, simply substitute @ApplicationScoped with Spring DI-annotations e.g. @Component.

### 1. Defining commands and reactions
#### Command
A `Command` defines a single use case consisting of an input and the corresponding result or output.

Example:
```java
public final class ListClientsByLastNameCommand implements Command.Many<Client> {
    private final String lastName;
    ...
}
```
 
#### Reaction
A `Reaction` encapsulates how the application responds to the associated `Command`. 

Example:
```java
@ApplicationScoped
public final class ListClientsByLastNameReaction implements Reaction<ListClientsByLastNameCommand, Iterable<Client>> {

    @Override
    public Iterable<Client> react(ListClientsByLastNameCommand command) {
        ...
        return clients;
    }
}
```

### 2. Execution
#### Reactive
Rex exposes its reactive API using org.reactivestreams\*. 

<sup>*Pending wider adoption this will be replaced with java.util.concurrent</sup>

```java
public class SampleEndpoint implements LinksService {

    @Inject
    private Rex rex;
    
    public Publisher<Something> doSomething(String description) {
        CreateSomethingCommand command = new CreateSomethingCommand(description);
        
        return rex.observe(command);
    }
}
```
Here `Reactive::observe` returns a single item containing the entire result of CreateSomethingCommand. This may not be preferable if your `Command` returns an instance of `java.lang.Iterable` (i.e. it is an instance of Command.Many). In this case use `Rex::observeIterable`.

If you like most of us prefer something more useful than Publisher, simply wrap the Publisher using your preferred implementation ([Rxjava](https://github.com/ReactiveX/RxJava), [Reactor](https://projectreactor.io) etc) e.g. so:

```java
Mono<String> something = Mono.from(rex.single(command))
                        .map(Something::toString);
```

<sup>The Maven artifact "rex-reactor" provides ready-made wrappers for Project Reactor.</sup>


## Related reading
 * [CommandOrientedInterface](https://martinfowler.com/bliki/CommandOrientedInterface.html)
