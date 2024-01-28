package DavidRios.entitiesDAOs;

import DavidRios.entities.Loan;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.transaction.TransactionalException;
import java.util.List;

public class LoanDAO {
    private final EntityManager em;

    // CONSTRUCTOR
    public LoanDAO(EntityManager em) {
        this.em = em;
    }

    // DAO METHODS

    public void saveLoan (Loan loan) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(loan);
            transaction.commit();
            System.out.println("Loan record saved successfully!");
        } catch (TransactionalException tErr) {
            System.err.println(tErr.getMessage());
        }
    }

    public Loan findLoanById(long id) {
        return em.find(Loan.class, id);
    }
    public List<Loan> findExpired() {
        String myQuery = "SELECT l FROM Loan l WHERE l.scheduledReturnDate < CURRENT_DATE AND l.returnDate = null";
        TypedQuery<Loan> query = em.createQuery(myQuery, Loan.class);
        if (query.getResultList() == null) System.out.println("No expired loans were found.");
        return query.getResultList();
    }

    public void findAndDeleteLoan(long id) {
        Loan found = findLoanById(id);
        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                em.remove(found);
                transaction.commit();
                System.out.println("Loan record deleted from DB!");
            } catch (TransactionalException tErr) {
                System.err.println(tErr.getMessage());
            }
        } else {
            System.out.println("Loan record not found.");
        }
    }


}
