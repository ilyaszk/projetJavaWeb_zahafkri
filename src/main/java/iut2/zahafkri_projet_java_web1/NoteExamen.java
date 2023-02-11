package iut2.zahafkri_projet_java_web1;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "NOTE_EXAMEN")
public class NoteExamen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nomExamen;

    @Column(nullable = true)
    private float note;

    @ManyToOne
    @JoinColumn(name = "etudiant_id")
    private Etudiant etudiant;

    @ManyToOne
    @JoinColumn(name = "matiere_id")
    private Matiere matiere;

    // Getters et setters

    public NoteExamen() {
        super();
    }

    public NoteExamen(Etudiant etudiant, Matiere matiere, String nomExamen, float note) {
        this.etudiant = etudiant;
        this.matiere = matiere;
        this.nomExamen = nomExamen;
        this.note = note;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public String getNomExamen() {
        return nomExamen;
    }

    public void setNomExamen(String nomExamen) {
        this.nomExamen = nomExamen;
    }
}
