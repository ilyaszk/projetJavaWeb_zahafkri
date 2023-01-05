package iut2.zahafkri_projet_java_web1;

public class Etudiant {
	
	private Integer id;
	private String prenom;
	private String nom;
	private int nbAbsence;
	private int moyenneGenerale;
	private String groupe;
	
	public Etudiant() {
		super();
	}

	public Etudiant(Integer id, String prenom, String nom, int nbAbsence, int moyenneGenerale, String groupe) {
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.nbAbsence = nbAbsence;
		this.moyenneGenerale = moyenneGenerale;
		this.groupe = groupe;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getNbAbsence() {
		return nbAbsence;
	}

	public void setNbAbsence(int nbAbsence) {
		this.nbAbsence = nbAbsence;
	}

	public int getMoyenneGenerale() {
		return moyenneGenerale;
	}

	public void setMoyenneGenerale(int moyenneGenerale) {
		this.moyenneGenerale = moyenneGenerale;
	}

	public String getGroupe() {
		return groupe;
	}

	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}
}
