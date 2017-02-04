package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Created by Krasimir on 4.2.2017 г..
 */
public class GameMenu extends JMenu {
    public GameMenu() {
        super("Game");
        JMenuItem startGameMI = new JMenuItem("Start", 'S');
        startGameMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        JMenuItem pauseMI = new JMenuItem("Pause", 'P');
        pauseMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
        JMenuItem quitMI = new JMenuItem("Quit");
       // startGameMI.addActionListener(new ActionListener() {
       //     public void actionPerformed(ActionEvent e) {
       //         panel.start();
       //     }
       // });
       // pauseMI.addActionListener(new ActionListener() {
       //     public void actionPerformed(ActionEvent e) {
       //         panel.pause();
       //     }
       // });
        quitMI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(startGameMI);
        add(pauseMI);
        add(quitMI);
    }
}
