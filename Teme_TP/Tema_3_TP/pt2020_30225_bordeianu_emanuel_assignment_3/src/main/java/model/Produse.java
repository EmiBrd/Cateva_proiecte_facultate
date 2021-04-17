package model;


public class Produse {
    private String nume_produs;
    private int cantitate_produs;
    private double pret_produs;

    /// constructor fara parametri care instantiaza null
    public Produse() {
        super();
    }

    /// constructor
    public Produse(String nume_produs, int cantitate_produs, double pret_produs) {
        this.nume_produs = nume_produs;
        this.cantitate_produs = cantitate_produs;
        this.pret_produs = pret_produs;
    }


    /***
     * getter pentru numele produsului
     * @return nume_produs
     */
    public String getNume_produs() {
        return nume_produs;
    }


    /***
     * getter pentru cantitatea produsului
     * @return cantitate_produs
     */
    public int getCantitate_produs() {
        return cantitate_produs;
    }


    /***
     * getter pentru pretul produsului
     * @return pret_produs
     */
    public double getPret_produs() {
        return pret_produs;
    }


    /***
     * setter pentru numele produsului
     * @param nume_produs
     */
    public void setNume_produs(String nume_produs) {
        this.nume_produs = nume_produs;
    }


    /***
     * setter pentru cantitatea produsului
     * @param cantitate_produs
     */
    public void setCantitate_produs(int cantitate_produs) {
        this.cantitate_produs = cantitate_produs;
    }


    /***
     * setter pentru pretul produsului
     * @param pret_produs
     */
    public void setPret_produs(double pret_produs) {
        this.pret_produs = pret_produs;
    }


    /***
     * metoda pentru afisare
     * @return
     */
    public String toString() {
        return this.nume_produs + " " + this.cantitate_produs + " " + this.pret_produs;
    }



}
