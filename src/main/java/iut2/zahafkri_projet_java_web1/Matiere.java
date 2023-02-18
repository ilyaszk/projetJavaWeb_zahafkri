package iut2.zahafkri_projet_java_web1;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Matiere {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String nom;

	@OneToMany(mappedBy = "matiere", cascade = CascadeType.ALL)
	private List<NoteExamen> notesExamen;

	@ManyToMany
	@JoinTable(
			name = "MATIERE_ETUDIANT",
			joinColumns = @JoinColumn(name = "matiere_id"),
			inverseJoinColumns = @JoinColumn(name = "etudiant_id")
	)
	private List<Etudiant> etudiants;

	// Getters et setters

	public Matiere() {
		super();
	}
	public Matiere(String nom) {
		this.nom = nom;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<NoteExamen> getNotesExamen() {
		return notesExamen;
	}

	public void setNotesExamen(List<NoteExamen> notesExamen) {
		this.notesExamen = notesExamen;
	}

	public List<Etudiant> getEtudiants() {
		return etudiants;
	}

	public void setEtudiants(List<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}

	public void addEtudiant(Etudiant etudiant){
		this.etudiants.add(etudiant);
	}
}