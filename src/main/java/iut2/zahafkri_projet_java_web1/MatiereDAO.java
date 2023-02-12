package iut2.zahafkri_projet_java_web1;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class MatiereDAO {
    public static Matiere create(String nom) {
        EntityManager em = GestionFactory.factory.createEntityManager();
        Matiere matiere = new Matiere(nom);
        em.getTransaction().begin();
        em.persist(matiere);
        em.getTransaction().commit();
        em.close();
        return matiere;
    }

    public static List<Matiere> getAll() {
        EntityManager em = GestionFactory.factory.createEntityManager();
        Query query = em.createQuery("SELECT m FROM Matiere m");
        List<Matiere> matieres = query.getResultList();
        em.close();
        return matieres;
    }

    public static void update(Matiere matiere) {
        EntityManager em = GestionFactory.factory.createEntityManager();
        em.getTransaction().begin();
        em.merge(matiere);
        em.getTransaction().commit();
        em.close();
    }

    public static void remove(Matiere matiere) {
        EntityManager em = GestionFactory.factory.createEntityManager();
        em.getTransaction().begin();
        if (!em.contains(matiere)) {
            matiere = em.merge(matiere);
        }
        em.remove(matiere);
        em.getTransaction().commit();
        em.close();
    }

    public static int removeAll() {
        EntityManager em = GestionFactory.factory.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("DELETE FROM Matiere m");
        int nb = query.executeUpdate();
        em.getTransaction().commit();
        em.close();
        return nb;
    }

    public static Matiere getMatiereById(int id) {
        EntityManager em = GestionFactory.factory.createEntityManager();
        Query query = em.createQuery("SELECT m FROM Matiere m WHERE m.id = :id");
        query.setParameter("id", id);
        Matiere matiere = (Matiere) query.getSingleResult();
        em.close();
        return matiere;
    }
}
