# Command

Command is a request for changes in the domain. Unlike an [event](event.md), it is not a statement of fact as it might be rejected. For example, `CreateUser` command may or may not result in an `UserCreated` event being produced.

Defining a command is pretty straightforward, through subclassing `StandardCommand<T, S>`:

```java
public class CreateUser extends StandardCommand<User, Void> {
  @Getter @Setter
  private String email;
}
```

The type parameter signifies an optional "result" type that can be returned
once the command is successfully executed, by overriding the `onCompletion()`
method:

```java
@Override
public User onCompletion() {
  return User.lookup(email);
}
```

A more important part of any command is being able to generate events. This is done by overriding the `events()` method that returns a stream of events:

```java
@Override
public EventStream<Void> events(Repository repository) {
  return EventStream.of(new UserCreated().setEmail(email));
}
```
