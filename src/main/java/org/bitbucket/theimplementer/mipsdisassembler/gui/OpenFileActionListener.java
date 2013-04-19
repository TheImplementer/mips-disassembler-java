package org.bitbucket.theimplementer.mipsdisassembler.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class OpenFileActionListener implements ActionListener {

    private final MainFrame parent;

    public OpenFileActionListener(MainFrame parent) {
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        final JFileChooser openFileChooser = new JFileChooser();
        final int userOpenAction = openFileChooser.showOpenDialog(parent);
        if (userOpenAction == JFileChooser.APPROVE_OPTION) {
            final File selectedFile = openFileChooser.getSelectedFile();
            try {
                parent.loadFile(selectedFile);
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(parent, "Cannot find selected file.");
            }
        }
    }
}
