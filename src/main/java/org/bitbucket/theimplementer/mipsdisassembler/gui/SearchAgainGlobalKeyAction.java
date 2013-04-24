package org.bitbucket.theimplementer.mipsdisassembler.gui;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JTable;
import net.emaze.dysfunctional.options.Box;

public class SearchAgainGlobalKeyAction extends AbstractAction {

    private final JTable table;
    private final Box<String> lastSearchedString;

    public SearchAgainGlobalKeyAction(JTable table, Box<String> lastSearchedString) {
        this.table = table;
        this.lastSearchedString = lastSearchedString;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (!lastSearchedString.hasContent()) {
            return;
        }
        final String searchString = lastSearchedString.getContent();
        new SearchAndSelectRow(table).perform(searchString);
    }
}
