package br.com.desafio.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.desafio.abs.ICRUDService;
import br.com.desafio.enumeration.StatusEnum;
import br.com.desafio.view.TaskBean;

public class TaskService  implements ICRUDService <TaskBean> {

	@Override
    public int inserir(TaskBean bean,
                       Connection conn) throws SQLException {
		PreparedStatement ps = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql = new StringBuffer();
			sql.append("INSERT INTO TASK (title, description, status, dateCreation) VALUES (?, ?, ?, sysdate)  ");
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, bean.getTaskTitle());
			ps.setString(2, bean.getTaskDescription());
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
    public void alterar(TaskBean bean,
                        Connection conn) throws SQLException {
		PreparedStatement ps = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql = new StringBuffer();
			sql.append("UPDATE TASK SET status = ? , title = ?, description = ?, dateEdit = sysdate  ");
			sql.append(" WHERE code = ? ");
	        ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, bean.getTaskStatus()); 
			ps.setString(2,  bean.getTaskTitle());
			ps.setString(3, bean.getTaskDescription());
			ps.setInt(4, bean.getTaskCode());
			ps.executeUpdate();
			
		} finally {
			try {
				ps.close();
			} catch (Exception e) {}
			ps = null;
		}
	    
    }

	@Override
    public void excluir(TaskBean bean,
                        Connection conn) throws SQLException {
		PreparedStatement ps = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql = new StringBuffer();
			sql.append("DELETE TASK WHERE code = ? ");
	        ps = conn.prepareStatement(sql.toString());
	        ps.setInt(1, bean.getTaskCode());
			ps.executeUpdate();
			
		} finally {
			try {
				ps.close();
			} catch (Exception e) {}
			ps = null;
		}
	    
    }
	
}
