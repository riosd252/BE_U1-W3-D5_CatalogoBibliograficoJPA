package DavidRios;

import DavidRios.entities.Book;
import DavidRios.entities.Loan;
import DavidRios.entities.User;
import DavidRios.entitiesDAOs.LiteratureDAO;
import DavidRios.entitiesDAOs.LoanDAO;
import DavidRios.entitiesDAOs.UserDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Application {
    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogobibliograficojpa");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        UserDAO ud = new UserDAO(em);
        LiteratureDAO ld = new LiteratureDAO(em);
        LoanDAO lod = new LoanDAO(em);

        User david = new User("David", "Rios", LocalDate.of(1997, 1, 13), 12345);
        Book randomBook = new Book("IT458204839","Pandemic and crisis", LocalDate.of(2020, 3,5), 200, "David Rios", "History");
        Loan davidRandom = new Loan(david, randomBook, LocalDate.of(2023,12,15));

        ud.saveUser(david);
        ld.saveLiterature(randomBook);
        lod.saveLoan(davidRandom);

        System.out.println(ld.findByIsbn("IT458204839"));
        System.out.println(ld.findByTitle("crisis"));
        System.out.println(ld.findByAuthor("David Rios"));
        System.out.println(ld.findByYear(2020));
        System.out.println(ld.findByCard(12345));
        System.out.println(lod.findExpired());

        em.close();
        emf.close();
    }
}
