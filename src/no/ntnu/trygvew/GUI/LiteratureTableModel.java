package no.ntnu.trygvew.GUI;

import javax.swing.table.AbstractTableModel;

public class LiteratureTableModel extends AbstractTableModel {

    private String[] columnNames;
    private Object[][] data;


    public LiteratureTableModel(String[] colNames, String[][] data) {
        this.columnNames = colNames;
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.length - 1;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length - 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

}

