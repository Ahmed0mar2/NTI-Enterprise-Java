# Online Store

A small online store backend built with plain Spring, JPA, Hibernate, and an
embedded H2 database.

The project currently supports:

- Customers and products
- Orders, order items, payments, and stock updates
- Paying, shipping, and cancelling orders
- Reports and category discounts
- Transaction handling with pessimistic stock locking
- A simple audit log using `REQUIRES_NEW`

## Running the demo

The demo is in `com.store.Main`. It starts the Spring context, creates a
customer and product, places an order, pays it, and ships it.

Run `com.store.Main` from IntelliJ, using the project Maven configuration.

## Running the tests

```text
mvn test
```

The tests use the same Spring configuration and an in-memory H2 database.

## Project structure

- `model` contains the JPA entities and enums.
- `repository` contains the hand-written repositories using `EntityManager`.
- `service` contains business rules and transactions.
- `dto` contains report and order summary records.
- `test` contains the integration tests.

The database uses `create-drop`, so the data is recreated each time the
application starts.
