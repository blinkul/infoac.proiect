package abonamente.comparator;

import abonamente.Contact;
import java.util.Comparator;

public class ComparatorID implements Comparator<Contact>{
    
    @Override
    public int compare(Contact o1, Contact o2) {
        return o1.getAbonat().getID().compareTo(o2.getAbonat().getID());
    }
}

