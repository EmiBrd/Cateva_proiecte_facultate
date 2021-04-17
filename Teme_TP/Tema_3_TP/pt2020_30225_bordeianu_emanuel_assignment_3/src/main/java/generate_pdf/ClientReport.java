package generate_pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dao.ClienttsDAO;
import model.Clientts;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.stream.Stream;

public class ClientReport {

    private static int nrr = 0;
    /// lista de clienti
    private List<Clientts> ll;


    /***
     * metoda pentru adaugarea unui rand
     * @param tabel
     */
    private void adauga_rand(PdfPTable tabel) {
        for (Clientts cc : ll) {
            tabel.addCell(cc.getNume_client() );
            tabel.addCell(cc.getAdresa() );
        }
    }


    /***
     * metoda pentru creare header
     * @param table
     */
    private void Header_tabel(PdfPTable table) {
        Stream.of("nume_client", "adresa").forEach(col -> {
            PdfPCell head = new PdfPCell();
            head.setBackgroundColor(BaseColor.BLACK);
            head.setBorderWidth(2);
            head.setPhrase(new Phrase(col) ) ;
            table.addCell(head);
        } );
    }


    /***
     * metoda pentru crearea tabelului pentru clienti
     */
    public void create_PDF() {
        if ( (ll = ClienttsDAO.findAllClients() ) == null) {
            System.out.println(" Clientul nu a fost gasit ");
            return ;
        }
        Document document = new Document();
        nrr++;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("Report_client" +nrr+ ".pdf");
            PdfWriter.getInstance(document, new FileOutputStream( sb.toString() ) );
        }
        catch (DocumentException e) {
            e.printStackTrace();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        document.open();
        PdfPTable table = new PdfPTable(2);
        Header_tabel(table);
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
