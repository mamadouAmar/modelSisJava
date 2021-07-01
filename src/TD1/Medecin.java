package TD1;

import java.util.ArrayList;

public class Medecin {
	//attributs
	private int matricule;
	private String nom;
	
		
	//liste consulations effectuees par un mèdecin
	public static ArrayList<Consultation> listeConsultations;
		
	public Medecin(int matricule, String nom) {
		this.matricule = matricule;
		this.nom = new String(nom);
	}
	public Medecin(Medecin medecin) {
		this(medecin.matricule, medecin.nom); 
	}
	
	//getters and setters
	public int getMatricule() {
		return matricule;
	}
	public void setMatricule(int matricule) {
		this.matricule = matricule;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	@Override
	public String toString() {
		return "Medecin [matricule=" + matricule + ", nom=" + nom + "]";
	}
	
	public Medecin copie(Medecin m) {
		return new Medecin(m);
	}
}
