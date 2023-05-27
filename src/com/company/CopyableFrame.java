package com.company;

import javax.swing.*;

public class CopyableFrame extends JFrame {
    private JTextArea textArea;

    public CopyableFrame(String text) {
        setTitle("Copyable Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(3000, 3000);

        textArea = new JTextArea(text);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        getContentPane().add(scrollPane);

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String input = "This is a sample text.";
            CopyableFrame frame = new CopyableFrame(input);
            frame.setVisible(true);
        });
    }
}

