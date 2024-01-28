package DavidRios.entitiesDAOs;

import DavidRios.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.TransactionalException;

public class UserDAO {
    private final EntityManager em;

    // CONSTRUCTOR
    public UserDAO(EntityManager em) {
        this.em = em;
    }

    // DAO METHODS

    public void saveUser(User user) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(user);
            transaction.commit();
            System.out.println(user.getName() +" "+ user.getSurname() + " saved successfully!");
        } catch (TransactionalException tErr) {
            System.err.println(tErr.getMessage());
        }
    }

    public User findUserById(long id) {
        return em.find(User.class, id);
    }

    public void findAndDeleteUser(long id) {
        User found = findUserById(id);
        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                em.remove(found);
                transaction.commit();
                System.out.println("User successfully deleted from DB!");
            } catch (TransactionalException tErr) {
                System.err.println(tErr.getMessage());
            }
        } else {
            System.out.println("User not found.");
        }
    }


}
