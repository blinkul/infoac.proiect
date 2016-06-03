package abonamente.gui;

import abonamente.comparator.ComparatorCNP;
import abonamente.comparator.ComparatorNume;
import abonamente.comparator.ComparatorPrenume;
import abonamente.Contact;
import abonamente.comparator.ComparatorID;
import abonamente.controller.ContactController;
import abonamente.controller.FileChooserController;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import jdk.nashorn.internal.scripts.JO;

/**
 *
 * @author ciprian.anghel
 */
public class AgendaFrame extends javax.swing.JFrame {

    List<Contact> contacte;
    DefaultTableModel dtm;
    private static int ID=1;

    public AgendaFrame() {
        initComponents();
        buttonGroup1.add(radioSortareDupaNume);
        buttonGroup1.add(radioSortareDupaPrenume);
        buttonGroup1.add(radioSortareDupaCNP);
        buttonGroup1.add(radioSortareDupaID);
        contacte = new ArrayList<>();
        dtm = new DefaultTableModel();
        dtm.setColumnIdentifiers(new String[]{"ID","Nume", "Prenume", "CNP", "Numar de telefon"});
        tabelContacte.setModel(dtm);
        tabelContacte.getColumnModel().getColumn(0).setPreferredWidth(10);
        afisareContacte();
        addListeners();
        //*******************************************************************************
        menuItemSave.setEnabled(true);  //TEMPORAR PANA TERMIN APLICATIA - PENTRU TESTARE
        menuItemOpen.setEnabled(true);  //TEMPORAR PANA TERMIN APLICATIA - PENTRU TESTARE
        //*******************************************************************************
    }

    private void afisareContacte() {
        if (dtm.getRowCount() > 0) {
            for (int i = dtm.getRowCount() - 1; i > -1; i--) {
                dtm.removeRow(i);
            }
        }

        for (Contact contact : contacte) {
            dtm.addRow(new String[]{
                contact.getAbonat().getID(),                
                contact.getAbonat().getNume(),
                contact.getAbonat().getPrenume(),
                contact.getAbonat().getCnp(),
                contact.getNrTel().getNr()
            });
        }
    }

    private void addListeners() {
        butonGolesteLista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contacte.clear();
                afisareContacte();
            }
        });
        butonInsereazaContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adaugaContact();
            }
        });
        butonSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (radioSortareDupaNume.isSelected()) {
                    ContactController.getInstance().sortare(contacte, new ComparatorNume());
                    afisareContacte();
                } else if (radioSortareDupaPrenume.isSelected()) {
                    ContactController.getInstance().sortare(contacte, new ComparatorPrenume());
                    afisareContacte();
                } else if(radioSortareDupaCNP.isSelected()){
                    ContactController.getInstance().sortare(contacte, new ComparatorCNP());
                    afisareContacte();
                } else {
                    ContactController.getInstance().sortare(contacte, new ComparatorID());
                    afisareContacte();
                }
            }
        });
        
        menuItemOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ContactController openFileChooser = ContactController.getInstance();
                try {
                    contacte = openFileChooser.openContact();
                } catch (IOException ex) {
                    Logger.getLogger(AgendaFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AgendaFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                afisareContacte();                    
            }
        });
        menuItemSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileChooserController saveFileChooser = FileChooserController.getInstance();
                try {
                    saveFileChooser.fileSaver(contacte);
                } catch (IOException ex) {
                    Logger.getLogger(AgendaFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }
        });
        
        /**
         * Dupa validarea codului de inregistrare optiunile Open si Save devin active iar optiunea de Inregistrare devine inactiva.
         * Codul de validare = cisco
         */
        menuItemInregistrare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(new JPanel() ,"Introduceti codul de validare!");
                if(input.equals("cisco")){
                   menuItemSave.setEnabled(true);
                   menuItemOpen.setEnabled(true);
                   menuItemInregistrare.setEnabled(false);
                   JOptionPane.showMessageDialog(new JPanel(), "Codul a fost validat cu succes. Acum beneficiati de optiunile Open si Save");
                }else{
                   JOptionPane.showMessageDialog(new JPanel(),"Codul introdus nu este corect.\nAsigurati-va ca nu aveti CapsLock activ.", "Codul este incorect!", JOptionPane.WARNING_MESSAGE);
                }                              
            }
        });
        menuItemAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(new JPanel(), "Aceasta aplicatie a fost creata de Anghel Ciprian Liviu ca proiect pentru cursul de Java din cadrul InfoAcademy.");
            }
        });
        menuItemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = JOptionPane.showConfirmDialog(new JPanel(),"Doriti sa inchideti aplicatia?","Confirmare",JOptionPane.YES_NO_OPTION);
                if(i==JOptionPane.YES_OPTION){
                    System.exit(0);
                }else{return;}
                
            }
        });
        butonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tabelContacte.isRowSelected(tabelContacte.getSelectedRow())){

                JPanel editPanel = new JPanel();
                editPanel.setLayout(new GridLayout(4,2,5,5));
                
                editPanel.add(new JLabel("Nume:\t"));
                editPanel.add(new JTextField((String) tabelContacte.getModel().getValueAt(tabelContacte.getSelectedRow(), 1)));
                editPanel.add(new JLabel("Prenume:\t"));
                editPanel.add(new JTextField((String) tabelContacte.getModel().getValueAt(tabelContacte.getSelectedRow(), 2)));
                editPanel.add(new JLabel("CNP:\t"));
                editPanel.add(new JTextField((String) tabelContacte.getModel().getValueAt(tabelContacte.getSelectedRow(), 3)));
                editPanel.add(new JLabel("Telefon:\t"));
                editPanel.add(new JTextField((String) tabelContacte.getModel().getValueAt(tabelContacte.getSelectedRow(), 4)));
               
                
                Object[] optiuni = {"Yes", "Cancel"};
                int i = JOptionPane.showOptionDialog(null, editPanel, "Editare",JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, optiuni, optiuni[1]);
                
                    if(i==JOptionPane.YES_OPTION){
                         /** tabelContacte.getModel().setValueAt();**/
                    }else{return;}
                
                }else{
                    JOptionPane.showMessageDialog(new JPanel(), "Pentru editare este necesar sa selectati randul dorit!", "Informational", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                    
            }
        });
        
    }

    public void adaugaContact() {
        String nume = numeTextField.getText();
        String prenume = prenumeTextField.getText();
        String cnp = cnpTextField.getText();
        String numar = nrTextField.getText();
        incrementID();
        Contact contact = ContactController.getInstance().createContact(nume, prenume, cnp, numar);
        contacte.add(contact);
        afisareContacte();
        numeTextField.setText("");
        prenumeTextField.setText("");
        cnpTextField.setText("");
        nrTextField.setText("");
        
    }
    
    //Citeste numarul de randuri din tabel si incrementeaza valoarea cu o unitate
    //Populeaza campul static ID cand metoda adaugaContact() este utilizata (la apasarea butonului "Inserare Contact")
    private void incrementID(){
        ID = tabelContacte.getRowCount()+1;
    }
    
    //Metoda folosita in constructorul obiectului Abonat, pentru a putea incrementa fiecare instantiere.
    //Foloseste valoarea campului static ID, populat de catre metoda incrementID(). 
    public static int getNumberOfRows(){
        int rows = ID;
        return rows;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelMare = new javax.swing.JPanel();
        paneCenter = new javax.swing.JPanel();
        butonSort = new javax.swing.JButton();
        radioSortareDupaNume = new javax.swing.JRadioButton();
        radioSortareDupaPrenume = new javax.swing.JRadioButton();
        radioSortareDupaCNP = new javax.swing.JRadioButton();
        numeTextField = new javax.swing.JTextField();
        prenumeTextField = new javax.swing.JTextField();
        cnpTextField = new javax.swing.JTextField();
        nrTextField = new javax.swing.JTextField();
        tfNume = new javax.swing.JLabel();
        tfPrenume = new javax.swing.JLabel();
        tfCNP = new javax.swing.JLabel();
        nrTelTextField = new javax.swing.JLabel();
        butonInsereazaContact = new javax.swing.JButton();
        jScrollPane = new javax.swing.JScrollPane();
        tabelContacte = new javax.swing.JTable();
        radioSortareDupaID = new javax.swing.JRadioButton();
        butonGolesteLista = new javax.swing.JButton();
        butonEdit = new javax.swing.JButton();
        paneWest = new javax.swing.JPanel();
        labelReclamaWest = new javax.swing.JLabel();
        paneEast = new javax.swing.JPanel();
        labelReclamaEast = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        menuItemOpen = new javax.swing.JMenuItem();
        menuItemSave = new javax.swing.JMenuItem();
        menuItemSeparator = new javax.swing.JPopupMenu.Separator();
        menuItemExit = new javax.swing.JMenuItem();
        menuHelp = new javax.swing.JMenu();
        menuItemInregistrare = new javax.swing.JMenuItem();
        menuItemSeparator2 = new javax.swing.JPopupMenu.Separator();
        menuItemAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agenda Telefonica");
        setMinimumSize(new java.awt.Dimension(1000, 400));
        setResizable(false);

        panelMare.setRequestFocusEnabled(false);
        panelMare.setLayout(new java.awt.BorderLayout());

        paneCenter.setForeground(new java.awt.Color(255, 204, 0));
        paneCenter.setAlignmentX(0.0F);
        paneCenter.setAlignmentY(0.0F);
        paneCenter.setMinimumSize(new java.awt.Dimension(600, 400));
        paneCenter.setName(""); // NOI18N

        butonSort.setText("Sort");
        butonSort.setMaximumSize(null);
        butonSort.setMinimumSize(null);
        butonSort.setPreferredSize(new java.awt.Dimension(10, 23));
        butonSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butonSortActionPerformed(evt);
            }
        });

        radioSortareDupaNume.setText("Sortare dupa nume");

        radioSortareDupaPrenume.setText("Sortare dupa prenume");

        radioSortareDupaCNP.setText("Sortare dupa CNP");

        numeTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        numeTextField.setAutoscrolls(false);

        tfNume.setText("Nume");

        tfPrenume.setText("Prenume");

        tfCNP.setText("CNP");

        nrTelTextField.setText("Numar Telefon");

        butonInsereazaContact.setText("Inserare Contact");
        butonInsereazaContact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butonInsereazaContactActionPerformed(evt);
            }
        });

        jScrollPane.setMinimumSize(new java.awt.Dimension(200, 400));
        jScrollPane.setName(""); // NOI18N
        jScrollPane.setPreferredSize(new java.awt.Dimension(400, 402));

        tabelContacte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nume", "Prenume", "CNP", "Numar de Telefon"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tabelContacte.setAlignmentX(0.0F);
        tabelContacte.setAlignmentY(0.0F);
        tabelContacte.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tabelContacte.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane.setViewportView(tabelContacte);

        radioSortareDupaID.setText("Sortare dupa ID");

        butonGolesteLista.setText("Goleste Lista");

        butonEdit.setText("Editeaza rand selectat");

        javax.swing.GroupLayout paneCenterLayout = new javax.swing.GroupLayout(paneCenter);
        paneCenter.setLayout(paneCenterLayout);
        paneCenterLayout.setHorizontalGroup(
            paneCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneCenterLayout.createSequentialGroup()
                .addGroup(paneCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneCenterLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(paneCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(butonSort, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(radioSortareDupaPrenume)
                            .addComponent(radioSortareDupaNume)
                            .addGroup(paneCenterLayout.createSequentialGroup()
                                .addGroup(paneCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(nrTextField)
                                    .addComponent(cnpTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(numeTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(prenumeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(paneCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nrTelTextField)
                                    .addComponent(tfPrenume)
                                    .addComponent(tfNume)
                                    .addComponent(tfCNP)))
                            .addComponent(radioSortareDupaCNP)
                            .addComponent(butonInsereazaContact, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(radioSortareDupaID)))
                    .addComponent(butonGolesteLista, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(butonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE))
        );
        paneCenterLayout.setVerticalGroup(
            paneCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfNume))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paneCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prenumeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfPrenume))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paneCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cnpTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfCNP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paneCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nrTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nrTelTextField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(butonInsereazaContact)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(radioSortareDupaID)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioSortareDupaNume)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioSortareDupaPrenume)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioSortareDupaCNP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(butonSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(butonGolesteLista)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(butonEdit)
                .addGap(24, 24, 24))
            .addGroup(paneCenterLayout.createSequentialGroup()
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panelMare.add(paneCenter, java.awt.BorderLayout.CENTER);

        paneWest.setToolTipText("");
        paneWest.setAlignmentX(0.0F);
        paneWest.setAlignmentY(0.0F);
        paneWest.setMinimumSize(new java.awt.Dimension(100, 400));
        paneWest.setName(""); // NOI18N
        paneWest.setPreferredSize(new java.awt.Dimension(100, 400));

        labelReclamaWest.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelReclamaWest.setIcon(new javax.swing.ImageIcon(getClass().getResource("/abonamente/imagini/reclama/reclama1.jpg"))); // NOI18N
        labelReclamaWest.setAlignmentY(0.0F);
        labelReclamaWest.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout paneWestLayout = new javax.swing.GroupLayout(paneWest);
        paneWest.setLayout(paneWestLayout);
        paneWestLayout.setHorizontalGroup(
            paneWestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelReclamaWest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        paneWestLayout.setVerticalGroup(
            paneWestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneWestLayout.createSequentialGroup()
                .addComponent(labelReclamaWest)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panelMare.add(paneWest, java.awt.BorderLayout.WEST);

        paneEast.setAlignmentX(0.0F);
        paneEast.setAlignmentY(0.0F);
        paneEast.setMinimumSize(new java.awt.Dimension(100, 400));
        paneEast.setPreferredSize(new java.awt.Dimension(100, 400));

        labelReclamaEast.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelReclamaEast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/abonamente/imagini/reclama/reclama2.jpg"))); // NOI18N
        labelReclamaEast.setAlignmentY(0.0F);

        javax.swing.GroupLayout paneEastLayout = new javax.swing.GroupLayout(paneEast);
        paneEast.setLayout(paneEastLayout);
        paneEastLayout.setHorizontalGroup(
            paneEastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneEastLayout.createSequentialGroup()
                .addComponent(labelReclamaEast)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        paneEastLayout.setVerticalGroup(
            paneEastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneEastLayout.createSequentialGroup()
                .addComponent(labelReclamaEast)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panelMare.add(paneEast, java.awt.BorderLayout.EAST);

        menuFile.setText("File");

        menuItemOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        menuItemOpen.setText("Open");
        menuItemOpen.setToolTipText("");
        menuItemOpen.setEnabled(false);
        menuFile.add(menuItemOpen);

        menuItemSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        menuItemSave.setText("Save");
        menuItemSave.setEnabled(false);
        menuFile.add(menuItemSave);
        menuFile.add(menuItemSeparator);

        menuItemExit.setText("Exit");
        menuFile.add(menuItemExit);

        jMenuBar1.add(menuFile);

        menuHelp.setText("Help");

        menuItemInregistrare.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        menuItemInregistrare.setText("Inregistrare");
        menuItemInregistrare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemInregistrareActionPerformed(evt);
            }
        });
        menuHelp.add(menuItemInregistrare);
        menuHelp.add(menuItemSeparator2);

        menuItemAbout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        menuItemAbout.setText("About");
        menuHelp.add(menuItemAbout);

        jMenuBar1.add(menuHelp);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panelMare, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMare, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void butonInsereazaContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butonInsereazaContactActionPerformed
        //am creat metoda addListeners()
        //nu am stiut cum sa mai sterg aceasta metoda
    }//GEN-LAST:event_butonInsereazaContactActionPerformed

    private void butonSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butonSortActionPerformed
        //am creat metoda addListeners()
        //nu am stiut cum sa mai sterg aceasta metoda
    }//GEN-LAST:event_butonSortActionPerformed

    private void menuItemInregistrareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemInregistrareActionPerformed
        //am creat metoda addListeners()
        //nu am stiut cum sa mai sterg aceasta metoda
    }//GEN-LAST:event_menuItemInregistrareActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AgendaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgendaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgendaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgendaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AgendaFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butonEdit;
    private javax.swing.JButton butonGolesteLista;
    private javax.swing.JButton butonInsereazaContact;
    private javax.swing.JButton butonSort;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField cnpTextField;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JLabel labelReclamaEast;
    private javax.swing.JLabel labelReclamaWest;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenu menuHelp;
    private javax.swing.JMenuItem menuItemAbout;
    private javax.swing.JMenuItem menuItemExit;
    private javax.swing.JMenuItem menuItemInregistrare;
    private javax.swing.JMenuItem menuItemOpen;
    private javax.swing.JMenuItem menuItemSave;
    private javax.swing.JPopupMenu.Separator menuItemSeparator;
    private javax.swing.JPopupMenu.Separator menuItemSeparator2;
    private javax.swing.JLabel nrTelTextField;
    private javax.swing.JTextField nrTextField;
    private javax.swing.JTextField numeTextField;
    private javax.swing.JPanel paneCenter;
    private javax.swing.JPanel paneEast;
    private javax.swing.JPanel paneWest;
    private javax.swing.JPanel panelMare;
    private javax.swing.JTextField prenumeTextField;
    private javax.swing.JRadioButton radioSortareDupaCNP;
    private javax.swing.JRadioButton radioSortareDupaID;
    private javax.swing.JRadioButton radioSortareDupaNume;
    private javax.swing.JRadioButton radioSortareDupaPrenume;
    private javax.swing.JTable tabelContacte;
    private javax.swing.JLabel tfCNP;
    private javax.swing.JLabel tfNume;
    private javax.swing.JLabel tfPrenume;
    // End of variables declaration//GEN-END:variables

}
