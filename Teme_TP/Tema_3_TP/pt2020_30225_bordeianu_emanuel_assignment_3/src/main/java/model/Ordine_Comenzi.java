package model;

public class Ordine_Comenzi {
    private String nume_client_ordine;
    private String nume_produs_ordine;
    private int cantitate_produs_ordine;


    /***
     * constructor fara param
     */
    public Ordine_Comenzi() {
        super();
    }


    /***
     * constructor
     * @param nume_client_ordine
     * @param nume_produs_ordine
     * @param cantitate_produs_ordine
     */
    public Ordine_Comenzi(String nume_client_ordine, String nume_produs_ordine, int cantitate_produs_ordine) {
        this.nume_client_ordine = nume_client_ordine;
        this.nume_produs_ordine = nume_produs_ordine;
        this.cantitate_produs_ordine = cantitate_produs_ordine;
    }


    /***
     * getter pentru numele clientului care comanda
     * @return nume_client_ordine
     */
    public String getNume_client_ordine() {
        return nume_client_ordine;
    }


    /***
     * getter pentru numele produsului care este comandat
     * @return nume_produs_ordine
     */
    public String getNume_produs_ordine() {
        return nume_produs_ordine;
    }


    /***
     * getter pentru cantitatea produsului care este comandata
     * @return cantitate_produs_ordine
     */
    public int getCantitate_produs_ordine() {
        return cantitate_produs_ordine;
    }


    /***
     * setter pentru numele clientului care comanda
     * @param nume_client_ordine
     */
    public void setNume_client_ordine(String nume_client_ordine) {
        this.nume_client_ordine = nume_client_ordine;
    }


    /***
     * setter pentru numele produsului care este comandat
     * @param nume_produs_ordine
     */
    public void setNume_produs_ordine(String nume_produs_ordine) {
        this.nume_produs_ordine = nume_produs_ordine;
    }


    /***
     * setter pentru cantitatea produsului care este comandata
     * @param cantitate_produs_ordine
     */
    public void setCantitate_produs_ordine(int cantitate_produs_ordine) {
        this.cantitate_produs_ordine = cantitate_produs_ordine;
    }


    /***
     * metoda pentru afisare
     * @return
     */
    public String toString() {
        return this.nume_client_ordine + " " + this.nume_produs_ordine + " " + this.cantitate_produs_ordine;
    }



}
