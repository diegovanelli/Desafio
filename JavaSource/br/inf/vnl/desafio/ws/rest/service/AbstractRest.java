package br.inf.vnl.desafio.ws.rest.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.desafio.ConexaoMySQL;

/**
 * @author DB70031
 * @since 16/12/2015 13:24:22
 */
public abstract class AbstractRest {
	public static final String prefixPath = "";
	
    protected Connection getConn() throws NamingException, SQLException {
    	return this.getConn(true);
    }
    
    protected Connection getConn(boolean isAutoCommit) throws NamingException, SQLException {
        Connection conn = ConexaoMySQL.getConexaoMySQL();
        conn.setAutoCommit(isAutoCommit);
        return conn;
    }
    
    protected void closeConn(Connection conn) {
        try {
            if (conn != null && !conn.isClosed())
                conn.close();
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
    }
    
    /**
     * 
     * @param conn
     * @throws SQLException 
     * 
     * @author Maikon Rafael da Rocha
     * @since 11/04/2012 15:35:41
     */
    protected void commit(Connection conn) throws SQLException {
            if (conn != null && !conn.isClosed())
                conn.commit();
    }
    
    /**
     * 
     * @param conn
     * @throws SQLException 
     * 
     * @author Maikon Rafael da Rocha
     * @since 11/04/2012 15:35:51
     */
    protected void rollback(Connection conn) {
    	try {
    		if (conn != null && !conn.isClosed()) {
    			conn.rollback();
    		}   
    	} catch (SQLException sqlEx) {
    		System.out.println(sqlEx.getMessage());
    	}
    }
    
    /**
     * 
     * @param status
     * @param msg
     * @return
     *
     * @author Luiz Gustavo Graupner de Godoy
     * @since 09/05/2017 11:23:41
     */
    protected Response getResponse(Status status, String msg) {
    	if (msg != null && !msg.isEmpty()) {
    		return Response.status(status).entity(msg).build();
    	}
    	return Response.status(status).build();
    }
}
