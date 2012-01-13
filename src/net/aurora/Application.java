package net.aurora;

import net.aurora.external.opengl.OpenGL;
import net.aurora.ui.UI;

import javax.swing.*;
import java.io.File;

/**
 * @author Rick van Biljouw
 *         Date: 18-11-11
 *         Time: 0:16
 */
public class Application {
    public static OpenGL glInstance;
    private static UI ui;

    public static void main(String[] args) {
        try {
            javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            JPopupMenu.setDefaultLightWeightPopupEnabled(false);

            java.awt.EventQueue.invokeLater(new Runnable() {

                public void run() {
                   // System.load(new File("C:\\Users\\Devel\\Desktop\\opengl32.dll").getAbsolutePath());
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
