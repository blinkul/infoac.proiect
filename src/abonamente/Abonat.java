package abonamente;

import abonamente.gui.AgendaFrame;
import java.io.Serializable;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Abonat implements Serializable {

    private String cnp;
    private String nume, prenume;
    private String id;

    public Abonat(String cnp, String nume, String prenume){        
            setCnp(cnp);
            setNume(nume);
            setPrenume(prenume);
            this.id = String.valueOf(AgendaFrame.getNumberOfRows());
    }

    public Abonat() {
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp){
        if (!(Pattern.matches("^[0-9]+$", cnp))) {
            
        } else {
            this.cnp = cnp;
        }
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume){
        if (!(Pattern.matches("^[a-zA-Z]+$", nume))) {
            
        } else {
            this.nume = nume;
        }
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume){
        if (!(Pattern.matches("^[a-zA-Z]+$", prenume))) {
            
        } else {
            this.prenume = prenume;
        }
    }

    public String getID() {
        return id;
    }
}
