/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abonamente.controller;

import java.util.List;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author ciprian.anghel
 */
public class ReclameTask extends TimerTask {

    List<JLabel> listaLabel;

    public ReclameTask(){
        
    }
    
    public ReclameTask(List<JLabel> listaLabel) {
        this();
        this.listaLabel = listaLabel;
    }

    @Override
    public void run() {
        for (JLabel label : listaLabel) {
            label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/abonamente/imagini/reclama/cola1.jpg")));
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ReclameTask.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (JLabel label : listaLabel) {
            label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/abonamente/imagini/reclama/cola2.jpg")));
        }
    }
}
