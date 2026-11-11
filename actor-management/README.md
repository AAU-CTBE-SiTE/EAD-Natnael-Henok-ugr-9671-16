# Actor Management: Spring Boot CRUD using an ArrayList

A small REST API. The actors are kept in an `ArrayList` inside `ActorController`,
so there is no database and the data resets every time the app restarts.

## Endpoints (base path `/api/v1`)

| Method | URL | What it does | Success | Not found |
|---|---|---|---|---|
| GET | `/actor` | list all actors | 200 | |
| GET | `/actor/{id}` | get one actor | 200 | 404 |
| POST | `/actor` | add an actor (id is assigned by the server) | 201 | |
| PUT | `/actor/{id}` | replace name and movieName | 200 | 404 |
| PATCH | `/actor/{id}` | change only the fields you send | 200 | 404 |
| DELETE | `/actor/{id}` | remove the actor | 204 | 404 |

Actor JSON: `{ "id": 1, "name": "John Doe", "movieName": "The Great Adventure" }`

## Run
Requirements: JDK 21 and Maven (bundled with IntelliJ IDEA).

Run `ActorManagementApplication.main()`, then call the API on `http://localhost:8080`.
`requests.http` has a ready-made request for every endpoint (IntelliJ runs it with the green arrow).

Or with curl:
```
curl http://localhost:8080/api/v1/actor
curl -X POST http://localhost:8080/api/v1/actor -H "Content-Type: application/json" -d '{"name":"Sam Wilson","movieName":"Lost City"}'
curl -X PUT http://localhost:8080/api/v1/actor/1 -H "Content-Type: application/json" -d '{"name":"John Doe Jr.","movieName":"The Great Adventure 2"}'
curl -X PATCH http://localhost:8080/api/v1/actor/2 -H "Content-Type: application/json" -d '{"movieName":"Mystery Island Returns"}'
curl -X DELETE http://localhost:8080/api/v1/actor/2
```
