package abonamente;

import abonamente.exceptii_custom.*;
import abonamente.gui.AgendaFrame;
import java.io.Serializable;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Abonat implements Serializable {

    private String cnp;
    private String nume, prenume;
    private String id;

    public Abonat(String cnp, String nume, String prenume) throws ExceptieInstantiereAbonat{
        try {
            setCnp(cnp);
            setNume(nume);
            setPrenume(prenume);
            this.id = String.valueOf(AgendaFrame.getNumberOfRows());
        } catch (ExceptieValidareLitere ex) {
            JOptionPane.showMessageDialog(null, "Va rugam sa folositi litere (a-z sau A-Z)");
            throw new ExceptieInstantiereAbonat();
        } catch (ExceptieValidareNumere ex) {
            JOptionPane.showMessageDialog(null, "Va rugam sa folositi cifre (0-9)");
            throw new ExceptieInstantiereAbonat();
        }
    }

    public Abonat() {
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) throws ExceptieValidareNumere {
        if (!(Pattern.matches("^[0-9]+$", cnp))) {
            throw new ExceptieValidareNumere();
        } else {
            this.cnp = cnp;
        }
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) throws ExceptieValidareLitere {
        if (!(Pattern.matches("^[a-zA-Z]+$", nume))) {
            throw new ExceptieValidareLitere();
        } else {
            this.nume = nume;
        }
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) throws ExceptieValidareLitere {
        if (!(Pattern.matches("^[a-zA-Z]+$", prenume))) {
            throw new ExceptieValidareLitere();
        } else {
            this.prenume = prenume;
        }
    }

    public String getID() {
        return id;
    }
}
