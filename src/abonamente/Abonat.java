package abonamente;

import abonamente.gui.AgendaFrame;
import java.io.Serializable;

public class Abonat implements Serializable{
    private String cnp;
    private String nume, prenume;
    private String id;

    public Abonat(String cnp, String nume, String prenume) {
        this.cnp = cnp;
        this.nume = nume;
        this.prenume = prenume;
        this.id = String.valueOf(AgendaFrame.getNumberOfRows());
    }

    public Abonat() {
    }    
        
    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        //Se accepta si litere pentru cazul altor tari
        //sa implementeze exceptii
        this.cnp = cnp;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        //sa implementeze exceptii
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        //sa implementeze exceptii
        this.prenume = prenume;
    }

    public String getID(){
        return id;
    }
}
