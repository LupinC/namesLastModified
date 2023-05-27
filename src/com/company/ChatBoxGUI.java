package com.company;

import javax.swing.*;
import java.io.File;
import java.util.Date;

public class ChatBoxGUI extends JFrame {
    private JTextField inputField;
    private Date date;
    private String path;


    public ChatBoxGUI() {
        setTitle("the use of this app is to find files that is modified after a given date, input yyyymmdd and a path, eg, 20230101C:/Users/cy295/OneDrive/Desktop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 100);
        setLocationRelativeTo(null);

        inputField = new JTextField();
        inputField.addActionListener(e -> {
            String userInput = inputField.getText();
            int y = Integer.parseInt(userInput.substring(0,4)) - 1900;
            int m = Integer.parseInt(userInput.substring(4,6)) - 1;
            int d = Integer.parseInt(userInput.substring(6,8));
            path = userInput.substring(8);


            this.date = new Date(y,m,d);

            LastModified a = new LastModified();
            File[] files = a.getFiles(path, date);
            String output = a.writeToFile(files);

            JOptionPane.showMessageDialog(this, a.writeToFile(files));
            inputField.setText("");

            SwingUtilities.invokeLater(() -> {
                CopyableFrame frame = new CopyableFrame(output);
                frame.setSize(1500, 2000);
                frame.setVisible(true);
            });
        });

        add(inputField);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChatBoxGUI gui = new ChatBoxGUI();
            gui.setVisible(true);
        });
    }
}
