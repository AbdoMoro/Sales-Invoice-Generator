/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

public class Invoice {

    public static int invoiceNumberCount = 1;
    private final int invoiceNumber;
    private String invoiceDate;
    private String customerName;
    private double invoiceTotal;
    private ArrayList<InvoiceItem> invoiceItems;

    public Invoice(String invoiceDate, String customerName) {
        invoiceNumber = invoiceNumberCount++;
        this.invoiceDate = invoiceDate;
        this.customerName = customerName;
        invoiceItems = new ArrayList<>();
    }

    public Invoice(int invoiceNumber, String invoiceDate, String customerName) {
        this.invoiceNumber = invoiceNumber;
        invoiceNumberCount = invoiceNumber + 1;
        this.invoiceDate = invoiceDate;
        this.customerName = customerName;
        invoiceItems = new ArrayList<>();
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getInvoiceTotal() {
        return invoiceTotal;
    }

    public ArrayList<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(ArrayList<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    public void calculateInvoiceTotal() {
        invoiceTotal = 0;
        for (int i = 0; i < invoiceItems.size(); i++) {
            invoiceTotal += invoiceItems.get(i).getTotal();
        }
    }

    public void addItem(InvoiceItem item) {
        invoiceItems.add(item);
    }

}
