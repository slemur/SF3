package net.aurora.ui;

import net.aurora.bot.Bot;
import net.aurora.game.Frame;
import net.aurora.game.Server;
import net.aurora.jni.NLibrary;
import net.aurora.loader.AppletLoader;
import net.aurora.loader.AppletLoaderContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Rick van Biljouw
 *         Date: 19-11-11
 *         Time: 21:11
 */
public class SimpleFrame extends JFrame {
    private JButton button = new JButton("Toggle wireframes");

    public SimpleFrame() {
        this.setTitle("hello");
        this.setSize(762, 535);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Bot bot = new Bot();
        add(bot.getLoader().getApplet());

        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                NLibrary.toggleWireframe();
            }
        });

        this.add(button, BorderLayout.SOUTH);
        this.setVisible(true);

    }
}
