package com.example;

import com.example.model.Author;
import com.example.model.Book;
import com.example.model.Publisher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory ef = Persistence.createEntityManagerFactory("libraryPU");
        EntityManager em = ef.createEntityManager();

        em.getTransaction().begin();

        Author alaa = new Author("Eng.Alaa");
        Publisher kotbJuice = new Publisher("3aseer el kotb");
        Book core = new Book("Spring Core");
        Book security = new Book("Spring Security");
        Book boot = new Book("Spring boot");

        alaa.addBook(security);
        alaa.addBook(core);
        alaa.addBook(boot);
        security.setPublisher(kotbJuice);
        boot.setPublisher(kotbJuice);

        em.persist(alaa);
        em.persist(kotbJuice);
        //alaa.removeBook(boot);

        em.getTransaction().commit();

        System.out.println(em.find(Author.class, alaa.getId()));

        // finding all alaa's books
        TypedQuery<Book> alaaBooks = em.createQuery(
                "SELECT b FROM Book b WHERE b.author.name = :name", Book.class);
        alaaBooks.setParameter("name", "Eng.Alaa");
        List<Book> bookLis = alaaBooks.getResultList();
        System.out.println("Eng.Alaa books list using jpql: {" + bookLis + "}");

        // finding books by publisher
        TypedQuery publisherQuery = em.createQuery("SELECT b FROM Book b WHERE b.publisher.name = :name", Book.class);
        publisherQuery.setParameter("name", "3aseer el kotb");
        System.out.println("getting books by publisher\n" + publisherQuery.getResultList());

        // id positional parameter
        TypedQuery<Book> positionalQuery = em.createQuery("SELECT b FROM Book b where b.id = ?1", Book.class);
        positionalQuery.setParameter(1, 1);
        System.out.println("Positional parameter query for book with id = 1: " + positionalQuery.getResultList());

        //join fetch author and his books
        TypedQuery<Author> authorQuery = em.createQuery("SELECT a FROM Author a JOIN FETCH a.books WHERE a.name = :name", Author.class);
        authorQuery.setParameter("name", "Eng.Alaa");
        System.out.println(authorQuery.getResultList());

        //author with books count
        TypedQuery<Author> authorWithBooksCount = em.createQuery("SELECT a,COUNT(a.books) FROM  Author a group by a.id", Author.class);
        System.out.println(authorWithBooksCount);


        //Criteria API query that finds books by optional title and author name
        String title = "security";
        String authorName = "Eng.Alaa";
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Book> query = cb.createQuery(Book.class);

        Root<Book> book = query.from(Book.class);

        List<Predicate> predicates = new ArrayList<>();

        if (title != null && !title.isBlank()) {
            predicates.add(
                    cb.equal(book.get("title"), title)
            );
        }

        if (authorName != null && !authorName.isBlank()) {
            predicates.add(
                    cb.equal(book.get("author").get("name"), authorName)
            );
        }

        query.select(book);

        if (!predicates.isEmpty()) {
            query.where(cb.and(predicates.toArray(new Predicate[0])));
        }

        List<Book> books = em.createQuery(query).getResultList();

        System.out.println(books);


        //lazy vs join fetch -> join fetch loads the other entity when querying so the data is present even if the object is detached so no exception when accessing
        TypedQuery<Author> lazyQuery = em.createQuery("SELECT a FROM Author a WHERE a.name =:name ", Author.class);
        TypedQuery<Author> joinFetchQuery = em.createQuery("SELECT a FROM Author a JOIN FETCH a.books WHERE a.name = :name", Author.class);
        lazyQuery.setParameter("name","Eng.Alaa");
        joinFetchQuery.setParameter("name","Eng.Alaa");
        Author lazyAuthor = lazyQuery.getSingleResult();
        Author joinFetchAuthor = joinFetchQuery.getSingleResult();
        //em.close();
        //lazyAuthor.getBooks().size(); -> Exception
        //joinFetchAuthor.getBooks().size(); -> no Exception books already fetched




        //SELECT b FROM Book b WHERE b.title = :title -> JPQL query for standard JPA
        //HQL can understand the JPA standard and add their hibernate specific feature as 'hibernate native' exists independently
    }
}
