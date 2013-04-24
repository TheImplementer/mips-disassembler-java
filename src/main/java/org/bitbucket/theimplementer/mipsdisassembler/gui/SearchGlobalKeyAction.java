package org.bitbucket.theimplementer.mipsdisassembler.gui;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.emaze.dysfunctional.options.Box;

public class SearchGlobalKeyAction extends AbstractAction {

    private final JTable table;
    private final Box<String> lastSearchedString;

    public SearchGlobalKeyAction(JTable table, Box<String> lastSearchedString) {
        this.table = table;
        this.lastSearchedString = lastSearchedString;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        final String searchString = JOptionPane.showInputDialog(null, "Insert text to find", "Find an instruction", JOptionPane.PLAIN_MESSAGE);
        if (searchString == null) {
            return;
        }
        lastSearchedString.setContent(searchString);
        new SearchAndSelectRow(table).perform(searchString);
    }
}
