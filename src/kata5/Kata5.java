 package kata5;
  
 import java.io.*;
 import java.sql.*;
  
  /**
   * @author Eduardo Rodríguez Hernández
   * v1.9
   */
 public class Kata5 {
     public static void main(String[] args) throws SQLException, ClassNotFoundException, FileNotFoundException, IOException {
         Class.forName("org.sqlite.JDBC");
         Connection c = DriverManager.getConnection("jdbc:sqlite:E:\\programa\\SQLiteDatabaseBrowserPortable\\Data\\kata5.db");
          
         Statement stmt = c.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM PEOPLE");
         String fileName = "E:\\base.txt";
         BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
          
         while (rs.next()){
             System.out.print("ID = " +rs.getInt("ID"));
             System.out.print("-->");
             System.out.println(" NAME = " +rs.getString("Nombre"));
         }
         String query2="CREATE TABLE IF NOT EXISTS MAIL" + "('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'mail' TEXT NOT NULL)";
                 
         stmt.execute(query2);        
         String mail;
          
        rs.close();
        while ((mail=reader.readLine())!=null){
            String query = "INSERT INTO MAIL (mail) VALUES ('" + mail + "')";
            stmt.executeUpdate(query);
        }
 
         stmt.close();
         c.close();
     }
 }