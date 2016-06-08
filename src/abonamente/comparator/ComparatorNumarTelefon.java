package abonamente.comparator;

import abonamente.Contact;
import abonamente.exceptii_custom.ExceptieNumarTelefonDuplicat;
import java.util.Comparator;

public class ComparatorNumarTelefon implements Comparator<Contact> {

    @Override
    public int compare(Contact o1, Contact o2) {
        return o1.getNrTel().getNr().compareTo(o2.getNrTel().getNr());
    }

    public boolean compareTelefon(String numar1, String numar2) throws ExceptieNumarTelefonDuplicat {
        if (numar1.equals(numar2)) {
            throw new ExceptieNumarTelefonDuplicat();
        } else {
            return false;
        }
    }
}
