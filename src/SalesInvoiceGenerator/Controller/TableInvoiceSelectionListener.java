/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SalesInvoiceGenerator.Controller;

import SalesInvoiceGenerator.Model.InvoiceHeaderData;
import SalesInvoiceGenerator.Model.InvoiceLineData;
import SalesInvoiceGenerator.Model.InvoiceLineTableModel;
import SalesInvoiceGenerator.View.InvoiceFrameRunnable;
import java.util.ArrayList;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

/**
 *
 * @author ABDULRAHMAN
 */
public class TableInvoiceSelectionListener implements ListSelectionListener {

    private InvoiceFrameRunnable frame;

    public TableInvoiceSelectionListener(InvoiceFrameRunnable frame) {
        this.frame = frame;
    }
    
    @Override
    public void valueChanged(ListSelectionEvent lse) {
        int selectedInvIndex = frame.getInvoiceHTbl().getSelectedRow();
        
        System.out.println("Invoice Selected: "+selectedInvIndex);
        if (selectedInvIndex != -1){
        InvoiceHeaderData selectedInvoice = frame.getInvoicesArray().get(selectedInvIndex);
        ArrayList<InvoiceLineData> lines =  selectedInvoice.getLines();
        InvoiceLineTableModel lineTableModel = new InvoiceLineTableModel(lines);
        frame.setLinesArray(lines);
        frame.getInvLTbl().setModel(lineTableModel);
        frame.getCustNameLbl().setText(selectedInvoice.getCustomer());
        frame.getInvNumLbl().setText(""+selectedInvoice.getNum());
        frame.getInvTotalLbl().setText(""+selectedInvoice.getInvoiceTotal());
        frame.getInvDateLbl().setText(InvoiceFrameRunnable.dateFormat.format(selectedInvoice.getInvDate()));
        }
    }
    
}
