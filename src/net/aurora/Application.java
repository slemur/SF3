package net.aurora;

import net.aurora.ui.UI;

import javax.swing.*;
import java.io.File;

/**
 * @author Rick van Biljouw
 *         Date: 18-11-11
 *         Time: 0:16
 */
public class Application {
    private static UI ui;

    public static void main(String[] args) {
        //Bootstrap our hacked opengl dll
        System.load(new File("opengl32.dll").getAbsolutePath());

        try {
            javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            JPopupMenu.setDefaultLightWeightPopupEnabled(false);

            java.awt.EventQueue.invokeLater(new Runnable() {

                public void run() {
                    (ui = new UI()).setVisible(true);
                }

            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static UI getUI() {
        return ui;
    }
}
