/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SalesInvoiceGenerator.Model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ABDULRAHMAN
 */
public class InvoiceLineTableModel extends AbstractTableModel {
    private ArrayList<InvoiceLineData> linesArray;
    private String[] columns = {"Item Name","Unit Price", "Count", "Line Total"};

    public InvoiceLineTableModel(ArrayList<InvoiceLineData> linesArray) {
        this.linesArray = linesArray;
    }

    @Override
    public int getRowCount() {
        return linesArray == null? 0:linesArray.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int i1) {
        if (linesArray == null )
        {return "";}
        else{
            InvoiceLineData line = linesArray.get(rowIndex);
        switch (i1){
            case 0 : return line.getItem();
            case 1: return line.getPrice();
            case 2: return line.getCount();
            case 3: return line.getLineTotal();
            default: return "";
        }}
    }
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
}
