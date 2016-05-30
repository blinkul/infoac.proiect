package Abonament;

import java.io.Serializable;

public class Contact implements Comparable, Serializable{
    Abonat abonat;
    NrTel nrTel;

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


    
    
}
