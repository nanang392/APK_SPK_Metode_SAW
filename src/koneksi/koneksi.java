package koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class koneksi {
 private static Connection conn;
 
 public static Connection getConnection(){
if(conn==null)
     try{
    String url = "jdbc:mysql://localhost/spksiswaberprestasi";
    String user="root";
    String pass="";
    DriverManager.registerDriver(new com.mysql.jdbc.Driver());
    conn= (Connection) DriverManager.getConnection(url,user,pass); 
    System.out.println("Berhasil konek ke database");
 }
 catch (SQLException e){
 Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE,null,e);
  System.out.println("gagal koneksi"+e);
 }
 return conn;
 } 

    public Connection connect() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
