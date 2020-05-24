package techblog.repository;

import org.springframework.stereotype.Repository;
import techblog.model.User;

import javax.persistence.*;

@Repository
public class UserRepository {

    @PersistenceUnit(unitName = "techblog")
    private EntityManagerFactory emf;

//    Creating a new User in the Users Database
    public void registerUser(User newUser){
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(newUser);
            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
        }
    }

    public User checkUser(String username, String password){

        try {
            EntityManager em = emf.createEntityManager();
            TypedQuery<User> typedQuery = em.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class);
            //Parameter Binding
            typedQuery.setParameter("username", username);
            typedQuery.setParameter("password", password);

            //Return a single user
            return typedQuery.getSingleResult();
        } catch(NoResultException nre){
            return null;
        }
    }
}
