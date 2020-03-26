package Traversees;

import java.io.Serializable;
import java.util.Date;


public class Traversees implements Serializable {
	
	private String IdTraversees;
	private Date DateDepart;
	private String PortDepart;
	private String PortArrivee;
	private int Matricule;
        private int Prix;
	

	public Traversees(String IdTraversees, Date DateDepart, String PortDepart,
			String PortArrivee, int Matricule, int Prix) {
		super();
		this.IdTraversees = IdTraversees;
		this.DateDepart = DateDepart;
		this.PortDepart = PortDepart;
		this.PortArrivee = PortArrivee;
		this.Matricule = Matricule;
                this.Prix = Prix;
	}

	public Traversees() {
		super();
	}

    public void setDateDepart(Date DateDepart) {
        this.DateDepart = DateDepart;
    }

    public Date getDateDepart() {
        return DateDepart;
    }

    public void setIdTraversees(String IdTraversees) {
        this.IdTraversees = IdTraversees;
    }

    public String getIdTraversees() {
        return IdTraversees;
    }

    public void setMatricule(int Matricule) {
        this.Matricule = Matricule;
    }

    public int getMatricule() {
        return Matricule;
    }

    public void setPortArrivee(String PortArrivee) {
        this.PortArrivee = PortArrivee;
    }

    public String getPortArrivee() {
        return PortArrivee;
    }

    public void setPortDepart(String PortDepart) {
        this.PortDepart = PortDepart;
    }

    public String getPortDepart() {
        return PortDepart;
    }

    public void setPrix(int Prix) {
        this.Prix = Prix;
    }

    public int getPrix() {
        return Prix;
    }
    
    
}