package facades;

import dto.MovieDTO;
import entities.Movie;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class MovieFacade {

    private static MovieFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private MovieFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MovieFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public long getMovieCount() {
        EntityManager em = getEntityManager();
        try {
            return (long) em.createQuery("SELECT COUNT(r) FROM Movie r").getSingleResult();
        } finally {
            em.close();
        }
    }

    public Movie addMovie(Movie movie) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(movie);
            em.getTransaction().commit();
            return movie;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new Exception("Could not add movie: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    public Movie addMovie(String name, Date releaseDate, int score, int earnings) throws Exception {
        EntityManager em = getEntityManager();
        try {
            Movie movie = new Movie(name, releaseDate, score, earnings);
            em.getTransaction().begin();
            em.persist(movie);
            em.getTransaction().commit();
            return movie;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new Exception("Could not add movie: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    List<MovieDTO> getAllMovieDTOs() throws Exception {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT new dto.MovieDTO(m) FROM Movie m", MovieDTO.class).getResultList();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new Exception("Could not retrieve movies: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

}
