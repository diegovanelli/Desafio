package br.inf.vnl.desafio.ws.rest.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.core.Response;

import br.com.desafio.ConexaoMySQL;
import br.com.desafio.cursor.TaskCursor;
import br.com.desafio.service.TaskService;
import br.com.desafio.view.TaskBean;
import br.inf.vnl.desafio.ws.rest.service.ITaskRest;

public class TaskRest implements ITaskRest {

	@Override
    public Response insert(TaskBean bean) {
		new ConexaoMySQL();
		Connection conn = ConexaoMySQL.getConexaoMySQL();
	    TaskService service = new TaskService();
	    try {
	        service.inserir(bean, conn);
        } catch (SQLException e) {
	        System.out.println(e.getMessage());
        }
	    		
	    return null;
    }

	@Override
    public Response update(TaskBean bean) {
		new ConexaoMySQL();
		Connection conn = ConexaoMySQL.getConexaoMySQL();
	    TaskService service = new TaskService();
	    try {
	        service.alterar(bean, conn);
        } catch (SQLException e) {
	        System.out.println(e.getMessage());
        }
	    return null;
    }

	@Override
    public Response delete(TaskBean bean) {
		new ConexaoMySQL();
		Connection conn = ConexaoMySQL.getConexaoMySQL();
	    TaskService service = new TaskService();
	    try {
	        service.excluir(bean, conn);
        } catch (SQLException e) {
	        System.out.println(e.getMessage());
        }
	    return null;
    }

	@Override
    public List <TaskBean> list() {
		new ConexaoMySQL();
		Connection conn = ConexaoMySQL.getConexaoMySQL();
	    TaskCursor service = new TaskCursor();
	    try {
	    	List<TaskBean> taskBeans = service.listar(conn); 
	    	if (!taskBeans.isEmpty()) {
	    		return taskBeans;
	    	}
        } catch (SQLException e) {
	        System.out.println(e.getMessage());
        }
	    return null;
    }

	
}
