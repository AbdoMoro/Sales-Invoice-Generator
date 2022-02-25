/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.util.Map;
import Model.Invoice;
import Model.InvoiceItem;
import View.NewInvoiceDialog;
import View.NewItemDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class InvoiceOperationsHandler {
    
     public static void loadDataIntoInvoicesTable(Map<Integer, Invoice> invoices, JTable table) {
        
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        Object rowData[] = new Object[4];
        for (Invoice invoice : invoices.values()) {
            rowData[0] = invoice.getInvoiceNumber();
            rowData[1] = invoice.getInvoiceDate();
            rowData[2] = invoice.getCustomerName();
            rowData[3] = invoice.getInvoiceTotal();
            tableModel.addRow(rowData);
        }
    }
    
     public static void createInvoice(Map<Integer, Invoice> invoices, JTable table){
         new NewInvoiceDialog(null, true, String.valueOf(Invoice.invoiceNumberCount)).show();
        Invoice invoice = NewInvoiceDialog.getCreatedInvoice();
        if (invoice != null) {
            invoices.put(invoice.getInvoiceNumber(), invoice);
            InvoiceOperationsHandler.loadDataIntoInvoicesTable(invoices, table);
        }
     }
     
    public static void deleteSelectedInvoice(JTable table, Map<Integer,Invoice> invoices, int key){
        invoices.remove(key);
        loadDataIntoInvoicesTable(invoices, table);
    }
    
    public static void createItem(JTable table, int key){
        new NewItemDialog(null, true, key).show();
        InvoiceItem item  = NewItemDialog.getNewItem();
        if(item != null){
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            Object rowData[] = new Object[5];
            rowData[0] = item.getInvoiceNum();
            rowData[1] =item.getName();
            rowData[2] = item.getPrice();
            rowData[3] = item.getCount();
            rowData[4] = item.getTotal();
            model.addRow(rowData);
        }
    }
    
}
