package Conectare_BD;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;


public class Connect_DB{
	
    private Connection con=null;
    //private Statement st=null;
    //private ResultSet rs=null;
    private  String url="jdbc:mysql://localhost:3306/Magazin_roboti?allowPublicKeyRetrieval=true&useSSL=false";//Conectare baza de date
    //private String url=new String("jdbc:mysql://127.0.0.1:3306/?user=root");
    //private String user="root";
    //private String pass="0000";

    // Constructor pentru conectarea la BD
    public Connection get_conexiune(String usr,String ps)//Metoda pentru a obtine conexiunea la baza de date
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(url,usr,ps);
            System.out.println(con);

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return con;

    }

}

