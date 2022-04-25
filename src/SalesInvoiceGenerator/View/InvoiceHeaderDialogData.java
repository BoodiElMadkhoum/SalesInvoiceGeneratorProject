/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SalesInvoiceGenerator.View;

import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridLayout;


public class InvoiceHeaderDialogData extends JDialog {
    private JTextField customerName;
    private JTextField invDate;
    private JLabel customerNameLabel;
    private JLabel invDateLabel;
    private JButton okButton;
    private JButton cancelButton;
    public InvoiceHeaderDialogData(InvoiceFrameRunnable frame){
        customerName = new JTextField(20);
        customerNameLabel = new JLabel("Customer Name:");
        
        invDate = new JTextField(20);
        invDateLabel = new JLabel("Invoice");
        
        
        okButton = new JButton("Ok");
        cancelButton = new JButton("Cancel");
        
        okButton.setActionCommand("newInvoiceOK");
        cancelButton.setActionCommand("newInvoiceCancel");
        
        okButton.addActionListener(frame.getActionListener());
        cancelButton.addActionListener(frame.getActionListener());
        
        setLayout(new GridLayout(3,2));
        add(invDateLabel);
        add(invDate);
        add(customerNameLabel);
        add(customerName);
        add(okButton);
        add(cancelButton);
        
        pack();
    }

    public JTextField getCustomerName() {
        return customerName;
    }

    public void setCustomerName(JTextField customerName) {
        this.customerName = customerName;
    }

    public JTextField getInvDate() {
        return invDate;
    }

    public void setInvDate(JTextField invDate) {
        this.invDate = invDate;
    }

    public JLabel getCustomerNameLabel() {
        return customerNameLabel;
    }

    public void setCustomerNameLabel(JLabel customerNameLabel) {
        this.customerNameLabel = customerNameLabel;
    }

    public JLabel getInvDateLabel() {
        return invDateLabel;
    }

    public void setInvDateLabel(JLabel invDateLabel) {
        this.invDateLabel = invDateLabel;
    }

    public JButton getOkButton() {
        return okButton;
    }

    public void setOkButton(JButton okButton) {
        this.okButton = okButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(JButton cancelButton) {
        this.cancelButton = cancelButton;
    }
    
    
}
