package org.bitbucket.theimplementer.mipsdisassembler.gui;

import javax.swing.table.AbstractTableModel;


public class ContentTableModel extends AbstractTableModel {
    public static final int COLUMN_COUNT = 3;
    public static final String[] COLUMN_NAMES = new String[]{"Offset", "Opcode", "Instruction"};
    private final Object[][] content;

    public ContentTableModel(Object[][] content) {
        this.content = content;
    }

    @Override
    public int getRowCount() {
        return content.length;
    }

    @Override
    public int getColumnCount() {
        return COLUMN_COUNT;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return content[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }



}
