package dao;


import model.Ordine_Comenzi;
import java.util.List;


public class Ordine_ComenziDAO extends AbstractDAO<model.Ordine_Comenzi> {

    /***
     * constructor
     * @param cls
     */
    public Ordine_ComenziDAO(Class<model.Ordine_Comenzi> cls) {
        super(cls);
    }


    /***
     * metoda pentru inserare comanda clinet
     * @param orders
     */
    public static void insert_Ordine(Ordine_Comenzi orders) {
        new AbstractDAO<model.Ordine_Comenzi>(model.Ordine_Comenzi.class).insert(orders);
    }


    /***
     * metoda pentru gasire conanda clienti
     * @return
     */
    public static List<Ordine_Comenzi> findAllOrders() {
        return new AbstractDAO<model.Ordine_Comenzi>(model.Ordine_Comenzi.class).findAll();
    }


}
