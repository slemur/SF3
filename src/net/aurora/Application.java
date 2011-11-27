package net.aurora;

import net.aurora.ui.SimpleFrame;

import javax.swing.*;

/**
 * @author Rick van Biljouw
 *         Date: 18-11-11
 *         Time: 0:16
 */
public class Application {

    public static void main(String[] args) {
        System.load("C:\\Users\\Devel\\Desktop\\opengl32.dll");

        try {
            javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            java.awt.EventQueue.invokeLater(new Runnable() {

                public void run() {
                    new SimpleFrame().setVisible(true);
                }
            });
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(SimpleFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

}
