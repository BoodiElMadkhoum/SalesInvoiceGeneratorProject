
package SalesInvoiceGenerator.Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class InvoiceHeaderData {
    private int num;
    private String customer;
    private Date invDate;
    private ArrayList<InvoiceLineData> lines;
    private DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public InvoiceHeaderData() {
    }

    public InvoiceHeaderData(int num, String customer, Date invDate) {
        this.num = num;
        this.customer = customer;
        this.invDate = invDate;
    }

    public Date getInvDate() {
        return invDate;
    }

    public void setInvDate(Date invDate) {
        this.invDate = invDate;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public ArrayList<InvoiceLineData> getLines() {
        if (lines == null){
            lines = new ArrayList<>();
        }
        return lines;
    }

    public void setLines(ArrayList<InvoiceLineData> lines) {
        this.lines = lines;
    }
    
    public double getInvoiceTotal(){
        double total = 0;
        
        for(int i = 0 ; i< getLines().size();i++){
            total += getLines().get(i).getLineTotal();
        }
        return total;
    }

    @Override
    public String toString() {
        return num + "," + dateFormat.format(invDate)+","+ customer ;
    }
    
}
