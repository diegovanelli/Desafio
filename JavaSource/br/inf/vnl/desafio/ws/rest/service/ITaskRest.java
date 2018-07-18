package br.inf.vnl.desafio.ws.rest.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.desafio.view.TaskBean;

@Path(AbstractRest.prefixPath + "/task")
public interface ITaskRest {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/insert/v1/")
	public abstract Response insert(TaskBean bean);
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/update/v1/")
	public abstract Response update(TaskBean bean);
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/delete/v1/")
	public abstract Response delete(TaskBean bean);
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/list/v1/")
	public List<TaskBean> list();
	
}