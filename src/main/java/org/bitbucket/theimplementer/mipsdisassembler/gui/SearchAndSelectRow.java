package org.bitbucket.theimplementer.mipsdisassembler.gui;

import java.awt.Rectangle;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import net.emaze.dysfunctional.dispatching.actions.Action;

public class SearchAndSelectRow implements Action<String> {

    private final JTable table;

    public SearchAndSelectRow(JTable table) {
        this.table = table;
    }

    @Override
    public void perform(String searchString) {
        final int selectedRow = table.getSelectionModel().getMinSelectionIndex();
        final TableModel tableModel = table.getModel();
        for (int count = selectedRow + 1; count != tableModel.getRowCount(); ++count) {
            final String rowValue = tableModel.getValueAt(count, 2).toString();
            if (rowValue.contains(searchString)) {
                table.getSelectionModel().setSelectionInterval(0, count);
                final Rectangle cellRect = table.getCellRect(count, 0, true);
                table.scrollRectToVisible(cellRect);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Cannot find specified value");
    }
}
