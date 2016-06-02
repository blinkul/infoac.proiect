package abonamente;

import abonamente.gui.AgendaFrame;
import java.io.Serializable;

public class Contact implements Comparable, Serializable{
    private Abonat abonat;
    private NrTel nrTel;


    public Contact(Abonat abonat, NrTel nrTel) {
        this.abonat = abonat;
        this.nrTel = nrTel;

    }

    public Contact() {
    }  

    public Abonat getAbonat() {
        return abonat;
    }

    public void setAbonat(Abonat abonat) {
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
