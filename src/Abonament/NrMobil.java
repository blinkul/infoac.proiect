package Abonament;

public class NrMobil implements NrTel{
    
    String nrTel;
    
    @Override
    public void setNr(String nrTel) {
        //sa accepte numai format de 10 caractere
        //sa nu accepte alteceva decat cifre
        //sa se formateze 0726 328 982
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNr() {
        nrTel="Neimplementat";
        return nrTel;
    }
    
}
