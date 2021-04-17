package model;

public class Warehouse {

    private int id_warehouse;
    private String nume_client_warehouse;
    private String nume_produs_warehouse;
    private int cantitate_produs_warehouse;
    private double pret_produs_warehouse;

    /***
     * getter pentru id_warehouse
     * @return id_warehouse
     */
    public int getId_warehouse() {
        return id_warehouse;
    }

    /***
     * getter pentru nume_client_warehouse
     * @return nume_client_warehouse
     */
    public String getNume_client_warehouse() {
        return nume_client_warehouse;
    }

    /***
     * getter pentru nume_produs_warehouse
     * @return nume_produs_warehouse
     */
    public String getNume_produs_warehouse() {
        return nume_produs_warehouse;
    }

    /***
     * getter pentru cantitate_produs_warehouse
     * @return cantitate_produs_warehouse
     */
    public int getCantitate_produs_warehouse() {
        return cantitate_produs_warehouse;
    }

    /***
     * getter pentru nume_produs_warehouse
     * @return pret_produs_warehouse
     */
    public double getPret_produs_warehouse() {
        return pret_produs_warehouse;
    }

    /***
     * setter pentru id_warehouse
     * @param id_warehouse
     */
    public void setId_warehouse(int id_warehouse) {
        this.id_warehouse = id_warehouse;
    }

    /***
     * setter pentru nume_client_warehouse
     * @param nume_client_warehouse
     */
    public void setNume_client_warehouse(String nume_client_warehouse) {
        this.nume_client_warehouse = nume_client_warehouse;
    }

    /***
     * setter pentru nume_produs_warehouse
     * @param nume_produs_warehouse
     */
    public void setNume_produs_warehouse(String nume_produs_warehouse) {
        this.nume_produs_warehouse = nume_produs_warehouse;
    }

    /***
     * setter pentru cantitate_produs_warehouse
     * @param cantitate_produs_warehouse
     */
    public void setCantitate_produs_warehouse(int cantitate_produs_warehouse) {
        this.cantitate_produs_warehouse = cantitate_produs_warehouse;
    }

    /***
     * setter pentru pret_produs_warehouse
     * @param pret_produs_warehouse
     */
    public void setPret_produs_warehouse(double pret_produs_warehouse) {
        this.pret_produs_warehouse = pret_produs_warehouse;
    }


}
