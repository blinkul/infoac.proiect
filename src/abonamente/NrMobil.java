package abonamente;

import java.io.Serializable;

public class NrMobil implements NrTel, Serializable{
    
    String nrTel;

    public NrMobil(String nrTel) {
        this.nrTel = nrTel;
    }
    
    @Override
    public void setNr(String nrTel) {
        //sa accepte numai format de 10 caractere
        //sa nu accepte alteceva decat cifre
        //sa se formateze 0726 328 982
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNr() {
        nrTel=this.nrTel;
        return nrTel;
    }
    
}
