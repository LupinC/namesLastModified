package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;
import java.util.List;

public class ModifiedGUI extends JFrame {
    private JTextField modDate;
    private JTextField SrcDir;
    private JTextField DstDir;
    private JTextArea modifiedFiles;

    private Date date;
    private String src;
    private String dst;


    public ModifiedGUI() {
        setTitle("Find and Move, run it as admin please");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 750);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        modDate = new JTextField();
        SrcDir = new JTextField();
        DstDir = new JTextField();
        modifiedFiles = new JTextArea();

        JButton searchButton = new JButton("DO");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                src = SrcDir.getText();
                dst = DstDir.getText();
                String da = modDate.getText();
                int y = Integer.parseInt(da.substring(0,4)) - 1900;
                int m = Integer.parseInt(da.substring(4,6)) - 1;
                int d = Integer.parseInt(da.substring(6,8));
                date = new Date(y,m,d);

                Modified modified = new Modified();
                List<File> files = modified.getModifiedFiles(src,date);

                if (files.isEmpty()) {
                    modifiedFiles.setText("No files found.");
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Found files modified after: ").append(da).append(" ").append("\n");
                    for (File s : files) {
                        sb.append(s.getName()).append("\n");
                    }
                    modifiedFiles.setText(sb.toString());
                }

                CopyToNewLocation newLocation = new CopyToNewLocation();
                newLocation.cutAndPasteFiles(files,dst);

                String n = "Files moved to" + dst;
                JOptionPane.showMessageDialog(null, n);

            }
        });

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Source directory: "));
        inputPanel.add(SrcDir);
        inputPanel.add(new JLabel("Destination directory"));
        inputPanel.add(DstDir);
        inputPanel.add(new JLabel("Modified after (YYYYMMDD): "));
        inputPanel.add(modDate);

        inputPanel.add(searchButton);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(modifiedFiles), BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ModifiedGUI gui = new ModifiedGUI();
            gui.setVisible(true);
        });
    }
}
