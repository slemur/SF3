package net.aurora.ui;

import net.aurora.bot.Bot;

import javax.swing.*;

/**
 *
 * @author Devel
 */
public class SimpleFrame extends javax.swing.JFrame {

    /** Creates new form SimpleFrame */
    public SimpleFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Aurora Gameclient");
        setSize(768, 506);
        this.add(new Bot().getApplet());
        setVisible(true);
    }

}
