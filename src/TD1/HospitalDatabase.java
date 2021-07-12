package TD1;

import java.sql.*;
import java.sql.DriverManager;

public class HospitalDatabase {
	
	private Connection connection;
	
	public HospitalDatabase() {
		try {
			Class.forName("org.postgresql.Driver");
			
			String url = "jdbc:postgresql://localhost:5432/hospital";
			String user = "quantumamar";
			String passwd = "amar";
			
			connection = DriverManager.getConnection(url, user, passwd);
			System.out.println("Connection avec la base de donnée hospital établie");
		}
		catch (Exception e) {
			e.getMessage();
		}
	}
	
	
	// les requêtes d'insertion sur les tables
	public void insertMedecin(Medecin m) {
		String requete = "INSERT INTO medecin VALUES("+m.getMatricule()+", '"+m.getNom()+"')";
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(requete);
		}
		catch(SQLException e) {
			e.getMessage();
		}
	}
	
	public void insertPatient(Patient p) {
		String requete = "INSERT INTO patient VALUES("+p.getNumeroSecuriteSociale()+", '"+p.getNom()+"')";
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(requete);
		}
		catch(SQLException e) {
			e.getMessage();
		}
	}
	
	public void insertConsultation(Consultation c) {
		String requete = "INSERT INTO consultation VALUES("+c.getNumero()+", "+c.getMedecin().getMatricule()+", "
				+ c.getPatientAssiste().getNumeroSecuriteSociale()+ ", NOW() )";
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(requete);
		}
		catch(SQLException e) {
			e.getMessage();
		}
	}
	
	public void insertMedicament(Medicament m) {
		String requete = "INSERT INTO medicament VALUES("+m.getCode()+", '"+m.getLibelle()+"')";
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(requete);
		}
		catch(SQLException e) {
			e.getMessage();
		}
	}
	
	public void insertPrescription(Prescription p) {
		String requete = "INSERT INTO consultation VALUES("+p.getNumero()+", "+p.getConsultation().getNumero()+", "
				+ p.getMedicament().getCode()+ ", "+ p.getNombrePrise() +")";
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(requete);
		}
		catch(SQLException e) {
			e.getMessage();
		}
	}
	
	//les requêtes selects
	public void selectAllMedecin() {
		String requete = "SELECT * FROM medecin";
		try {
			Statement stmt = connection.createStatement();
			ResultSet results = stmt.executeQuery(requete);
			while(results.next()) {
				Fonctionnalites.medecins.add(new Medecin(results.getInt("matricule"), results.getString("nom")));
			}
			results.close();
		}
		catch(SQLException e) {
			e.getMessage();
		}
	}
	
	public void selectAllPatients() {
		String requete = "SELECT * FROM patient";
		try {
			Statement stmt = connection.createStatement();
			ResultSet results = stmt.executeQuery(requete);
			while(results.next()) {
				Fonctionnalites.patients.add(new Patient(results.getInt("noss"), results.getString("nom")));
			}
			results.close();
		}
		catch(SQLException e) {
			e.getMessage();
		}
	}
	
	public void selectAllMedicaments() {
		String requete = "SELECT * FROM medicament";
		try {
			Statement stmt = connection.createStatement();
			ResultSet results = stmt.executeQuery(requete);
			while(results.next()) {
				Fonctionnalites.medicaments.add(new Medicament(results.getInt("code"), results.getString("libelle")));
			}
			results.close();
		}
		catch(SQLException e) {
			e.getMessage();
		}
	}
	
	public void selectAllConsultations() {
		String requete = "SELECT * FROM consultation";
		try {
			Statement stmt = connection.createStatement();
			ResultSet results = stmt.executeQuery(requete);
			while(results.next()) {
				Fonctionnalites.consultations.add(new Consultation(results.getInt("no_consultation"),
						results.getDate("date_consultation"),Fonctionnalites.patients.get(results.getInt("noss")-1) ,
						Fonctionnalites.medecins.get(results.getInt("matricule")-1)));
			}
			results.close();
		}
		catch(SQLException e) {
			e.getMessage();
		}
	}
	
	public void selectAllPrescriptions() {
		String requete = "SELECT * FROM prescription";
		try {
			Statement stmt = connection.createStatement();
			ResultSet results = stmt.executeQuery(requete);
			while(results.next()) {
				Fonctionnalites.prescriptions.add(new Prescription(results.getInt("no_prescription"),
						results.getInt("nbprises"),
						Fonctionnalites.medicaments.get(results.getInt("code")-1), 
						Fonctionnalites.consultations.get(results.getInt("no_consultation")-1)
						));	
			}
			results.close();
		}
		catch(SQLException e) {
			e.getMessage();
		}
	}
	
	
	
}
