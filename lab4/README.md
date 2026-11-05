# Spring Core Lab: IoC and Bean Management (XML configuration)

Day 1 lab. The project follows the handout step by step. The handout rewrites the same
`Student` class and `beansConfig.xml` at every stage, so each stage lives in its own
package with its own `Student`, `App` and XML file. That way every stage still runs.

| Stage | Handout section | Package | XML (src/main/resources/stageNN/) |
|---|---|---|---|
| 1 | Problem: tight coupling | `com.craft.stage01` | none |
| 2 | Let Spring manage object creation | `stage02` | `beansConfig.xml` |
| 3 | Add fields; set values by hand | `stage03` | `beansConfig.xml` |
| 4 | Setter-based injection | `stage04` | `beansConfig.xml` |
| 5 | Constructor-based injection (by name, no name, by index) | `stage05` | `beansConfig.xml`, `-noname`, `-index` |
| 6 | Literal dependency injection | `stage06` | `beansConfig.xml` |
| 7 | Object dependencies with `ref` | `stage07` | `beansConfig.xml` |
| 8 | Object injection through the constructor | `stage08` | `beansConfig.xml` |
| 9 | Collections: `list`, `array`, `set` | `stage09` | `beansConfig.xml`, `-array`, `-set` |
| 10 | Bean scopes: singleton and prototype | `stage10` | `beansConfig.xml`, `-prototype` |

## Run
Requirements: JDK 21 and Maven (bundled with IntelliJ IDEA).

- To run one stage, open its `App.java` and run `main()`.
- To run every stage in order, run `com.craft.RunAll`.

## Summary
- **IoC** moves the control of creating and wiring objects out of your code and into a framework ("Don't call us, we'll call you").
- **DI** is how Spring does IoC: the container creates an object's dependencies and hands them in through setters or constructors instead of the object using `new`.
- The part of Spring that does this is the **IoC container** (`ApplicationContext`), and every object it manages is a **bean**.
