package abonamente;

import abonamente.exceptii_custom.ExceptieInstantiereNumar;
import abonamente.exceptii_custom.ExceptieValidareNumere;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class NrTel implements Serializable{
    
    private String nrTel;

    public NrTel(String nrTel) {
        try {
            setNr(nrTel);
        } catch (ExceptieInstantiereNumar ex) {
            JOptionPane.showMessageDialog(null, "Va rugam sa folositi cifre (0-9)");
        }
    }
    
    public void setNr(String nrTel) throws ExceptieInstantiereNumar{
        if (!(Pattern.matches("^[0-9]+$", nrTel))) {
            throw new ExceptieInstantiereNumar();
        } else {
             this.nrTel=nrTel;
        }
        this.nrTel=nrTel;
    }

    public String getNr() {
        nrTel=this.nrTel;
        return nrTel;
    }
    
}
