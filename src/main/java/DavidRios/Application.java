package DavidRios;

import DavidRios.entitiesDAOs.LiteratureDAO;
import DavidRios.entitiesDAOs.LoanDAO;
import DavidRios.entitiesDAOs.UserDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Application {
    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogobibliograficojpa");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        UserDAO ud = new UserDAO(em);
        LiteratureDAO ld = new LiteratureDAO(em);
        LoanDAO lod = new LoanDAO(em);


        em.close();
        emf.close();
    }
}
