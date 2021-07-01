package TD1;

public class Medicament {
	//attributs
	private int code;
	private String libelle;	
	
	//Constructeurs
	public Medicament(int code, String libelle) {
		this.code = code;
		this.libelle = new String(libelle);
	}

	public Medicament(Medicament medicament) {
		this(medicament.code, medicament.libelle);
	}
	
	//getters et les setters
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	@Override
	public String toString() {
		return "Medicament [code=" + code + ", libelle=" + libelle + "]";
	}
	
	
}
