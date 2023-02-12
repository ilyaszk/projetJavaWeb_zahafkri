package iut2.zahafkri_projet_java_web1;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Entity implementation class for Entity: Groupe
 */
@Entity
public class Etudiant implements Serializable {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable = false)
	private String prenom;

	@Column(nullable = false)
	private String nom;

	private int nbAbsences;

	private float moyenneGenerale;

	@ManyToOne
	@JoinColumn(name = "groupe_id")
	private Groupe groupe;

	//list<matiere>
	@ManyToMany(mappedBy = "etudiants", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private List<Matiere> matieres;

	private static final long serialVersionUID = 1L;

	public Etudiant() {
		super();
		nbAbsences = 0;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Groupe getGroupe() {
		return this.groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
		if (!groupe.getEtudiants().contains(this)) {
			groupe.getEtudiants().add(this);
		}
	}

	public int getNbAbsences() {
		return nbAbsences;
	}

	public void setNbAbsences(int nbAbsences) {
		this.nbAbsences = nbAbsences;
	}

	public float getMoyenneGenerale() {
		return moyenneGenerale;
	}

	public void setMoyenneGenerale(float moyenneGenerale) {
		this.moyenneGenerale = moyenneGenerale;
	}

	public List<Matiere> getMatieres() {
		return matieres;
	}

	public void setMatieres(List<Matiere> matieres) {
		this.matieres = matieres;
	}

	public void addMatiere(Matiere matiere) {
		if (!matieres.contains(matiere)) {
			matieres.add(matiere);
		}
		if (!matiere.getEtudiants().contains(this)) {
			matiere.getEtudiants().add(this);
		}
	}

	public void addNoteExamen(NoteExamen noteExamen) {
		if (!matieres.contains(noteExamen.getMatiere())) {
			matieres.add(noteExamen.getMatiere());
		}
		if (!noteExamen.getMatiere().getEtudiants().contains(this)) {
			noteExamen.getMatiere().getEtudiants().add(this);
		}
	}
}
