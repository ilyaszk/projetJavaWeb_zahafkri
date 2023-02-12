package iut2.zahafkri_projet_java_web1;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class NoteExamenDAO {
    public static void create(Etudiant etudiant, Matiere matiere, String nomExamen) {
        EntityManager em = GestionFactory.factory.createEntityManager();
        NoteExamen noteExamen = new NoteExamen(etudiant, matiere, nomExamen);
        em.getTransaction().begin();
        em.persist(noteExamen);
        em.getTransaction().commit();
        em.close();
    }

    public static NoteExamen retrieveById(long id) {
        EntityManager em = GestionFactory.factory.createEntityManager();
        NoteExamen noteExamen = em.find(NoteExamen.class, id);
        em.close();
        return noteExamen;
    }

    public static NoteExamen update(NoteExamen noteExamen) {
        EntityManager em = GestionFactory.factory.createEntityManager();
        em.getTransaction().begin();
        em.merge(noteExamen);
        em.getTransaction().commit();
        em.close();
        return noteExamen;
    }

    public static void remove(NoteExamen noteExamen) {
        EntityManager em = GestionFactory.factory.createEntityManager();
        em.getTransaction().begin();
        if (!em.contains(noteExamen)) {
            noteExamen = em.merge(noteExamen);
        }
        em.remove(noteExamen);
        em.getTransaction().commit();
        em.close();
    }

    public static List<NoteExamen> getAll() {
        EntityManager em = GestionFactory.factory.createEntityManager();
        Query query = em.createQuery("SELECT n FROM NoteExamen n");
        List<NoteExamen> notesExamens = query.getResultList();
        em.close();
        return notesExamens;
    }

    public static List<NoteExamen> retrieveByNoteExamen(float note) {
        EntityManager em = GestionFactory.factory.createEntityManager();
        Query query = em.createQuery("SELECT n FROM NoteExamen n WHERE n.note = :note");
        query.setParameter("note", note);
        List<NoteExamen> notesExamens = query.getResultList();
        em.close();
        return notesExamens;
    }

    public static List<NoteExamen> retrieveByEtudiant(Etudiant etudiant) {
        EntityManager em = GestionFactory.factory.createEntityManager();
        Query query = em.createQuery("SELECT n FROM NoteExamen n WHERE n.etudiant = :etudiant");
        query.setParameter("etudiant", etudiant);
        List<NoteExamen> notesExamens = query.getResultList();
        em.close();
        return notesExamens;
    }

    //recupere les notes d'un etudiant par matiere
    public static List<NoteExamen> retrieveByEtudiantGroupByMatiere(Integer id) {
        EntityManager em = GestionFactory.factory.createEntityManager();
        Query query = em.createQuery("SELECT n FROM NoteExamen n WHERE n.etudiant.id = :idEtu GROUP BY n.matiere");
        query.setParameter("idEtu", id);
        List<NoteExamen> notesExamens = query.getResultList();
        em.close();
        return notesExamens;
    }
}