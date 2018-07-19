package br.inf.dbr.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.inf.dbr.persistence.abs.ICRUDService;
import br.inf.dbr.persistence.cursor.TaskCursor;
import br.inf.dbr.persistence.entity.Task;
import br.inf.dbr.persistence.enumeration.StatusEnum;


public class TaskService  implements ICRUDService <Task> {

	@Override
    public int inserir(Task bean,
                       Connection conn) throws SQLException {
		PreparedStatement ps = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql = new StringBuffer();
			sql.append("INSERT INTO TASK (title, description, status, dateCreation) VALUES (?, ?, ?, sysdate())  ");
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, bean.getTitle());
			ps.setString(2, bean.getDescription());
			ps.setInt(3, StatusEnum.NOVO.getValue());
			
			return ps.executeUpdate();
			
		} finally {
			try {
				ps.close();
			} catch (Exception e) {}
			ps = null;
		}
    }

	@Override
    public void alterar(Task bean,
                        Connection conn) throws SQLException {
		PreparedStatement ps = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql = new StringBuffer();
			sql.append("UPDATE TASK SET status = ? , title = ?, description = ?, dateEdit = sysdate()  ");
			sql.append(" WHERE code = ? ");
	        ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, bean.getStatus()); 
			ps.setString(2,  bean.getTitle());
			ps.setString(3, bean.getDescription());
			ps.setInt(4, bean.getCode());
			ps.executeUpdate();
			
		} finally {
			try {
				ps.close();
			} catch (Exception e) {}
			ps = null;
		}
	    
    }

	@Override
    public void excluir(Task bean,
                        Connection conn) throws SQLException {
		PreparedStatement ps = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql = new StringBuffer();
			sql.append("DELETE FROM TASK WHERE code = ? ");
	        ps = conn.prepareStatement(sql.toString());
	        ps.setInt(1, bean.getCode());
			ps.executeUpdate();
			
		} finally {
			try {
				ps.close();
			} catch (Exception e) {}
			ps = null;
		}
	    
    }
	
	public List <Task> listar (Connection conn) throws SQLException {
		return new TaskCursor().listar(conn);
	}
	
}
