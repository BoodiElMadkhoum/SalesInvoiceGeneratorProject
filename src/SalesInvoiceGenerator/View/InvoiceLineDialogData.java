
package SalesInvoiceGenerator.View;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class InvoiceLineDialogData extends JDialog {
    private JTextField itemName;
    private JTextField itemCount;
    private JTextField itemPrice;
    private JLabel itemNameLabel;
    private JLabel itemCountLabel;
    private JLabel itemPriceLabel;
    private JButton okButton;
    private JButton cancelButton;
    
    public InvoiceLineDialogData(InvoiceFrameRunnable frame){
        itemName = new JTextField(20);
        itemNameLabel = new JLabel("Item Name");
        
        itemCount = new JTextField(20);
        itemCountLabel = new JLabel("Item Count");
        
        itemPrice = new JTextField(20);
        itemPriceLabel = new JLabel("Item Price");
        
        okButton = new JButton("Ok");
        cancelButton = new JButton("Cancel");
        
        okButton.setActionCommand("newLineOK");
        cancelButton.setActionCommand("newLineCancel");
        
        okButton.addActionListener(frame.getActionListener());
        cancelButton.addActionListener(frame.getActionListener());
        
        setLayout(new GridLayout(4,2));
        add(itemNameLabel);
        add(itemName);
        add(itemCountLabel);
        add(itemCount);
        add(itemPriceLabel);
        add(itemPrice);
        add(okButton);
        add(cancelButton);
        
        pack();
    }

    public JTextField getItemName() {
        return itemName;
    }

    public void setItemName(JTextField itemName) {
        this.itemName = itemName;
    }

    public JTextField getItemCount() {
        return itemCount;
    }

    public void setItemCount(JTextField itemCount) {
        this.itemCount = itemCount;
    }

    public JTextField getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(JTextField itemPrice) {
        this.itemPrice = itemPrice;
    }

    public JLabel getItemNameLabel() {
        return itemNameLabel;
    }

    public void setItemNameLabel(JLabel itemNameLabel) {
        this.itemNameLabel = itemNameLabel;
    }

    public JLabel getItemCountLabel() {
        return itemCountLabel;
    }

    public void setItemCountLabel(JLabel itemCountLabel) {
        this.itemCountLabel = itemCountLabel;
    }

    public JLabel getItemPriceLabel() {
        return itemPriceLabel;
    }

    public void setItemPriceLabel(JLabel itemPriceLabel) {
        this.itemPriceLabel = itemPriceLabel;
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
