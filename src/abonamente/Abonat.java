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

    public Abonat(String nume, String prenume, String cnp) throws ExceptieCnpNumarCaractere, ExceptieFormatNume, ExceptieFormatPrenume, ExceptieFormatCnp{
        if(cnp.length() != 13){
            throw new ExceptieCnpNumarCaractere();
        }else if(!(Pattern.matches("^[a-zA-Z]+$", nume))){
            throw new ExceptieFormatNume();
        }else if(!(Pattern.matches("^[a-zA-Z]+$", prenume))){
            throw new ExceptieFormatPrenume();
        }else if(!(Pattern.matches("^[0-9]+$", cnp))){
            throw new ExceptieFormatCnp();
        }else{
            setCnp(cnp);
            setNume(nume);
            setPrenume(prenume);
            this.id = String.valueOf(AgendaFrame.getNumberOfRows());
        }
    }

    public Abonat() {
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) throws ExceptieCnpNumarCaractere, ExceptieFormatCnp{
        if(cnp.length() != 13){
            throw new ExceptieCnpNumarCaractere();
        }else if(!(Pattern.matches("^[0-9]+$", cnp))){
            throw new ExceptieFormatCnp();
        }else{
        this.cnp = cnp;
        }
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) throws ExceptieFormatNume{
        if(!(Pattern.matches("^[a-zA-Z]+$", nume))){
            throw new ExceptieFormatNume();
        }else{
        this.nume = nume;
        }
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume){
        this.prenume = prenume;
    }

    public String getID() {
        return id;
    }
}
