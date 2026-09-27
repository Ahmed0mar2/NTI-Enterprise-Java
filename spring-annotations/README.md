# Spring Annotation Configuration

This example uses component scanning from `AppConfig` to assemble an employee
management application. Spring's default bean scope is singleton, so the
following beans have one shared instance per application context:

- `EmployeeServiceImpl` (`@Service`)
- `NotificationManager` and the three notifier components
  (`@Component`)
- The active `EmployeeRepository` implementation (`@Repository`)
- `EmployeeValidator` (the `@Bean` method in `AppConfig`)

These classes are stateless coordinators or shared application services, so a
single instance avoids unnecessary object creation and gives all callers the
same configured collaborators. The repository is also intentionally shared:
the in-memory implementation owns the application employee collection, while
the file-backed implementation represents one shared persistence gateway.

`AuditLogger` is different: it is declared with
`@Scope("prototype")`. A new logger target is appropriate for each lookup/use,
rather than sharing one logger object for the entire context. Because
`EmployeeServiceImpl` is a singleton, directly injecting a prototype would
otherwise resolve the prototype only once during service construction. The
logger therefore uses `ScopedProxyMode.TARGET_CLASS`. Spring injects a
class-based proxy into the service; each call through that proxy obtains the
appropriate prototype target. The same behavior is visible when `Main` calls
`context.getBean(AuditLogger.class)` repeatedly.

## Injection choices

Constructor injection is used for required collaborators in
`EmployeeServiceImpl` and `NotificationManager` (the constructors are marked
`@Autowired`). This makes dependencies explicit, ensures the objects cannot be
created without their required collaborators, and keeps the fields final.
`EmployeeValidator` is supplied through a `@Bean` method because it has no
framework annotations or dependencies and its construction is simple.

`@Value` field injection supplies scalar configuration values such as
`raise.max-percentage`, `company.name`, and `company.currency` from
`application.properties`. These are external settings rather than object
collaborators, so property injection keeps them configurable without adding
configuration plumbing to the constructor. `@Autowired` constructor injection
also makes all three notifier implementations explicit when
`NotificationManager` builds its notification list.

## Scoped beans and profiles

`AppConfig` scans `com.training.empmanager`, so both repository classes are
discovered, but only the class matching the active profile is registered:

- `dev` wires `InMemoryEmployeeRepository`, which stores employees in memory
  and is convenient for local runs.
- `prod` wires `FileBackedEmployeeRepository`, which persists employees in
  `employees.txt` and reads them back between operations.

Both implement `EmployeeRepository`, so `EmployeeServiceImpl` depends only on
the interface and does not change when the storage mechanism changes. The
current `Main` program activates `dev` before refreshing the context:
`context.getEnvironment().setActiveProfiles("dev")`. To run with the
file-backed implementation, use `"prod"` instead; only one profile-specific
repository should be active at a time.
