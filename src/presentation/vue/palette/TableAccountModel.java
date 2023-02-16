package presentation.vue.palette;

import presentation.modele.Client;
import presentation.modele.Compte;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableAccountModel extends AbstractTableModel {

    private String[] columsName ;
    private Object[][] data;

    public String[] getColumsName()
    {
        return columsName;
    }
    public TableAccountModel(String... colName)
    {
        columsName = new String[colName.length];
        System.arraycopy(colName, 0, columsName, 0, colName.length);
    }

    public String[] initClientColumns()
    {
        return null ;
    }

    public void initCompteData(List<Compte> comptes)
    {
        int i = 0 ;
        data = new Object[comptes.size()][columsName.length];
        for(Compte compte : comptes)
        {
            data[i][0] = compte.getNumeroCompte();
            data[i][1] = compte.getDateCreation();
            data[i][2] = compte.getSolde();
            data[i][3] = compte.getProprietaire().getId();
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
