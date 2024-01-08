package Magazin_pachet;

import java.util.LinkedList;
import java.util.List;

import Actori_pachet.*;
import java.sql.*;

public class Magazin {

    private int cod;
    private String nume_magazin;
    private Administrator administrator;
    private List<Client> client_list;
    private List<Produs> produs_list;
    private Connection con;

    // constructor pentru magazin
    public Magazin(Connection con)
    {
        System.out.println("magazinnn:"+con);
        this.con=con;
        client_list = new LinkedList<Client>();
        produs_list = new LinkedList<Produs>();
        //administrator = new Administrator();
        init_actori(con);
        init_magazin_pachet(con);
    }

    
    private void init_actori(Connection con)
    {
        init_Admin(con);
        init_Client(con);
    }

    private void init_magazin_pachet(Connection con)
    {
        init_magazin(con);
        init_produs(con);
        //init_Client(con);
    }

    private void init_Admin(Connection con)
    {
        String q="select * from Administrator;";
        try
        {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(q);
            while(rs.next() )
            {
                int admin_id = rs.getInt(1);
                int cod_magazin = rs.getInt(2);
                String administrator_nume = rs.getString(3);
                String administrator_prenume = rs.getString(4);
                String adresa = rs.getString(5);
                String e_mail = rs.getString(6);
                String parola_admin = rs.getString(7);

                //Instantiez Adminul
                this.administrator = new Administrator(admin_id, cod_magazin,administrator_nume,
                                     administrator_prenume, adresa, e_mail, parola_admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // creeaza un client existent in BD si il adauga intr-o lista
    private void init_Client(Connection con)
    {
        String q="select * from Client;";
        try
        {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(q);
            //System.out.println("Querry client:");
            while(rs.next() )
            {
                int id_client = rs.getInt(1);
                int cod_magazin = rs.getInt(2);
                String nume_client = rs.getString(3);
                String prenume_client = rs.getString(4);
                String CNP = rs.getString(5);
                String stare_civila=rs.getString(6);
                String adresa = rs.getString(7);
                String e_mail = rs.getString(8);
                String coment = rs.getString(9);

                Client cll = new Client(id_client, cod_magazin, nume_client, prenume_client, CNP,
                           stare_civila, adresa, e_mail, coment);
                //System.out.println(cll.getE_mail());
                client_list.add(cll);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // creeaza un magazin
    private void init_magazin(Connection con)
    {
        String q="select * from Magazin;";
        try
        {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(q);
            while(rs.next() )
            {
                int cod = rs.getInt(1);
                String nume_magazin = rs.getString(2);
                this.cod = cod;
                this.nume_magazin = nume_magazin;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // creeaza un produs existent in BD si il adauga in lista
    private void init_produs(Connection con)
    {
        String q="select * from Produs;";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(q);
            while(rs.next() )
            {
                int cod = rs.getInt(1);
                int cod_magazin = rs.getInt(2);
                String nume_produs = rs.getString(3);
                String material_produs = rs.getString(4);
                String culoare_produs = rs.getString(5);
                float pret_produs = rs.getFloat(6);
                int numar_bucati = rs.getInt(7);

                Produs pp = new Produs(cod, cod_magazin, nume_produs, material_produs, culoare_produs,
                        pret_produs, numar_bucati);
                //System.out.println(pp.getNume_produs() );
                produs_list.add(pp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    // adauga un produs in BD
    public void add_produs(Connection con, Produs produs){
        String query = "insert into Produs (cod, cod_magazin, nume_produs, material_produs, culoare_produs, " +
                "pret_produs, numar_bucati)" + " values (?, ?, ?, ?, ?, ?, ?)";
        //String q="insert into Produs values";
        try
        {
            System.out.println("Suntem in try de la produs");
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, produs.getCod() );
            preparedStmt.setInt (2, produs.getCod_magazin() );
            preparedStmt.setString   (3, produs.getNume_produs() );
            preparedStmt.setString(4, produs.getMaterial_produs() );
            preparedStmt.setString    (5, produs.getCuloare_produs() );
            preparedStmt.setFloat(6, produs.getPret_produs() );
            preparedStmt.setInt(7, produs.getNumar_bucati() );

            // execute the preparedstatement
            preparedStmt.execute();
            //System.out.println("A scris produsul in BD");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    // actualizare produs in BD
    public void update_produs(Connection con, Produs produs){
        // create the java mysql update preparedstatement
        String query = "update Produs set nume_produs = ?, material_produs = ?," +
                       " culoare_produs = ?, pret_produs = ?, numar_bucati = ? where cod = ?";

        try
        {
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setString   (1, produs.getNume_produs() );
            preparedStmt.setString(2, produs.getMaterial_produs() );
            preparedStmt.setString    (3, produs.getCuloare_produs() );
            preparedStmt.setFloat(4, produs.getPret_produs() );
            preparedStmt.setInt(5, produs.getNumar_bucati() );

            preparedStmt.setInt (6, produs.getCod() );
            //preparedStmt.setInt (2, produs.getCod_magazin() );

            // execute the preparedstatement
            preparedStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    // stergere produs in BD
    public void delete_produs(Connection con, Produs produs){
        String query = "DELETE FROM Produs WHERE cod = ?";

        try
        {
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setInt (1, produs.getCod() );

            // execute the preparedstatement
            preparedStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // adaugare client in BD
    public void add_client(Connection con, Client client){
        String query = " insert into Client (cod, cod_magazin, nume_client, prenume_client, CNP, " +
                "stare_civila, adresa, e_mail, coment)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        //String q="insert into Produs values";
        System.out.println("Suntem inainte de try de la client");
        try
        {
            System.out.println("Suntem in try de la client");
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, client.getCod() );
            preparedStmt.setInt (2, client.getCod_magazin() );
            preparedStmt.setString   (3, client.getNume_client() );
            preparedStmt.setString(4, client.getPrenume_client() );
            preparedStmt.setString    (5, client.getCNP() );
            preparedStmt.setString(6, client.getStare_civila() );
            preparedStmt.setString(7, client.getAdresa() );
            preparedStmt.setString(8, client.getE_mail() );
            preparedStmt.setString(9, client.getComent() );

            // execute the preparedstatement
            preparedStmt.execute();
            //System.out.println("A scris clientul in BD");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    // actualizare client in BD
    public void update_client(Connection con, Client client){
        // create the java mysql update preparedstatement
        String query = "update Client set nume_client = ?, prenume_client = ?, CNP = ?," +
                " stare_civila = ?, adresa = ?, e_mail = ?, coment = ? where cod = ?";

        try
        {
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setString   (1, client.getNume_client() );
            preparedStmt.setString(2, client.getPrenume_client() );
            preparedStmt.setString    (3, client.getCNP() );
            preparedStmt.setString(4, client.getStare_civila() );
            preparedStmt.setString(5, client.getAdresa() );
            preparedStmt.setString(6, client.getE_mail() );
            preparedStmt.setString(7, client.getComent() );

            preparedStmt.setInt (8, client.getCod() );
            //preparedStmt.setInt (2, produs.getCod_magazin() );

            // execute the preparedstatement
            preparedStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    // stergere client in BD
    public void delete_client(Connection con, Client client){
        String query = "DELETE FROM Client WHERE id_client = ?";

        try
        {
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setInt (1, client.getCod() );

            // execute the preparedstatement
            preparedStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    // mai jos sunt setters si getters
    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNume_magazin() {
        return nume_magazin;
    }

    public void setNume_magazin(String nume_magazin) {
        this.nume_magazin = nume_magazin;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public List<Client> getClient_list() {
        return client_list;
    }

    public void setClient_list(List<Client> client_list) {
        this.client_list = client_list;
    }

    public List<Produs> getProdus_list() {
        return produs_list;
    }

    public void setProdus_list(List<Produs> produs_list) {
        this.produs_list = produs_list;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

}
