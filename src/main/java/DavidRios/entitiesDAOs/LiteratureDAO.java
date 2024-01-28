package DavidRios.entitiesDAOs;

import DavidRios.entities.Book;
import DavidRios.entities.Literature;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.transaction.TransactionalException;
import java.util.List;

public class LiteratureDAO {
    private final EntityManager em;

    // CONSTRUCTOR
    public LiteratureDAO(EntityManager em) {
        this.em = em;
    }

    // DAO METHODS

    public void saveLiterature (Literature literature) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(literature);
            transaction.commit();
            System.out.println(literature.getTitle() + ", ISBN " + literature.getIsbnCode() + ", saved successfully!");
        } catch (TransactionalException tErr) {
            System.err.println(tErr.getMessage());
        }
    }

    public Literature findByIsbn(String isbn) {
        String myQuery = "SELECT l FROM Literature l WHERE l.isbnCode LIKE:isbnCode";
        TypedQuery<Literature> query = em.createQuery(myQuery, Literature.class);
        query.setParameter("isbnCode", isbn);
        if (query.getSingleResult() == null) System.out.println("Literature item not found.");
        return query.getSingleResult();
    }

   public List<Literature> findByYear(int year) {
        String myQuery = "SELECT l FROM Literature l WHERE FUNCTION('YEAR', l.publicationDate) = :year";
        TypedQuery<Literature> query = em.createQuery(myQuery, Literature.class);
        query.setParameter("year", year);
        if (query.getResultList() == null) System.out.println("No literature published on " + year + " was found.");
       return query.getResultList();
    }

    public List<Book> findByAuthor(String author) {
        String myQuery = "SELECT b FROM Book b WHERE b.author LIKE:author";
        TypedQuery<Book> query = em.createQuery(myQuery, Book.class);
        query.setParameter("author", author);
        if (query.getResultList() == null) System.out.println("There are no books from " + author + " in store.");
        return query.getResultList();
    }

    public List<Literature> findByTitle(String title) {
        String myQuery = "SELECT l FROM Literature l WHERE l.title LIKE:title";
        TypedQuery<Literature> query = em.createQuery(myQuery, Literature.class);
        query.setParameter("title", "%" + title + "%");
        if (query.getResultList() == null) System.out.println("No title matches your research.");
        return query.getResultList();
    }

    public List<Literature> findByCard(long card) {
        String myQuery = "SELECT l FROM Literature l WHERE l.id IN (SELECT l.literatureOnLoan FROM Loan l WHERE l.user IN (SELECT u FROM User u WHERE u.loyaltyCardNumber = :card))";
        TypedQuery<Literature> query = em.createQuery(myQuery, Literature.class);
        query.setParameter("card", card);
        if (query.getResultList() == null) System.out.println("User card " + card + " has no linked loans.");
        return query.getResultList();
    }

    public void findAndDeleteByIsbn(String isbn){
        Literature itemToRemove = findByIsbn(isbn);
        if(itemToRemove != null) {
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                em.remove(itemToRemove);
                transaction.commit();
                System.out.println("Literature item successfully deleted from DB!");
            } catch (TransactionalException tErr) {
                System.err.println(tErr.getMessage());
            }
        } else {
            System.out.println("Literature item not found.");
        }
    }


}
