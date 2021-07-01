package TD1;

import java.util.ArrayList;

public class Patient {
	//attributs
	private int numeroSecuriteSociale;
	private String nom;
	
	//liste des consutations pour un patient
	static ArrayList<Consultation> listeConsultations;
	
	//constructeurs
	public Patient(int numeroSecuriteSociale, String nom) {
		this.numeroSecuriteSociale = numeroSecuriteSociale;
		this.nom = new String(nom);
	}
	
	public Patient(Patient patient) {
		this(patient.numeroSecuriteSociale, patient.nom);
	}

	//getters et setters des attributs
	public int getNumeroSecuriteSociale() {
		return numeroSecuriteSociale;
	}
	
	public void setNumeroSecuriteSociale(int numeroSecuriteSociale) {
		this.numeroSecuriteSociale = numeroSecuriteSociale;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = new String(nom);
	}

	@Override
	public String toString() {
		return "Patient [numeroSecuriteSociale=" + numeroSecuriteSociale + ", nom=" + nom + "]";
	}
	
	
	
}
