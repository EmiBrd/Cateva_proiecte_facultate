package generate_pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dao.ClienttsDAO;
import dao.Ordine_ComenziDAO;
import model.Clientts;
import model.Produse;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;


public class OrderReport {

    private static int nr_bill = 0;  //
    private static int nr_in = 0;    //
    private static int nr_ordin = 0;  //

    private final String s_bill = "Bill";      //
    private final String ss = "Warehouse";   //
    private final String s_ordin = "Ordin_comanda";   //

    List<model.Ordine_Comenzi> ll;


    /***
     * metoda pentru creare PDF Warehouse
     * @param product
     */
    public void warehousePDF(Produse product) {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        nr_in++;
        try {
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();
            contentStream.setFont(PDType1Font.COURIER, 12);
            StringBuilder sb = new StringBuilder();
            sb.append(ss + nr_in + ".pdf");
            String t1 = " Numar limita " + product.getNume_produs() + " depasit ";
            String t2 = " Numarul de " + product.getNume_produs() +
                    " este = " + product.getCantitate_produs();
            contentStream.newLineAtOffset(25, 725);
            contentStream.setLeading(14.5);
            contentStream.showText(t1);
            contentStream.newLine();
            contentStream.showText(t2);
            contentStream.endText();
            contentStream.close();
            document.save( sb.toString() );
            document.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }


    /***
     * metoda pentru creare PDF bill
     * @param order
     * @param product
     */
    public void billPDF(model.Ordine_Comenzi order, Produse product) {
        nr_bill++;
        Clientts client = ClienttsDAO.find_nume_client(order.getNume_client_ordine() );
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        try {
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();
            contentStream.setFont(PDType1Font.COURIER, 12);
            String t1 = "Bill";
            String t2 = "Nume_client = " + client.getNume_client();
            String t3 = "Adresa_client = " + client.getAdresa();
            String t4 = "Numme_produs = " + product.getNume_produs();
            String t5 = "Cantitate_produs_ordine = " + order.getCantitate_produs_ordine();
            String t6 = "Pret_produs / bucata = " + product.getPret_produs();
            String t7 = "Pretul total este = " + order.getCantitate_produs_ordine() * product.getPret_produs();
            contentStream.newLineAtOffset(25, 725);
            contentStream.setLeading(14.5);
            contentStream.showText(t1);
            contentStream.newLine();
            contentStream.showText(t2);
            contentStream.newLine();
            contentStream.showText(t3);
            contentStream.newLine();
            contentStream.showText(t4);
            contentStream.newLine();
            contentStream.showText(t5);
            contentStream.newLine();
            contentStream.showText(t6);
            contentStream.newLine();
            contentStream.showText(t7);
            contentStream.newLine();
            contentStream.endText();
            contentStream.close();
            StringBuilder sb = new StringBuilder();
            sb.append(s_bill + nr_bill + ".pdf");
            document.save( sb.toString() );
            document.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }


    /***
     * metoda pentru creare PDF document
     */
    public void createPDF() {
        if ( (ll = Ordine_ComenziDAO.findAllOrders() ) == null) {
            System.out.println("Nu sunt comenzi");
            return;
        }
        Document document = new Document();
        nr_ordin++;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(s_ordin + nr_ordin + ".pdf");
            PdfWriter.getInstance(document, new FileOutputStream( sb.toString() ) );
        }
        catch (DocumentException e) {
            e.printStackTrace();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        document.open();
        PdfPTable table = new PdfPTable(3);
        Stream.of("Nume_client_ordine", "Nume_produs_ordine", "Cantitate_produs_ordine").forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.BLACK);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                } );

        for (model.Ordine_Comenzi order : ll) {
            table.addCell(order.getNume_client_ordine() );
            table.addCell(order.getNume_produs_ordine() );
            table.addCell(order.getCantitate_produs_ordine() + "");
        }
        try {
            document.add(table);
        }
        catch (DocumentException e) {
            e.printStackTrace();
        }
        document.close(); /// inchidere document
    }


}
