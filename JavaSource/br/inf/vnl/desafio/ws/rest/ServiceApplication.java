package br.inf.vnl.desafio.ws.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import br.inf.vnl.desafio.ws.rest.service.impl.TaskRest;

public class ServiceApplication  extends Application {
	private Set<Object> singletons = new HashSet<Object>();
	 
	public ServiceApplication() {
		singletons.add(new TaskRest());
	}
 
	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

	@Override
	public Set<Class<?>> getClasses() {
		return null;
	}
}