/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


public class InvoiceItem {
    private int invoiceNum;
    private String name;
    private double price;
    private int count;
    private double total;

    public InvoiceItem(int invoiceNum, String name, double price, int count) {
        this.invoiceNum = invoiceNum;
        this.name = name;
        this.price = price;
        this.count = count;
        calculateTotal();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getInvoiceNum() {
        return invoiceNum;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    public double getTotal() {
        return total;
    }
    
    private void calculateTotal(){
        total = price*count;
    }
    
}
