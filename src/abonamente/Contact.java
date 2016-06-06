package abonamente;

import abonamente.exceptii_custom.*;
import abonamente.gui.AgendaFrame;
import java.io.Serializable;
import java.util.regex.Pattern;

public class Contact implements Comparable, Serializable{
    private Abonat abonat;
    private NrTel nrTel;


    public Contact(Abonat abonat, NrTel nrTel){
        setAbonat(abonat);
        setNrTel(nrTel);
    }

    public Contact() {
    }  

    public Abonat getAbonat() {
        return abonat;
    }

    public void setAbonat(Abonat abonat){
        this.abonat = abonat;
    }

    public NrTel getNrTel() {
        return nrTel;
    }

    public void setNrTel(NrTel nrTel) {
        this.nrTel = nrTel;
    }

    @Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return this.getAbonat().getNume() + " " + this.getAbonat().getPrenume()+ " " + this.getAbonat().getCnp(); //To change body of generated methods, choose Tools | Templates.
    }
}
