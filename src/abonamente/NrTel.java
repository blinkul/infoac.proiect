package abonamente;

import abonamente.exceptii_custom.ExceptieFormatTelefon;
import abonamente.exceptii_custom.ExceptieTelefonNumarCaractere;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class NrTel implements Serializable{
    
    private String nrTel;

    public NrTel(String nrTel) throws ExceptieFormatTelefon, ExceptieTelefonNumarCaractere {
//        if (!(Pattern.matches("^[0-9]+$", nrTel))) {
//           throw new ExceptieFormatTelefon(); 
//        }else if(nrTel.length() != 10){
//           throw new ExceptieTelefonNumarCaractere(); 
//        }else{
           setNr(nrTel);
//        }
    }
    
    public void setNr(String nrTel) throws ExceptieFormatTelefon, ExceptieTelefonNumarCaractere{
        if (!(Pattern.matches("^[0-9]+$", nrTel))) {
           throw new ExceptieFormatTelefon(); 
        }else if(nrTel.length() != 10){
           throw new ExceptieTelefonNumarCaractere(); 
        }else{
        this.nrTel=nrTel;
        }
    }

    public String getNr() {
        nrTel=this.nrTel;
        return nrTel;
    }
    
}
