package citire_fisier;

import dao.ClienttsDAO;
import dao.Ordine_ComenziDAO;
import dao.ProduseDAO;
import model.Clientts;
import model.Produse;
import generate_pdf.ClientReport;
import generate_pdf.OrderReport;
import generate_pdf.ProductReport;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class Read {

    /***
     * metoda pentru citire din fisier
     * @param ff
     */
    public static void parseInputFile(String ff) {
        File file = new File(ff);
        String insert_client = "Insert client";
        String insert_product = "Insert product";
        String orderr = "Order";
        String delete_client = "Delete client";
        String delete_product = "Delete Product";
        String report_client = "Report client";
        String report_product = "Report product";
        String report_order = "Report order";


        Scanner scanin = null;
        try {
            scanin = new Scanner(file);
            while (scanin.hasNext() ) {
                String nextLine = scanin.nextLine();
                String[] tokk = nextLine.split(": ");

                //if(tokk[0] == insert_client){
                if(tokk[0].equals("Insert client") ){
                    String[] c = tokk[1].split(", ");
                    if (ClienttsDAO.find_nume_client(c[0]) == null)
                        ClienttsDAO.insertClient(new Clientts(c[0], c[1]));
                }
                //else if (tokk[0] == insert_product){
                else if(tokk[0].equals("Insert product") ) {
                    String[] p = tokk[1].split(", ");
                    if (ProduseDAO.find_nume_produs(p[0]) == null)
                        ProduseDAO.insert_produs(new Produse(p[0],
                                Integer.parseInt(p[1]), Double.parseDouble(p[2]) ) );
                }
                //else if (tokk[0] == orderr){
                else if(tokk[0].equals("Order") ) {
                    String[] o = tokk[1].split(", ");
                    Produse p = ProduseDAO.find_nume_produs(o[1]);
                    if (p.getCantitate_produs() < Integer.parseInt(o[2])) {
                        OrderReport orderReport = new OrderReport();
                        orderReport.warehousePDF(p);
                    }
                    else {
                        model.Ordine_Comenzi orders = new model.Ordine_Comenzi(o[0], o[1], Integer.parseInt(o[2]));
                        Ordine_ComenziDAO.insert_Ordine(orders);
                        ProduseDAO productDAO = new ProduseDAO();
                        Produse orderedProduct = productDAO.update_produs(o[1], Integer.parseInt(o[2]));
                        OrderReport orderReport = new OrderReport();
                        orderReport.billPDF(orders, orderedProduct);
                    }
                }
                //else if (tokk[0] == delete_client){
                else if(tokk[0].equals("Delete client") ) {
                    String[] c = tokk[1].split(", ");
                    if (ClienttsDAO.find_nume_client(c[0]) != null)
                        ClienttsDAO.deleteClient(c[0]);
                }
                //else if (tokk[0] == delete_product){
                else if(tokk[0].equals("Delete Product") ) {
                    String[] p = tokk[1].split(", ");
                    if (ProduseDAO.find_nume_produs(p[0]) != null)
                        ProduseDAO.delete_produs(p[0]);
                }
                //else if(tokk[0] == report_client){
                else if(tokk[0].equals("Report client") ) {
                    ClientReport clientReport = new ClientReport();
                    if (ClienttsDAO.findAllClients() != null)
                        clientReport.create_PDF();
                }
                //else if(tokk[0] == report_product){
                else if(tokk[0].equals("Report product") ) {
                    ProductReport productReport = new ProductReport();
                    if (ProduseDAO.findAllProducts() != null)
                        productReport.createPDF();
                }
                //else if (tokk[0] == report_order){
                else if (tokk[0].equals("Report order") ) {
                    OrderReport orderReport = new OrderReport();
                    if (Ordine_ComenziDAO.findAllOrders() != null)
                        orderReport.createPDF();
                }
                else {
                    System.out.println(tokk[0]+" Eroare!");
                    System.out.println(tokk[0]+" Datele nu sunt corecte in fisier!");
                }

            }

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

            scanin.close();

    }

}
