package Magazin_pachet;


public class Produs {

    private int cod;
    private int cod_magazin;
    private String nume_produs;
    private String material_produs;
    private String culoare_produs;
    private float pret_produs;
    private int numar_bucati;

    
    public Produs(int cod, int cod_magazin, String nume_produs, String material_produs,
                  String culoare_produs, float pret_produs, int numar_bucati)
    {
        super();
        this.cod = cod;
        this.cod_magazin = cod_magazin;
        this.nume_produs = nume_produs;
        this.material_produs = material_produs;
        this.culoare_produs = culoare_produs;
        this.pret_produs = pret_produs;
        this.numar_bucati = numar_bucati;
    }

    // mai jos sunt setters si getters
    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getCod_magazin() {
        return cod_magazin;
    }

    public void setCod_magazin(int cod_magazin) {
        this.cod_magazin = cod_magazin;
    }

    public String getNume_produs() {
        return nume_produs;
    }

    public void setNume_produs(String nume_produs) {
        this.nume_produs = nume_produs;
    }

    public String getMaterial_produs() {
        return material_produs;
    }

    public void setMaterial_produs(String material_produs) {
        this.material_produs = material_produs;
    }

    public String getCuloare_produs() {
        return culoare_produs;
    }

    public void setCuloare_produs(String culoare_produs) {
        this.culoare_produs = culoare_produs;
    }

    public float getPret_produs() {
        return pret_produs;
    }

    public void setPret_produs(float pret_produs) {
        this.pret_produs = pret_produs;
    }

    public int getNumar_bucati() {
        return numar_bucati;
    }

    public void setNumar_bucati(int numar_bucati) {
        this.numar_bucati = numar_bucati;
    }

}
