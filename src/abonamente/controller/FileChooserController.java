/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abonamente.controller;

import abonamente.Contact;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.JFileChooser;

/**
 *
 * @author ciprian.anghel
 */
public class FileChooserController {
    
    private static FileChooserController instance;
    /**
     * Metodele fileSaver si fileOpener creaza fereastra obiectului JFileChooser in momentul in care apasam Open sau Save.
     * fileSaver se foloseste de metoda saveContact din clasa ContactController pentru a salva obiectele de tip Contact intr-un fisier de tip .txt
     */
    public void fileSaver(List<Contact> contact) throws IOException{
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("."));
        fc.setDialogTitle("Salveaza Fisier");
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fc.showSaveDialog(null);
        ContactController.getInstance().saveContact(contact, fc.getSelectedFile());
    }
    public File fileOpener(){
        ContactController openFileChooser = ContactController.getInstance();
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("."));
        fc.setDialogTitle("Deschide Fisier");
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fc.showOpenDialog(null);
        return fc.getSelectedFile();
    }
    public static FileChooserController getInstance(){
        if(instance == null)
        instance = new FileChooserController();
        return instance;
    }
}
