	package br.inf.dbr.persistence.cursor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.inf.dbr.persistence.entity.Task;


public class TaskCursor {
	
	public List <Task> listar (Connection conn) throws SQLException {
		ArrayList <Task> tasks = new ArrayList <Task>();
		StringBuffer sql = new StringBuffer();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		sql.append("SELECT ");
		sql.append("	* ");
		sql.append("FROM ");
		sql.append("	task ");
		
		ps = conn.prepareStatement(sql.toString());
		rs = ps.executeQuery();
		while (rs.next()) {
			Task task = new Task();
			task.setCode(rs.getInt("code"));
			task.setTitle(rs.getString("title"));
			task.setDescription(rs.getString("description"));
			task.setStatus(rs.getInt("status"));
			task.setDateCreation(new Date(rs.getDate("dateCreation").getTime()));
			if (rs.getDate("dateEdit") != null) {
				task.setDateEdit(new Date(rs.getDate("dateEdit").getTime()));
			}
			tasks.add(task);
		}
		
		rs.close();
		ps.close();
		
		return tasks;
	}
	
}
