package abonamente.comparator;

import abonamente.Contact;
import abonamente.exceptii_custom.ExceptieCnpDuplicat;
import java.util.Comparator;
import javax.swing.JOptionPane;

public class ComparatorCNP implements Comparator<Contact>{

    @Override
    public int compare(Contact o1, Contact o2) {
        return o1.getAbonat().getCnp().compareTo(o2.getAbonat().getCnp());
    }
    
    public boolean compareCNP(String cnp1, String cnp2) throws ExceptieCnpDuplicat{
        if(cnp1.equals(cnp2)){
            throw new ExceptieCnpDuplicat();
        }else{
            return false;
        }
    }
    
}
