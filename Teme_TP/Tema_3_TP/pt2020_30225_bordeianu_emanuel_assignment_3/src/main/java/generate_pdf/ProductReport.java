package generate_pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dao.ProduseDAO;
import model.Produse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.stream.Stream;


public class ProductReport {

    private static int nrr = 0;
    List<Produse> ll;


    /***
     * metoda pentru creare header
     * @param tabel
     */
    private void Header(PdfPTable tabel) {
        Stream.of("Nume_produs", "cantitate_produs", "Pret_produs").forEach(columnTitle -> {
                    PdfPCell head = new PdfPCell();
                    head.setBackgroundColor(BaseColor.BLACK);
                    head.setBorderWidth(2);
                    head.setPhrase(new Phrase(columnTitle));
                    tabel.addCell(head);
        } );
    }


    /***
     * metoda pentru adaugare rand
     * @param tabel
     */
    private void adauga_rand(PdfPTable tabel) {
        for (Produse product : ll) {
            tabel.addCell(product.getNume_produs() );
            tabel.addCell(product.getCantitate_produs() + "");
            tabel.addCell(product.getPret_produs() + "");
        }
    }


    /***
     * metoda pentru creare PDF document
     */
    public void createPDF() {
        if ( (ll = ProduseDAO.findAllProducts() ) == null) {
            System.out.println(" Produs negasit ");
            return;
        }
        nrr++;
        Document document = new Document();
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("Report_product" + nrr + ".pdf");
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
        Header(table);
        adauga_rand(table);
        try {
            document.add(table);
        }
        catch (DocumentException e) {
            e.printStackTrace();
        }
        document.close(); /// inchidere document
    }



}
