/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author runin
 */
public class EntityTested {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    public static void main(String[] args) {
        EntityTested et = new EntityTested();
        et.persist(new Customer("Knud", "Knudsen"));
        et.persist(new Customer("Frans", "Frandsen"));
        et.persist(new Customer("Per", "Persen"));
    }

    public void persist(Customer c) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
