package TD1;

import java.util.Date;
import java.util.Scanner;

public class Test {
	
	static Medecin medecin;
	static Patient patient;
	static Medicament medicament;
	static Consultation consultation;
	static Prescription prescription;
	static int nbPrises = 2; //default
	static HospitalDatabase hdb = new HospitalDatabase();
	
	//initialiser les tableaux
	static void init() {
		hdb.selectAllMedicaments();
		hdb.selectAllMedecin();
		hdb.selectAllPatients();
		hdb.selectAllConsultations();
		hdb.selectAllPrescriptions();
	}
	
	
	static Scanner clavier = new Scanner(System.in);
	
	static int trouverConsultation(int num) {
		int n = -1;
		for(Consultation c : Fonctionnalites.consultations) {
			if(c.getNumero() == num) 
				n = c.getNumero();
		}
		return n;
		
	}
	
	static int trouverMedicament(String lib) {
		int existe = -1;
		for(Medicament medoc : Fonctionnalites.medicaments) {
			if(medoc.getLibelle().equals(lib)) {
				existe = medoc.getCode();
				System.out.println("Le medicament existe déjà :\n"+medoc);
				break;
			}
		}
		return existe;
	}
	
	static void enregistrerMedicament() {
		System.out.println("Enregistrement d'un Medicament ...\nDonnez le libelle du medicament");
		String lib = new String(clavier.next());
		int code = trouverMedicament(lib);
		if(code == -1) {
			medicament = new Medicament(Fonctionnalites.medicaments.size(), lib);
			hdb.insertMedicament(medicament);
			Fonctionnalites.medicaments.add(new Medicament(medicament));
			System.out.println("Medicament bien enregistré\n"+medicament);
		}
		else {
			medicament = Fonctionnalites.medicaments.get(code);
		}
	}
	
	static int trouverPatient(String nom) {
		int existe = -1;
		for(Patient pat : Fonctionnalites.patients) {
			if(pat.getNom().equals(nom)) {
				existe = pat.getNumeroSecuriteSociale();
				System.out.println("Le patient existe déjà :\n"+pat);
				break;
			}
		}
		return existe;
	}
	
	static void enregistrerPatient() {
		System.out.println("Enregistrement d'un patient ...\nDonnez le nom du patient");
		String nom = new String(clavier.next());
		int ss = trouverPatient(nom);
		if(ss == -1) {
			patient = new Patient(Fonctionnalites.patients.size(), nom);
			hdb.insertPatient(patient);
			Fonctionnalites.patients.add(new Patient(patient));
			System.out.println("Patient bien enregistré\n"+patient);
		}
		else {
			patient = Fonctionnalites.patients.get(ss);
		}
	}
	
	static int trouverMedecin(String nom) {
		int existe = -1;
		for(Medecin med : Fonctionnalites.medecins) {
			if(med.getNom().equals(nom)) {
				existe = med.getMatricule();
				System.out.println("Le medecin existe déjà :\n"+med);
				break;
			}
		}
		return existe;
	}
	static void enregistrerMedecin() {
		System.out.println("Enregistrement d'un medecin ...\nDonnez le nom du medecin");
		String nom = new String(clavier.next());
		int mat = trouverMedecin(nom);
		if(mat != -1) {
			medecin = Fonctionnalites.medecins.get(mat);
		}
		else {
			medecin = new Medecin(Fonctionnalites.medecins.size(), nom);
			hdb.insertMedecin(medecin);
			Fonctionnalites.medecins.add(new Medecin(medecin));
			System.out.println("medecin bien enregistré\n"+medecin);

		}
	}
	
	static int afficherMenu(String menu) {
		System.out.print(menu);
		System.out.println("Votre choix : ");
		int choix = clavier.nextInt();
		return choix;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		init();
		
		String menu_fonctionnnalités = new String("Choisissez une option :\n"
				+ "1- (Done)La liste des consultations pour un patient\n"
				+ "2- (Done) La Liste des patients consultés à une date donnée\n"
				+ "3- (Done) Pour une consultation afficher le médecin et son patient\n"
				+ "4- (Already Done) La liste des consultations effectuées par un médecin\n"
				+ "5- (Done) La liste des patients consultés par un médecin\n"
				+ "6- (Done) La liste des médecins qui ont consulté un patient\n"
				+ "7- (Done) La liste des médecins qui ont effectué des consultations à une date donnée\n"
				+ "8- (Done) Le nombre de consultations\n"
				+ "9- (Already Done) Liste des médicaments prescrit lors d’une consultation\n"
				+ "10- (Done) La liste des médicaments prescrit par un médecin à un patient à une date donnée\n"
				+ "11- (Done) La liste des médicaments prescrit par un médecin à un patient\n"
				+ "12- (Done) La liste des médicaments prescrit au moins par deux médecins"
				+ "13- (Done) Pour chaque médicament fournir le nombre total de prescription : Présenter deux solutions d’implémentation\n"
				+ "14- Retour\n"
				);
		String menu_enregistrement = new String("Veuillez choisir une option :\n"
				+ "1- Enregistrer un patient\n"
				+ "2- Enregistrer un médecin\n"
				+ "3- Enregistrer un médicament\n"
				+ "4- Enregistrer un consultation\n"
				+ "5- Consulter les enregistrements\n"
				+ "6- Quitter\n"
				);
		
		int a = 0;
		while(a != 6) {
			a = afficherMenu(menu_enregistrement);
			switch(a) {
			case 1:
				enregistrerPatient();
				break;
			case 2:
				enregistrerMedecin();
				break;
			case 3:
				enregistrerMedicament();
				break;
			case 5:
				int b = 0;
				while(b < 14)
				{
					b = afficherMenu(menu_fonctionnnalités);
					System.out.println();
					switch(b) {
						case 1:
							//1- (Done)La liste des consultations pour un patient
							enregistrerPatient();
							Fonctionnalites.defListeConsultationPatient(patient);
							Fonctionnalites.afficherListeConsultations();
							break;
						case 2:
							//2- (Done) La Liste des patients consultés à une date donnée
							System.out.println("La date ?? sous forme yyyy/mm/dd :");
							Date d = new Date(clavier.next());
							Fonctionnalites.defpatientsConsultes(d);
							Fonctionnalites.afficherListePatients();
							break;
						case 3:
							//3- (Done) Pour une consultation afficher le médecin et son patient\n
							System.out.println("Le numero de la consultation ??");
							int n = trouverConsultation(clavier.nextInt());
							if(n == -1)
								System.out.println("désolé cette consultation n'est pas enregistré !!");
							else
								System.out.println(Fonctionnalites.consultations.get(n));
							break;
						case 4:
							//4- (Already Done) La liste des consultations effectuées par un médecin\n
							enregistrerMedecin();
							Fonctionnalites.defListeConsultations(medecin);
							Fonctionnalites.afficherListeConsultations();
							break;
						case 5:
							//5- (Done) La liste des patients consultés par un médecin\n
							enregistrerMedecin();
							Fonctionnalites.defPatientsConsultes(medecin);
							Fonctionnalites.afficherListePatients();
							break;
						case 6:
							//6- (Done) La liste des médecins qui ont consulté un patient\n
							enregistrerPatient();
							Fonctionnalites.defListeMedecins(patient);
							Fonctionnalites.afficherListeMedecins();
							break;
						case 7:
							//7- (Done) La liste des médecins qui ont effectué des consultations à une date donnée\n
							System.out.println("La date ?? sous forme yyyy/mm/dd :");
							d = new Date(clavier.next());
							Fonctionnalites.defListeMedecins(d);
							Fonctionnalites.afficherListeMedecins();
							break;
						case 8:
							//8- (Done) Le nombre de consultations\n
							System.out.println("le nombre de consultation :"+ Fonctionnalites.nombreConsultation());
							break;
						case 9:
							//9- (Already Done) Liste des médicaments prescrit lors d’une consultation\n
							System.out.println("Le numero de la consultation ??");
							n = trouverConsultation(clavier.nextInt());
							if(n == -1)
								System.out.println("désolé cette consultation n'est pas enregistré !!");
							else {
								Fonctionnalites.consultations.get(n).setAllMedicamentsPrescrits();
								Fonctionnalites.consultations.get(n).afficherMedicamentsPrescrits();
							}
							break;
						case 10:
							//10- (Done) La liste des médicaments prescrit par un médecin à un patient à une date donnée\n
							enregistrerMedecin();
							enregistrerPatient();
							System.out.println("La date ?? sous forme yyyy/mm/dd :");
							d = new Date(clavier.next());
							Fonctionnalites.defMedicamentsPrescrits(medecin, patient, d);
							Fonctionnalites.afficherListeMedicament();
							break;
						case 11:
							//11- (Done) La liste des médicaments prescrit par un médecin à un patient\n
							enregistrerMedecin();
							enregistrerPatient();
							Fonctionnalites.defMedicamentsPrescrits(medecin, patient);
							Fonctionnalites.afficherListeMedicament();
							break;
						case 12:
							//12- (Done) La liste des médicaments prescrit au moins par deux médecins
							Fonctionnalites.defListeMedecaments2Medecins();
							Fonctionnalites.afficherListeMedicament();
							break;
						case 13:
							//13- (Done) Pour chaque médicament fournir le nombre total de prescription : Présenter deux solutions d’implémentation\n
							Fonctionnalites.defNombrePrescription();
							Prescription.afficherMap();
							//Ou
//							for(Medicament medoc : Fonctionnalites.medicaments)
//								System.out.println(medoc.getLibelle()+ "=>"+ medoc.nombre_prescription);
							break;
						default:
							break;
					}
				}
				break;
				case 4:
					System.out.println("Enregistrement de la consultation .....");
					System.out.println("Medecin qui consulte .....");
					enregistrerMedecin();
					System.out.println("Patient consulte.....");
					enregistrerPatient();
					consultation = new Consultation(Fonctionnalites.consultations.size(), new Date(), patient, medecin);
					hdb.insertConsultation(consultation);
					int c;
					do{
						System.out.println("Tapez sur 1 pour faire une prescription et 2 pour quitter");
						c = clavier.nextInt();
						switch(c) {
							case 1:{
								System.out.println("Prescription ......");
								enregistrerMedicament();
								System.out.print("Nombre de prises par jour : ");
								nbPrises = clavier.nextInt();
								if(!(nbPrises > 0 && nbPrises < 4))
									nbPrises = 4;
								Prescription pres = new Prescription(Fonctionnalites.prescriptions.size(), nbPrises, medicament, consultation);
								hdb.insertPrescription(pres);
								Fonctionnalites.prescriptions.add(new Prescription(pres));
								System.out.println(pres);
								}
								break;
							default:
								break;
						}
					}while(c==1);
					break;
			}
			
		}
	}

}
