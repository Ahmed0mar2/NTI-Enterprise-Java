# Library Management System - JPA Mapping Notes

This project is a small JPA-based library model that maps authors, books, publishers, categories, and a person hierarchy.

## Relationship mappings

- Author to Book: `@OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)`
  - `Author` is the inverse side.
  - `Book.author` is the owning side because it has the foreign key (`author_id`).
  - `Author.addBook()` updates both sides so the in-memory graph stays consistent.

- Book to Publisher: `@ManyToOne(fetch = FetchType.LAZY)`
  - `Book` owns the relationship via `@JoinColumn(name = "publisher_id")`.
  - The publisher is not the owning side because the foreign key lives in the book table.

- Book to Category: `@ManyToMany`
  - The join table is `book_category`.
  - `Book.categories` is the owning side, using `@JoinTable(...)`.
  - `Category.books` is mapped as inverse with `mappedBy = "categories"`.
  - `Book.addCategory()` updates both collections to keep the association synchronized.

## Fetch and cascade choices

- Fetch type is `LAZY` for the `author` and `publisher` associations to avoid loading full related objects unless needed.
- The project explicitly demonstrates the `JOIN FETCH` pattern in JPQL to load related data in one query when needed, which prevents LazyInitializationException after detachment.
- The `Author.books` collection uses `CascadeType.ALL` and `orphanRemoval = true`, so persisting or removing an author also persists or removes associated books, and removing a book from the collection detaches it from the parent.

## Inheritance strategy

- `Person` uses `@Inheritance(strategy = InheritanceType.SINGLE_TABLE)`.
- All subclasses are stored in one table with a discriminator column (`type`).
- `Employee` and `Customer` extend `Person` and use `@DiscriminatorValue("Emp")` and `@DiscriminatorValue("Cust")`.
- This is a good fit for simple, similar entities because it reduces join complexity and keeps read operations simple.

## Query decisions

- JPQL is used for named domain queries such as:
  - books by author name
  - books by publisher name
  - author with books using `JOIN FETCH`
- Criteria API is used for dynamic filtering, especially when title and author name are optional.
- Queries bind parameters with `:name` or positional parameters instead of string concatenation, which keeps the code safer and more maintainable.

## Summary

The design favors a simple bidirectional model with clear ownership, lazy-loading for associations, and a single-table inheritance strategy to keep the project easy to understand while still demonstrating the core JPA concepts used in enterprise Java applications.
