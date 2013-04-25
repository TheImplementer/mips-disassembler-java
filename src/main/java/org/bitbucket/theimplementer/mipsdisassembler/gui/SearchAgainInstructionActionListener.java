package org.bitbucket.theimplementer.mipsdisassembler.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import net.emaze.dysfunctional.options.Box;

public class SearchAgainInstructionActionListener implements ActionListener {

    private final JTable table;
    private final Box<String> lastSearchedString;

    public SearchAgainInstructionActionListener(JTable table, Box<String> lastSearchedString) {
        this.table = table;
        this.lastSearchedString = lastSearchedString;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (lastSearchedString.isEmpty()) {
            return;
        }
        new SearchAndSelectRow(table).perform(lastSearchedString.getContent());
    }
}
