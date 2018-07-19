package br.inf.dbr.desafio.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


@SuppressWarnings("serial")
public abstract class AbstractView extends VerticalLayout implements View {
	
	
	protected VerticalLayout layout;
	
	public AbstractView() {
		layout = new VerticalLayout();
	}
	
	protected abstract void initUI();
		
	public void enter (ViewChangeEvent event) {
		
		initUI();
		
		removeAllComponents();
		
		
		createWindow();
			
		UI.getCurrent().getPage().setTitle("Desafio");
		
	}
	
	private void createWindow () {
		setSizeFull();
		layout.removeAllComponents();
		layout.setMargin(new MarginInfo(false,
		                                false,
		                                false,
		                                false));
		addComponent(layout);
		
	}
	
}
