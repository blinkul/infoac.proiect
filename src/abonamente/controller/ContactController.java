
package abonamente.controller;
import abonamente.Abonat;
import abonamente.Contact;
import abonamente.NrMobil;
import abonamente.NrTel;
import abonamente.gui.AgendaFrame;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.print.Collation;
import javax.swing.JFileChooser;


public class ContactController {
    
    private static ContactController instance;

    private ContactController() {
    }
    
    public void saveContact(List<Contact> contact, File file) throws IOException{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file)); //File chooser
        oos.writeObject(contact);
    }
    public List<Contact> openContact(File file) throws IOException, ClassNotFoundException{
        List<Contact> contact =  new ArrayList<>();
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        contact = (List<Contact>)ois.readObject();
        return contact;
    }
    
    public Contact createContact(String nume, String prenume, String cnp, String nrTel){
        Abonat abonat = new Abonat(cnp, nume, prenume);
        NrTel nr = new NrMobil(nrTel);
        Contact contact = new Contact(abonat, nr);
        return contact;
    }
    
    //polimorfism sortare
    public List<Contact> sortare(List<Contact> contacte, Comparator<Contact> comparator){
        Collections.sort(contacte, comparator);
        return contacte;
    }
    

    public void fileSaver(List<Contact> contact) throws IOException{
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("./build/classes/abonamente/saves"));
        fc.setDialogTitle("Salveaza Fisier");
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fc.showSaveDialog(null);
        saveContact(contact, fc.getSelectedFile());
    }

    
    public static ContactController getInstance(){
        if(instance == null)
            instance = new ContactController();
        return instance;
    }
}
