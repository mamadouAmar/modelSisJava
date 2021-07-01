package TD1;

import java.util.ArrayList;
import java.util.Date;

public class Consultation {
	
	private int numero;
	private Date date;
	private Patient patientAssiste;
	private Medecin medecin;
	
	//attribut où sont stockés les médicaents prescrits lors d'une consultation
	private ArrayList<Prescription> medicamentsPrescrits = new ArrayList<Prescription>();
	
	//liste des patients consultes dépendant du paramètre
	static ArrayList<Patient> patientsConsultes;
	static void afficherPatientConsultes() {
		System.out.println("Resultat ....;");
		for(Patient p : patientsConsultes) {
			System.out.println(p);
		}
	}
	
	
	//liste de medecins dépendant du paramètre
	static ArrayList<Medecin> medecinsConsul;
	static void afficherMedecinsConsul() {
		System.out.println("Resultat ....;");
		for(Medecin m : medecinsConsul) {
			System.out.println(m);
		}
	}
	
	//constructeurs
	public Consultation(int numero, Date date, Patient patientAssiste, Medecin medecin) {
		this.numero = numero;
		this.date = date;
		this.patientAssiste = new Patient(patientAssiste);
		this.medecin = new Medecin(medecin);
	}
	
	public Consultation(Consultation consultation) {
		this(consultation.numero, consultation.date, consultation.patientAssiste, consultation.medecin);
	}
		
	public int getNumero() {
		return numero;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Patient getPatientAssiste() {
		return patientAssiste;
	}
	
	public void setPatientAssiste(Patient patientAssiste) {
		this.patientAssiste = patientAssiste;
	}
	
	public Medecin getMedecin() {
		return medecin;
	}
	
	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}
	
	@Override
	public String toString() {
		return "Consultation [numero=" + numero + ", date=" + date + ", patientAssiste=" + patientAssiste + ", medecin="
				+ medecin + "]";
	}
	
	//Récupération des médicaments prescrits
	public void setMedicamentsPrescrits(Prescription p) {
		this.medicamentsPrescrits.add(p);
	}
	
	//Récupérer tout les médocs priscrits
	public void setAllMedicamentsPrescrits() {
		try {
			for(int i=0; i < Fonctionnalites.prescriptions.size(); i++) {
				if(Fonctionnalites.prescriptions.get(i).getConsultation().equals(this)
						&& !this.medicamentsPrescrits.contains(Fonctionnalites.prescriptions.get(i)))
					this.medicamentsPrescrits.add(Fonctionnalites.prescriptions.get(i));
			}
		}
		catch(Exception e){
			System.out.println("Erreur lors de la recherche des médicaments prescrits\n" + e.getMessage());
		}
	}
	
	//affichage des médicaments prscrits
	public void afficherMedicamentsPrescrits() {
		System.out.println(this);
		for(Prescription p : this.medicamentsPrescrits)
			System.out.println(p.afficherMedicament());
	}
	
	//Comparer les date sans tenir compte des de l'heure, de la minute, de la seconde
	@SuppressWarnings("deprecation")
	public static boolean memeDate(Date d, Date date2) {
		if(d.getYear() == date2.getYear()
				&& d.getMonth() == date2.getMonth()
				&& d.getDate() == date2.getDate())
			return true;
		return false;
	}
	
	
}
