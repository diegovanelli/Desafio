	package br.com.desafio.cursor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.desafio.view.TaskBean;

public class TaskCursor {
	
	public List <TaskBean> listar (Connection conn) throws SQLException {
		ArrayList <TaskBean> tasks = new ArrayList <TaskBean>();
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
			tasks.add(new TaskBean(rs.getInt("code"),
			                             rs.getString("title"),
			                             rs.getString("description"),
			                             rs.getInt("status"),
			                             new Date(rs.getDate("dateCreation").getTime()),
			                             new Date(rs.getDate("dateEdit").getTime())));
		}
		
		rs.close();
		ps.close();
		
		return tasks;
	}
	
}
