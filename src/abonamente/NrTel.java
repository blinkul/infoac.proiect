package abonamente;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class NrTel implements Serializable{
    
    private String nrTel;

    public NrTel(String nrTel) {
        
            setNr(nrTel);
      
    }
    
    public void setNr(String nrTel){
        if (!(Pattern.matches("^[0-9]+$", nrTel))) {
            
        }else if(nrTel.length() != 10){
            
        }else{
        this.nrTel=nrTel;
        }
    }

    public String getNr() {
        nrTel=this.nrTel;
        return nrTel;
    }
    
}
