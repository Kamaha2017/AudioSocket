package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Elereman on 17.07.2017.
 */
public class ServerUI extends JFrame {
    private JPanel rootPanel;
    private JButton applyButton;
    private JTextField commandField;
    private JTextArea console;
    private JScrollBar scrollBar1;

    ServerUI() {
        setContentPane(rootPanel);
        setSize(600, 430);
        console.setEditable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        console.setLineWrap(true);
        console.setWrapStyleWord(true);
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printOnConsole(commandField.getText());
                applyLine(commandField.getText());
                commandField.setText("");
            }
        });
    }

    public static void main(String[] args) {
        new ServerUI();
    }


    public void printOnConsole(String string) {
        console.append(string);
    }

    private void applyLine(String line) {

    }
}
