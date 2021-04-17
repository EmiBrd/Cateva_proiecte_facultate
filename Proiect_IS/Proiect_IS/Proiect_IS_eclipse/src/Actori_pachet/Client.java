package Actori_pachet;


public class Client {

    private int cod;
    private int cod_magazin;
    private String nume_client;
    private String prenume_client;
    private String CNP;
    private String stare_civila;
    private String adresa;
    private String e_mail;
    private String coment;

    // constructor pentru client
    public Client (int cod, int cod_magazin, String nume_client, String prenume_client, String CNP,
                   String stare_civila, String adresa, String e_mail, String coment)
    {
        super();
        this.cod = cod;
        this.cod_magazin = cod_magazin;
        this.nume_client = nume_client;
        this.prenume_client = prenume_client;
        this.CNP = CNP;
        this.stare_civila = stare_civila;
        this.adresa = adresa;
        this.e_mail = e_mail;
        this.coment = coment;
    }

    // Mai jos sunt doar setters si getters
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

    public String getNume_client() {
        return nume_client;
    }

    public void setNume_client(String nume_client) {
        this.nume_client = nume_client;
    }

    public String getPrenume_client() {
        return prenume_client;
    }

    public void setPrenume_client(String prenume_client) {
        this.prenume_client = prenume_client;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public String getStare_civila() {
        return stare_civila;
    }

    public void setStare_civila(String stare_civila) {
        this.stare_civila = stare_civila;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }

}
