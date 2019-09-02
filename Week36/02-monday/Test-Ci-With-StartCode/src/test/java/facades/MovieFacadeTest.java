package facades;

import dto.MovieDTO;
import utils.EMF_Creator;
import entities.Movie;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class MovieFacadeTest {

    private static EntityManagerFactory emf;
    private static MovieFacade facade;
    private static List<Movie> movies;

    public MovieFacadeTest() {
    }

    @BeforeAll
    public static void setUpClassV2() {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.DROP_AND_CREATE);
        facade = MovieFacade.getFacadeExample(emf);
        EntityManager em = emf.createEntityManager();
        movies = new LinkedList<>(); //init

        //add to collection
        movies.add(new Movie("The Fellowship of the Ring", Date.valueOf("2001-12-19"), 10, 100000));
        movies.add(new Movie("The Two Towers", Date.valueOf("2002-12-18"), 10, 123455678));
        movies.add(new Movie("The Return of the King", Date.valueOf("2003-12-17"), 10, 999999999));

        try {
            for (Movie m : movies) {
                em.getTransaction().begin();
                em.persist(m);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }

    @org.junit.jupiter.api.Test
    public void testGetMovieCount() {
        //Arrange
        Long expResult = (long) movies.size();
        //Act
        Long result = facade.getMovieCount();
        //Assert
        assertNotNull(result);
        assertEquals(expResult, result);
    }
    
     @org.junit.jupiter.api.Test
    public void testGetAllMovies() throws Exception{
        //Arrange
        List<MovieDTO> expResult = new ArrayList();
        List<MovieDTO> result;
        for (Movie m : movies)
        {
            expResult.add(new MovieDTO(m));
        }
        //Act
        result = facade.getAllMovieDTOs()   ;
        //Assert
        assertNotNull(result);
        assertEquals(expResult, result);
                
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
//    @BeforeEach
//    public void setUp() {
//        EntityManager em = emf.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            em.createNamedQuery("RenameMe.deleteAllRows").executeUpdate();
//            em.persist(new Movie("The Lord of the Rings", Date.valueOf("2001-12-19"), 5, 125000));
//            em.persist(new Movie("Blå Mænd", Date.valueOf("2001-12-19"), 6, 20000));
//            //em.persist(new Movie("Some txt", "More text"));
//            //em.persist(new Movie("aaa", "bbb"));
//
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
//    }
}
