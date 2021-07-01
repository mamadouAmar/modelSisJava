package TD1;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Fonctionnalites {
	
	//variable static où sont stockés tous les instances des consultations
	static ArrayList<Consultation> consultations = new ArrayList<Consultation>();
	
	static ArrayList<Consultation> listeConsultations = new ArrayList<Consultation>(); 
	static void afficherListeConsultations() {
		System.out.println("Résultats..");
		for(Consultation c : listeConsultations)
			System.out.println(c);
	}

	//variable static où sont stockés tous les instances des consultations
	static ArrayList<Medecin> medecins = new ArrayList<Medecin>();
	
	static ArrayList<Medecin> listeMedecins = new ArrayList<Medecin>();
	static void afficherListeMedecins() {
		System.out.println("Résultats..");
		for(Medecin m : listeMedecins)
			System.out.println(m);
	}
	
	
	//variable static où sont stockés tous les instances des consultations
	static ArrayList<Medicament> medicaments = new ArrayList<Medicament>();
	
	static ArrayList<Medicament> listeMedicaments = new ArrayList<Medicament>();
	static void afficherListeMedicament() {
		System.out.println("Résultats..");
		for(Medicament m : listeMedicaments)
			System.out.println(m);
	}
	
	
	//variable static où sont stockés tous les instances des consultations
	static ArrayList<Patient> patients = new ArrayList<Patient>();
	
	static ArrayList<Patient> listePatients = new ArrayList<Patient>();
	static void afficherListePatients() {
		System.out.println("Résultats..");
		for(Patient p : listePatients)
			System.out.println(p);
	}
	
	//variable static où sont stockés tous les instances des consultations
	static ArrayList<Prescription> prescriptions = new ArrayList<Prescription>();

	
	static void defListeConsultations(Medecin m) {
		Fonctionnalites.listeConsultations = new ArrayList<Consultation>();
		for(Consultation c : consultations) {
			if(c.getMedecin().equals(m))
				Fonctionnalites.listeConsultations.add(c);
		}
	}
	
	//liste des consultations pour un patient
	static void defListeConsultationPatient(Patient p) {
		Fonctionnalites.listeConsultations = new ArrayList<Consultation>();
		for(Consultation c : consultations) {
			if(p.equals(c.getPatientAssiste()))
				Fonctionnalites.listeConsultations.add(c);
		}
	}
	
	//liste des patients consultées à une date donnée
	static void defpatientsConsultes(Date d) {
		Fonctionnalites.listePatients = new ArrayList<Patient>();
		for(Consultation c : consultations) {
			Date dd = new Date(c.getDate().getYear(), c.getDate().getMonth(), c.getDate().getDate());
			if(d.equals(dd))
				Fonctionnalites.listePatients.add(c.getPatientAssiste());
		}
	}
	
	//liste des consultations effectues par un medecin
	static void defPatientsConsultes(Medecin m) {
		Fonctionnalites.listePatients = new ArrayList<Patient>();
		for(Consultation c : consultations) {
			if(m.equals(c.getMedecin()))
				Fonctionnalites.listePatients.add(c.getPatientAssiste());
		}
	}
	
	//La liste des médecins qui ont consulté un patient
	static void defListeMedecins(Patient p) {
		Fonctionnalites.listeMedecins = new ArrayList<Medecin>();
		for(Consultation c : consultations) {
			if(p.equals(c.getPatientAssiste()))
				Consultation.medecinsConsul.add(c.getMedecin());
		}
	}
	
	//La liste des médecins qui ont effectué des consultations à une date donnée.
	static void defListeMedecins(Date d) {
		Fonctionnalites.listeMedecins = new ArrayList<Medecin>();
		for(Consultation c : consultations) {
			if(d.equals(c.getDate()))
				Fonctionnalites.listeMedecins.add(c.getMedecin());
		}
	}
	
	//le nombre total de consultations
	static int nombreConsultation() {
		return Fonctionnalites.consultations.size();
	}
	
	//La liste des médicaments prescrit par un médecin à un patient à une date donnée
	static void defMedicamentsPrescrits(Medecin m, Patient p, Date d) {
		Fonctionnalites.listeMedicaments = new ArrayList<Medicament>();
		for(Prescription pres : prescriptions) {
			if(m.equals(pres.getConsultation().getMedecin())
				&& p.equals(pres.getConsultation().getPatientAssiste())
				&& Consultation.memeDate(d, pres.getConsultation().getDate()))
				Fonctionnalites.listeMedicaments.add(pres.getMedicament());
		}
	}
	
	// La liste des médicaments prescrit par un médecin à un patient
	static void defMedicamentsPrescrits(Medecin m, Patient p) {
		Fonctionnalites.listeMedicaments = new ArrayList<Medicament>();
		for(Prescription pres : prescriptions) {
			if(m.equals(pres.getConsultation().getMedecin())
				&& p.equals(pres.getConsultation().getPatientAssiste()))
				Fonctionnalites.listeMedicaments.add(pres.getMedicament());
		}
	}
	
	// La liste des médicaments prescrit au moins par deux médecins
	static void defListeMedecaments2Medecins() {
		Fonctionnalites.listeMedicaments = new ArrayList<Medicament>();
		for(Medicament medoc : medicaments) {
			int compteur = 0;
			Medecin m = null;
			for(Prescription pres : prescriptions) {
				if(medoc.equals(pres.getMedicament())
					&& !m.equals(pres.getConsultation().getMedecin()))
				{
					compteur++;
					m = new Medecin(pres.getConsultation().getMedecin());
				}
				if(compteur == 2) {
					Fonctionnalites.listeMedicaments.add(medoc);
					break;
				}
					
			}
		}
	}
	
	//Pour chaque médicament fournir le nombre total de prescription : Présenter deux solutions d’implémentation.
	static void defNombrePrescription() {
		Prescription.medicamentsNombrePrescriptions = new HashMap<String, Integer>();
		for (Prescription pres: prescriptions) {
			String libelle = new String(pres.getMedicament().getLibelle());
			if(Prescription.medicamentsNombrePrescriptions.containsKey(pres.getMedicament().getLibelle()))
				Prescription.medicamentsNombrePrescriptions.replace(
						pres.getMedicament().getLibelle(), 
						Prescription.medicamentsNombrePrescriptions.get(libelle), 
						Prescription.medicamentsNombrePrescriptions.get(libelle)+1);
			else
				Prescription.medicamentsNombrePrescriptions.put(libelle, 1);
		}
	}
	//OU
	//juste utiliser l'attribut de médicament créé
}
