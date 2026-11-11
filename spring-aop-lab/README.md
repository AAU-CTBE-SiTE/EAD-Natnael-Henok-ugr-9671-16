# Spring AOP Lab: Aspect-Oriented Programming

AOP lets you add behaviour such as logging, timing, validation and auditing to your
code WITHOUT editing the business classes. `StudentService` contains no logging at all;
the aspects add it from the outside.

## Vocabulary
| Term | Meaning | In this project |
|---|---|---|
| Aspect | a class holding cross-cutting code | `LoggingAspect`, `ValidationAspect`, `TimingAspect`, `AuditAspect` |
| Advice | what to run, and when | `@Before`, `@After`, `@AfterReturning`, `@AfterThrowing`, `@Around` |
| Pointcut | which methods it applies to | `execution(...)`, `@annotation(...)`, `args(...)` |
| Join point | the method call being intercepted | `JoinPoint` / `ProceedingJoinPoint` |
| Target | the object being advised | `StudentService` |
| Weaving / proxy | Spring wraps the target in a proxy at runtime | `@EnableAspectJAutoProxy` |

## Project layout
```
src/main/java/com/craft/aop/
  App.java                 runs 7 demo sections
  config/AppConfig.java    @ComponentScan + @EnableAspectJAutoProxy
  service/StudentService   the business code (no AOP code inside)
  aspect/                  the four aspects
  annotation/              @TrackTime and @Audit (custom annotations)
```

## Run
Requirements: JDK 21 and Maven (bundled with IntelliJ IDEA).
Open the project in IntelliJ, wait for Maven to load, and run `com.craft.aop.App`.
