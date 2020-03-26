package Vehicule;

import java.io.Serializable;


public class Vehicule implements Serializable {
	
	private String Id;
        private short Assurance;
        private short Proprietaire;
        private short CarteVerte;
	

	public Vehicule(String Id, short Assurance, short Proprietaire, short CarteVerte) {
		super();
		this.Id = Id;
		this.Assurance = Assurance;
		this.Proprietaire = Proprietaire;
		this.CarteVerte = CarteVerte;
	}

	public Vehicule() {
		super();
	}

    public void setAssurance(short Assurance) {
        this.Assurance = Assurance;
    }

    public void setCarteVerte(short CarteVerte) {
        this.CarteVerte = CarteVerte;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public void setProprietaire(short Proprietaire) {
        this.Proprietaire = Proprietaire;
    }

    public short getAssurance() {
        return Assurance;
    }

    public short getCarteVerte() {
        return CarteVerte;
    }

    public String getId() {
        return Id;
    }

    public short getProprietaire() {
        return Proprietaire;
    }
}