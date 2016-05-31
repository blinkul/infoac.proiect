package abonamente;

public class NrFix implements NrTel{

    @Override
    public void setNr(String nrTel) {
        //sa accepte numai format de 10 caractere
        //sa nu accepte alteceva decat cifre
        //sa se formateze (021) 632 89 82
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNr() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
