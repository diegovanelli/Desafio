package br.com.desafio;                   
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
 
public class ConexaoMySQL {
		public ConexaoMySQL() {
			
		}
 
	public static Connection getConexaoMySQL() {
	 
	    Connection connection = null;
	    try {
	 
			String driverName = "com.mysql.jdbc.Driver";                        
			 
			Class.forName(driverName);
	        String serverName = "localhost";
	        String mydatabase ="desafiosupero";        
	        String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
	        String username = "root";         
	        String password = "";      
	        connection = DriverManager.getConnection(url, username, password);
	 
	        return connection;
	    } catch (Exception e) {  
	            return null;
	 
	    }
	}
 
    public static boolean FecharConexao() {
        try {
            ConexaoMySQL.getConexaoMySQL().close();
 
            return true;
 
        } catch (SQLException e) {
 
            return false;
 
        }
 
    }
 
    public static java.sql.Connection ReiniciarConexao() {
 
        FecharConexao();
 
  
 
        return ConexaoMySQL.getConexaoMySQL();
 
    }
 
}