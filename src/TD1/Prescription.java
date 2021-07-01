package TD1;

import java.util.ArrayList;
import java.util.Map;

public class Prescription {
	private int numero;
	//nombre de fois que l'on prend le médicament par jour
	private int nombrePrise;
	private Medicament medicament;
	private Consultation consultation;
	
	//liste de nombre prescriptions de tout les médicaments
	public static Map<String, Integer> medicamentsNombrePrescriptions;
	//affichage
	public static void afficherMap() {
		System.out.println("medicament : nombre de prescriptions");
        for (Map.Entry el : medicamentsNombrePrescriptions.entrySet()) {
           System.out.println(el.getKey()+ " => " + el.getValue() + "prescriptions");
        }
	}
	
	
	//liste médicaments dépendants du paramètre
	static ArrayList<Medicament> medicamentsPrescrits;
	static void afficherMedicaments() {
		System.out.println("Médicaments .....");
		for(Medicament m: medicamentsPrescrits) {
			System.out.println(m);
		}
	}
	
	public Prescription(int numero, int nombrePrise, Medicament medicament, Consultation consultation) {
		this.numero = numero;
		this.nombrePrise = nombrePrise;
		this.medicament = medicament;
		//second methode
		this.medicament.nombre_prescription++;
		this.consultation = consultation;
	}
	
	public Prescription(Prescription p) {
		this(p.numero, p.nombrePrise, p.medicament, p.consultation);
	}

	@Override
	public String toString() {
		return "Prescription [numero=" + numero + ", nombrePrise=" + nombrePrise + ", medicament=" + medicament
				+ ", consultation=" + consultation + "]";
	}



	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getNombrePrise() {
		return nombrePrise;
	}
	public void setNombrePrise(int nombrePrise) {
		this.nombrePrise = nombrePrise;
	}
	public Medicament getMedicament() {
		return medicament;
	}
	public void setMedicament(Medicament medicament) {
		this.medicament = medicament;
	}
	public Consultation getConsultation() {
		return consultation;
	}
	public void setConsultation(Consultation consultation) {
		this.consultation = consultation;
	}

	public String afficherMedicament() {
		// TODO Auto-generated method stub
		return "Prescription [numero=" + numero + ", nombrePrise=" + nombrePrise+" fois par jour " + ", medicament=" + medicament
				+ "]";
	}
	
	
	
}
