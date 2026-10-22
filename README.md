# JPA Lab

Jakarta Persistence (JPA) with Hibernate 6, MySQL and Lombok. CRUD (create, read, update, delete) with the EntityManager API on a `Student` entity.

## Setup
1. Create the database: `create database jpadb1;`
2. Edit the MySQL user and password in `src/main/resources/META-INF/persistence.xml` if yours are not `root` / `root`.
3. Open the project in IntelliJ (enable Lombok annotation processing) and run `com.craft.App`.

Hibernate creates the `student` table automatically (`hbm2ddl.auto=update`).
