package dao;


import java.util.List;
import model.Clientts;


public class ClienttsDAO extends AbstractDAO<Clientts> {


    /***
     * constructor
     * @param cls
     */
    public ClienttsDAO(Class<Clientts> cls) {
        super(cls);
    }


    /***
     * metoda pentru inserare client
     * @param cc
     */
    public static void insertClient(Clientts cc) {
        new AbstractDAO<Clientts>(Clientts.class).insert(cc);
    }


    /***
     * metoda pentru stergere (delete) client
     * @param cn
     */
    public static void deleteClient(String cn) {
        new AbstractDAO2<Clientts>(Clientts.class).delete("nume_client", cn);
    }


    /***
     * metoda pentru gasire nume client
     * @param cn
     * @return
     */
    public static Clientts find_nume_client(String cn) {
        return new AbstractDAO2<Clientts>(Clientts.class).find_name("nume_client", cn);
    }


    /***
     * metoda pentru gasire clienti
     * @return
     */
    public static List<Clientts> findAllClients() {
        return new AbstractDAO<Clientts>(Clientts.class).findAll();
    }



}
