## FAQ

##### What does Rex stand for?
Choose your pick! 

Rex is latin title for king, which seemed a fitting name for an object that gives commands. 

It could also be thought of as a "Reactive EXecutor" or "Reactive Enterprise eXtensions", if you prefer.

## Getting started
Instructions below are provided with CDI annotations to define and inject enterprise beans. 

When using Spring, substitute @ApplicationScoped with @Named or @Component.

NOTE: Reactions are cached and should have application scope.

### 1. Defining commands and Reactions
##### Command
A `Command` defines a single use case consisting of an input and the corresponding result or output.

Example:
```java
public final class ListClientsByLastNameCommand implements Command.Many<Client> {
    private final String lastName;
    ...
}
```
 
##### Reaction
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
##### Reactive
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
 * [How to design a service layer in Java using CRF](http://blog.sizovs.net/service-layer-design/)