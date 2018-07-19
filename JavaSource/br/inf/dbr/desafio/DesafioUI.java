package br.inf.dbr.desafio;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

import br.inf.dbr.desafio.views.IndexView;

@SuppressWarnings("serial")
@Theme("desafio")
public class DesafioUI extends UI {
	
	Navigator navigator;
	protected static final String VIEW_INDEX = "";

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = true, ui = DesafioUI.class, widgetset = "br.inf.dbr.desafio.widgetset.DesafioWidgetset")
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		navigator = new Navigator(this,
		      					this);
		navigator.addView(VIEW_INDEX,
				new IndexView());

	}

}