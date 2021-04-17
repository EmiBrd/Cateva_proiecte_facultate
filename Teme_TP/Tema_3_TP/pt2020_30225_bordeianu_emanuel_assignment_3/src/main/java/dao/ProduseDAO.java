package dao;


import model.Produse;
import java.util.List;

/**
 * This is a class
 */
public class ProduseDAO extends AbstractDAO<Produse> {


    /***
     * constructor 1 fata param
     */
    public ProduseDAO() {
        super(Produse.class);
    }


    /***
     * constructor 2
     * @param cls
     */
    public ProduseDAO(Class<Produse> cls) {
        super(cls);
    }


    /***
     * metoda pentru inserare produs
     * @param produs
     */
    public static void insert_produs(Produse produs) {
        new AbstractDAO<Produse>(Produse.class).insert(produs);
    }


    /***
     * metoda pentru stergere (delete) produs
     * @param nume_produs
     */
    public static void delete_produs(String nume_produs) {
        new AbstractDAO2<Produse>(Produse.class).delete("nume_produs", nume_produs);
    }


    /***
     * metoda pentru gasire produs dupa nume
     * @param nume_produs
     * @return
     */
    public static Produse find_nume_produs(String nume_produs) {
        return new AbstractDAO2<Produse>(Produse.class).find_name("nume_produs", nume_produs);
    }


    /***
     * metoda pentru gasire produse
     * @return
     */
    public static List<Produse> findAllProducts() {
        return new AbstractDAO<Produse>(Produse.class).findAll();
    }


    /***
     * metoda pentru actualizarea produsului
     * @param nume_produs
     * @param cantitate_produs
     * @return
     */
    public Produse update_produs(String nume_produs, int cantitate_produs) {
        Produse p1 = find_nume_produs(nume_produs);
        Produse product = new Produse(nume_produs, cantitate_produs, p1.getPret_produs() );
        p1.setCantitate_produs(p1.getCantitate_produs() - cantitate_produs);
        update(p1);
        return product;
    }


}
