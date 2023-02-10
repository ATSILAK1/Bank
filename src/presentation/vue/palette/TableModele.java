package presentation.vue.palette;

import presentation.modele.Client;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Objects;


public class TableModele extends AbstractTableModel {

    private String[] columsName ;
    private Object[][] data;

    public String[] getColumsName()
    {
        return columsName;
    }
    public TableModele(String... colName)
    {
        columsName = new String[colName.length];
        System.arraycopy(colName, 0, columsName, 0, colName.length);
    }

    public String[] initClientColumns()
    {
        return null;
    }
    public void initClientData(List<Client> clients)
    {
        int i = 0 ;
        data = new Object[clients.size()][columsName.length];
        for(Client client : clients)
        {
            data[i][0] = client.getId();
            data[i][1] = client.getNom();
            data[i][2] = client.getPrenom();
            data[i][3] = client.getEmail();
            data[i][4] = client.getTel();
            data[i][5] = client.getCin();
            data[i][6] = client.getSolde();
            data[i][7] = client.getLogin();


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

    public String getColumnName(int col)
    {return columsName[col];}
}
