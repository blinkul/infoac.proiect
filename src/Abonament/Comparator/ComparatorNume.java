package Abonament.Comparator;

import Abonament.Contact;
import java.util.Comparator;

public class ComparatorNume implements Comparator<Contact>{

    @Override
    public int compare(Contact o1, Contact o2) {
        return o1.getAbonat().getNume().compareTo(o2.getAbonat().getNume());
    }
    
}