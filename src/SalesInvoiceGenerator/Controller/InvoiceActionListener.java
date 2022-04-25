
package SalesInvoiceGenerator.Controller;

import SalesInvoiceGenerator.Model.InvoiceHeaderData;
import SalesInvoiceGenerator.Model.InvoiceHeaderTableModel;
import SalesInvoiceGenerator.Model.InvoiceLineData;
import SalesInvoiceGenerator.Model.InvoiceLineTableModel;
import SalesInvoiceGenerator.View.InvoiceFrameRunnable;
import SalesInvoiceGenerator.View.InvoiceHeaderDialogData;
import SalesInvoiceGenerator.View.InvoiceLineDialogData;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.util.*;


public class InvoiceActionListener implements ActionListener {
    
    private InvoiceFrameRunnable mainFrame;
    private InvoiceHeaderDialogData headerDialogBox;
    private InvoiceLineDialogData lineDialogBox;
    
    
    public InvoiceActionListener(InvoiceFrameRunnable mainFrame){
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()){
            case "Save Files":
                saveFiles();
                break;
            case "Load Files":
                loadFiles();
                break;
            case "Create New Invoice":
                createNewInvoice();
                break;
            case "Delete Invoice":
                deleteInvoice();
                break;
            case "New Line":
                createNewLine();
                break;
            case "Delete Line":
                deleteLine();
                break;
            case "newInvoiceOK":
                newInvoiceDialogBoxOk();
                break;
            case "newInvoiceCancel":
                newInvoiceDialogBoxCancel();
                break;
            case "newLineCancel":
                newLineDialogBoxCancel();
                break;
            case "newLineOK":
                newLineDialogBoxOK();
                break;
            default:
                break;
                
    }
    
}

    private void deleteLine() {
        int selectedLineIndex = mainFrame.getInvLTbl().getSelectedRow();
        int selectedInvoiceIndex = mainFrame.getInvoiceHTbl().getSelectedRow();
        if (selectedLineIndex != -1) {
            mainFrame.getLinesArray().remove(selectedLineIndex);
            InvoiceLineTableModel lineTableModel = (InvoiceLineTableModel) mainFrame.getInvLTbl().getModel();
            lineTableModel.fireTableDataChanged();
            mainFrame.getInvTotalLbl().setText(""+mainFrame.getInvoicesArray().get(selectedInvoiceIndex).getInvoiceTotal());
            mainFrame.getHeaderTableModel().fireTableDataChanged();
            mainFrame.getInvoiceHTbl().setRowSelectionInterval(selectedInvoiceIndex, selectedInvoiceIndex);
        
    }}

    private void createNewLine() {
        lineDialogBox = new InvoiceLineDialogData(mainFrame);
        lineDialogBox.setVisible(true);
        
    }

    private void deleteInvoice() {
        int selectedInvoiceIndex = mainFrame.getInvoiceHTbl().getSelectedRow();
        if (selectedInvoiceIndex != -1) {
            mainFrame.getInvoicesArray().remove(selectedInvoiceIndex);
            mainFrame.getHeaderTableModel().fireTableDataChanged();

            mainFrame.getInvLTbl().setModel(new InvoiceLineTableModel(null));
            mainFrame.setLinesArray(null);
            mainFrame.getCustNameLbl().setText("");
            mainFrame.getInvNumLbl().setText("");
            mainFrame.getInvTotalLbl().setText("");
            mainFrame.getInvDateLbl().setText("");
        
    }
    }
    private void createNewInvoice() {
        headerDialogBox = new InvoiceHeaderDialogData(mainFrame);
        headerDialogBox.setVisible(true);
        
       
    }

    private void loadFiles() {
        JFileChooser fileChooser = new JFileChooser();
        try {
        int result = fileChooser.showOpenDialog(mainFrame);
        if(result == JFileChooser.APPROVE_OPTION){
            File headerFile = fileChooser.getSelectedFile();
            Path headerPath = Paths.get(headerFile.getAbsolutePath());
            List<String> headerLines = Files.readAllLines(headerPath);
            ArrayList<InvoiceHeaderData> invoiceHeaders = new ArrayList<>();
            for (String headerLine: headerLines){
                String[] arr = headerLine.split(",");
                String str1 = arr[0];
                String str2 = arr[1];
                String str3 = arr[2];
                int code = Integer.parseInt(str1);
                Date invoiceDate = InvoiceFrameRunnable.dateFormat.parse(str2);
                InvoiceHeaderData header = new InvoiceHeaderData(code,str3,invoiceDate);
                invoiceHeaders.add(header);      
            }
            mainFrame.setInvoicesArray(invoiceHeaders);
            result = fileChooser.showOpenDialog(mainFrame);
            if (result == JFileChooser.APPROVE_OPTION){
                File lineFile = fileChooser.getSelectedFile();
                Path linePath = Paths.get(lineFile.getAbsolutePath());
                List<String> lineLines = Files.readAllLines(linePath);
                ArrayList<InvoiceLineData> invoiceLines = new ArrayList<>();
                for(String lineLine : lineLines){
                    String[] arr = lineLine.split(",");
                    String str1 = arr[0]; // invoiceNum
                    String str2 = arr[1]; //itemName
                    String str3 = arr[2]; //price
                    String str4 = arr[3]; // count
                    int invCode = Integer.parseInt(str1);
                    double price = Double.parseDouble(str3);
                    int count = Integer.parseInt(str4);
                    InvoiceHeaderData inv = mainFrame.getInvObject(invCode);
                    InvoiceLineData line = new InvoiceLineData(str2,price,count,inv);
                    inv.getLines().add(line);
                }
            }
            InvoiceHeaderTableModel headerTableModel = new InvoiceHeaderTableModel(invoiceHeaders);
            mainFrame.setHeaderTableModel(headerTableModel);
            mainFrame.getInvoiceHTbl().setModel(headerTableModel);
            System.out.println("Files Read");
        }  
        
        }catch (IOException ex){
            JOptionPane.showMessageDialog(mainFrame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        catch (ParseException ex){
            JOptionPane.showMessageDialog(mainFrame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveFiles() {
        ArrayList<InvoiceHeaderData> invoicesArray = mainFrame.getInvoicesArray();
        JFileChooser fileChooser = new JFileChooser();
        try {
            int result = fileChooser.showSaveDialog(mainFrame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File headerFile = fileChooser.getSelectedFile();
                FileWriter headerFileWriter = new FileWriter(headerFile);
                String headers = "";
                String lines = "";
                for (InvoiceHeaderData invoice : invoicesArray) {
                    headers += invoice.toString();
                    headers += "\n";
                    for (InvoiceLineData line : invoice.getLines()) {
                        lines += line.toString();
                        lines += "\n";
                    }
                }
                headers = headers.substring(0, headers.length()-1);
                lines = lines.substring(0, lines.length()-1);
                result = fileChooser.showSaveDialog(mainFrame);
                File lineFile = fileChooser.getSelectedFile();
                FileWriter lineFileWriter = new FileWriter(lineFile);
                headerFileWriter.write(headers);
                lineFileWriter.write(lines);
                headerFileWriter.close();
                lineFileWriter.close();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(mainFrame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
   
    }

    private void newInvoiceDialogBoxOk() {
        headerDialogBox.setVisible(false);
        
        String custName = headerDialogBox.getCustomerName().getText();
        String str = headerDialogBox.getInvDate().getText();
        Date d = new Date();
        try{
        d = InvoiceFrameRunnable.dateFormat.parse(str);
        } catch (ParseException ex){
            JOptionPane.showMessageDialog(mainFrame, "Cannot parse Date, resetting to Today's Date","Invalid date format",JOptionPane.ERROR_MESSAGE);
        }
        
        int invNum = 0;
        for(InvoiceHeaderData inv: mainFrame.getInvoicesArray()){
            if (inv.getNum() > invNum){invNum = inv.getNum();}
        }
        invNum++;
        InvoiceHeaderData newInv = new InvoiceHeaderData(invNum,custName,d);
        mainFrame.getInvoicesArray().add(newInv);
        mainFrame.getHeaderTableModel().fireTableDataChanged();
        
        headerDialogBox.dispose();
        headerDialogBox = null;
    }

    private void newInvoiceDialogBoxCancel() {
        headerDialogBox.setVisible(false);
        headerDialogBox.dispose();
        headerDialogBox = null;
    }

    private void newLineDialogBoxOK() {
        lineDialogBox.setVisible(false);
        
        String name = lineDialogBox.getItemName().getText();
        String str1 = lineDialogBox.getItemCount().getText();
        String str2 = lineDialogBox.getItemPrice().getText();
        int count = 1;
        double price = 1;
        try {
            count = Integer.parseInt(str1);
        } catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(mainFrame, "Cannot convert number", "Invalid number format", JOptionPane.ERROR_MESSAGE);
        }
        
        try {
            price = Double.parseDouble(str2);
        } catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(mainFrame, "Cannot convert price", "Invalid number format", JOptionPane.ERROR_MESSAGE);
        }
        int selectedInvHeader = mainFrame.getInvoiceHTbl().getSelectedRow();
        if (selectedInvHeader != -1) {
            InvoiceHeaderData invHeader = mainFrame.getInvoicesArray().get(selectedInvHeader);
            InvoiceLineData line = new InvoiceLineData(name, price, count, invHeader);
            mainFrame.getLinesArray().add(line);
            InvoiceLineTableModel lineTableModel = (InvoiceLineTableModel) mainFrame.getInvLTbl().getModel();
            lineTableModel.fireTableDataChanged();
            mainFrame.getHeaderTableModel().fireTableDataChanged();
        }
        mainFrame.getInvoiceHTbl().setRowSelectionInterval(selectedInvHeader, selectedInvHeader);
        lineDialogBox.dispose();
        lineDialogBox = null;
        
    }

    private void newLineDialogBoxCancel() {
        lineDialogBox.setVisible(false);
        lineDialogBox.dispose();
        lineDialogBox = null;
    }
    
}