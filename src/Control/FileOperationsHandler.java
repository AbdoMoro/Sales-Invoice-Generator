/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Invoice;
import Model.InvoiceItem;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class FileOperationsHandler {

    private static File chosenHeaderFile;
    private static File chosenInvoiceLineFile;

    public static void loadFiles(Map<Integer, Invoice> invoices) {
        JOptionPane.showMessageDialog(null, "Select Invoice Header file", "Invoice header", JOptionPane.WARNING_MESSAGE);
        while (true) {
            JFileChooser chooser = new JFileChooser();
            int selected = chooser.showOpenDialog(null);
            if (selected == JFileChooser.APPROVE_OPTION) {
                chosenHeaderFile = chooser.getSelectedFile();
                try {
                    if (!chosenHeaderFile.getName().endsWith(".csv")) {
                        throw new FileFormatException("Wrong file type\nPlease select CSV file");
                    }
                    FileReader reader = new FileReader(chosenHeaderFile);
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    String str;
                    while ((str = bufferedReader.readLine()) != null) {
                        String[] invoiceFields = str.split(",");
                        invoices.put(Integer.parseInt(invoiceFields[0]), new Invoice(Integer.parseInt(invoiceFields[0]), invoiceFields[1], invoiceFields[2]));

                    }
                    bufferedReader.close();
                    reader.close();
                    break;
                } catch (FileNotFoundException e) {
                    JOptionPane.showMessageDialog(null, "File not found!!\nPlease try again", "ERROR!!", JOptionPane.ERROR_MESSAGE);
                } catch (IOException e) {
                } catch (FileFormatException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR!!", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Wrong file format", "ERROR!!", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Select Invoice line file", "Invoice line", JOptionPane.WARNING_MESSAGE);
        while (true) {
            JFileChooser chooser = new JFileChooser();
            int selected = chooser.showOpenDialog(null);
            if (selected == JFileChooser.APPROVE_OPTION) {
                chosenInvoiceLineFile = chooser.getSelectedFile();
                try {
                    if (!chosenInvoiceLineFile.getName().endsWith(".csv")) {
                        throw new FileFormatException("Wrong file type\nPlease select CSV file");
                    }
                    FileReader reader = new FileReader(chosenInvoiceLineFile);
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    String str;
                    while ((str = bufferedReader.readLine()) != null) {
                        String[] invoiceItemsFields = str.split(",");
                        invoices.get(Integer.parseInt(invoiceItemsFields[0])).addItem(
                                new InvoiceItem(Integer.parseInt(invoiceItemsFields[0]), invoiceItemsFields[1], Double.parseDouble(invoiceItemsFields[2]), Integer.parseInt(invoiceItemsFields[3])));
                    }
                    bufferedReader.close();
                    reader.close();
                    break;
                } catch (FileNotFoundException e) {
                    JOptionPane.showMessageDialog(null, "File not found!!\nPlease try again", "ERROR!!", JOptionPane.ERROR_MESSAGE);
                } catch (IOException e) {
                } catch (FileFormatException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR!!", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Wrong file format", "ERROR!!", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public static void saveFiles(Map<Integer, Invoice> invoices) {
        chosenHeaderFile.setWritable(true);
        chosenInvoiceLineFile.setWritable(true);
        try {
            FileWriter headerwriter = new FileWriter(chosenHeaderFile, false);
            BufferedWriter headerbufferedWriter = new BufferedWriter(headerwriter);
            FileWriter LineFileWriter = new FileWriter(chosenInvoiceLineFile, false);
            BufferedWriter lineBufferedWriter = new BufferedWriter(LineFileWriter);
            if (!chosenHeaderFile.getName().endsWith(".csv") || !chosenInvoiceLineFile.getName().endsWith(".csv")) {
                throw new FileFormatException("Wrong file type\nPlease select CSV file");
            }
            for (Invoice invoice : invoices.values()) {
                String header = "" + invoice.getInvoiceNumber() + "," + invoice.getInvoiceDate() + "," + invoice.getCustomerName() + "\n";
                headerbufferedWriter.write(header);

                for (InvoiceItem item : invoice.getInvoiceItems()) {
                    String line = "" + item.getInvoiceNum() + "," + item.getName() + "," + item.getPrice() + "," + item.getCount() + "\n";
                    lineBufferedWriter.write(line);
                }
            }
            lineBufferedWriter.close();
            LineFileWriter.close();
            headerbufferedWriter.close();
            headerwriter.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage()+"\nPlease make sure you are not openning the file by any file editor then try again.", "ERROR!!", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Files not found", "ERROR!!", JOptionPane.ERROR_MESSAGE);
        } catch (FileFormatException e) {
            JOptionPane.showMessageDialog(null, "Wrong file format", "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }
    }

}
