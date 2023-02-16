package presentation.vue.palette;

import presentation.modele.Compte;
import presentation.modele.Log;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableLogModel extends AbstractTableModel {

    private String[] columsName;
    private Object[][] data;

    public String[] getColumsName() {
        return columsName;
    }

    public TableLogModel(String... colName) {
        columsName = new String[colName.length];
        System.arraycopy(colName, 0, columsName, 0, colName.length);
    }

    public String[] initLogColumns() {
        return null;
    }

    public void initLogData(List<Log> logs) {
        int i = 0;
        data = new Object[logs.size()][columsName.length];
        for (Log log : logs) {
            data[i][0] = log.getDate();
            data[i][1] = log.getTime();
            data[i][2] = log.getType();
            data[i][3] = log.getMessage();
            i++;
        }
        this.fireTableDataChanged();
    }

    public Object[][] getData() {
        return data;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columsName.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    public String getColumnName(int col) {
        return columsName[col];
    }


}

