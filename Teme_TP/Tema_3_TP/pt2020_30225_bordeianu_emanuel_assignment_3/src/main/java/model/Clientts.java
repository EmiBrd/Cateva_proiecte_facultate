package model;

public class Clientts {
    private String nume_client;
    private String adresa;


    /***
     * constructor fara param care instantiaza null
     */
    public Clientts() {
        super();
    }


    /***
     * constructor
     * @param nume_client
     * @param adresa
     */
    public Clientts(String nume_client, String adresa) {
        this.nume_client = nume_client;
        this.adresa = adresa;
    }


    /***
     * getter pentru numele clientului
     * @return nume_client
     */
    public String getNume_client() {
        return nume_client;
    }


    /***
     * getter pentru adresa clientului
     * @return adresa
     */
    public String getAdresa() {
        return adresa;
    }


    /***
     * setter pentru numele clientului
     * @param nume_client
     */
    public void setNume_client(String nume_client) {
        this.nume_client = nume_client;
    }


    /***
     * setter pentru adresa clientului
     * @param adresa
     */
    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }


    /***
     * metoda pentru afisare client
     * @return
     */
    public String toString() {
        return this.nume_client + " " + this.adresa;
    }

}
