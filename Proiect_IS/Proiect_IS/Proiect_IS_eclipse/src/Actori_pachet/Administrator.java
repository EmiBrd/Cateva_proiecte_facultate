package Actori_pachet;

public class Administrator {

    private int admin_id;
    private int cod_magazin;
    private String administrator_nume;
    private String administrator_prenume;
    private String adresa;
    private String e_mail;
    private String parola;

    // constructor pentru Admin
    public Administrator(int admin_id, int cod_magazin, String administrator_nume,
                         String administrator_prenume, String adresa, String e_mail, String parola)
    {
        super();
        this.admin_id = admin_id;
        this.cod_magazin = cod_magazin;
        this.administrator_nume = administrator_nume;
        this.administrator_prenume = administrator_prenume;
        this.adresa = adresa;
        this.e_mail = e_mail;
        this.parola = parola;
    }

    // mai jos sunt doar setters si getters
    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public int getCod_magazin() {
        return cod_magazin;
    }

    public void setCod_magazin(int cod_magazin) {
        this.cod_magazin = cod_magazin;
    }

    public String getAdministrator_nume() {
        return administrator_nume;
    }

    public void setAdministrator_nume(String administrator_nume) {
        this.administrator_nume = administrator_nume;
    }

    public String getAdministrator_prenume() {
        return administrator_prenume;
    }

    public void setAdministrator_prenume(String administrator_prenume) {
        this.administrator_prenume = administrator_prenume;
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

	public String getParola() {
		return parola;
	}

	public void setParola(String parola) {
		this.parola = parola;
	}
    
    

}
