package br.inf.dbr.commons;

import java.io.Serializable;

import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class ConfirmDialog extends Window {
	
    private UI ui;
	private String title;
	private String message;
	private String okCaption;
	private String cancelCaption;
	private Listener listener;
	private boolean confirmed;
	
	private Label messageLabel;
	private Button okButton;
	private Button cancelButton;
    
    public interface Listener extends Serializable {
        void onClose(ConfirmDialog dialog);
    }
    
	private ConfirmDialog (UI ui, 
                          String title,
                          String message, 
                          String okCaption,
                          String cancelCaption, 
                          Listener listenerValue) {
		this(ui, title, message, okCaption, cancelCaption, listenerValue, 370, 160);    	
	}

	public ConfirmDialog(UI ui, 
                         String title,
                         String message, 
                         String okCaption,
                         String cancelCaption, 
                         Listener listenerValue, 
                         int width,
                         int height) {
		this.ui = ui;
    	this.title = title;
    	this.message = message;
    	this.okCaption = okCaption;
    	this.cancelCaption = cancelCaption;
    	this.listener = listenerValue;
    	this.confirmed = false;
    	
    	setCaption(title);
    	setClosable(false);
    	setResizable(false);
    	setModal(true);
    	
    	this.setWidth(width, Unit.PIXELS);
		this.setHeight(height, Unit.PIXELS);
    	
    	messageLabel = new Label(message);
    	messageLabel.setContentMode(ContentMode.HTML);
    	messageLabel.setWidth(100, Unit.PERCENTAGE);
    	messageLabel.addStyleName("label-align-center");
    	
    	okButton = new Button(okCaption);
    	cancelButton = new Button(cancelCaption);
    	
    	HorizontalLayout hMessage = new HorizontalLayout();
    	hMessage.setWidth(100, Unit.PERCENTAGE);
    	hMessage.addComponent(messageLabel);
    	hMessage.setComponentAlignment(messageLabel, Alignment.MIDDLE_CENTER);
    	
    	HorizontalLayout hButtons = new HorizontalLayout();
    	hButtons.setHeight(40, Unit.PIXELS);
    	hButtons.setWidth(100, Unit.PERCENTAGE);
    	hButtons.setSpacing(true);
    	hButtons.addComponent(okButton);
    	hButtons.addComponent(cancelButton);
    	hButtons.setComponentAlignment(okButton, Alignment.MIDDLE_RIGHT);
    	hButtons.setComponentAlignment(cancelButton, Alignment.MIDDLE_LEFT);
    	
    	VerticalLayout layout = new VerticalLayout();
    	layout.setSpacing(true);
    	layout.setMargin(true);
    	layout.addComponent(hMessage);
    	layout.addComponent(hButtons);
    	
    	setContent(layout);
    	
    	this.addShortcutListener(new ShortcutListener(null, KeyCode.ARROW_LEFT, null){
			
			@Override
			public void handleAction (Object sender,
			                          Object target) {
				okButton.focus();
			}
		});
    	this.addShortcutListener(new ShortcutListener(null, KeyCode.ARROW_RIGHT, null){
    		
    		@Override
    		public void handleAction (Object sender,
    		                          Object target) {
    			cancelButton.focus();
    		}
    	});
    	
    	this.addFocusListener(new FocusListener(){
			
			public void focus (FocusEvent event) {
				okButton.focus();
			}
		});
	}
	
	public static ConfirmDialog show (UI ui, 
                                      String title,
                                      String message, 
                                      String okCaption,
                                      String cancelCaption, 
                                      Listener listener) {
    	final ConfirmDialog confirm = new ConfirmDialog(ui, title, message, okCaption, cancelCaption, listener);
    	
    	confirm.addCloseListener(new CloseListener(){
			public void windowClose (CloseEvent e) {
				confirm.getListener().onClose(confirm);
			}
		});
    	
    	confirm.okButton.addClickListener(new ClickListener(){
			
			public void buttonClick (ClickEvent event) {
				confirm.setConfirmed(true);
				confirm.close();
			}
		});

    	confirm.cancelButton.addClickListener(new ClickListener(){
    		
    		public void buttonClick (ClickEvent event) {
    			confirm.close();
    		}
    	});
    	
    	UI.getCurrent().addWindow(confirm);
    	confirm.focus();
    	
    	return confirm;
    }
	
	public static ConfirmDialog show (UI ui, 
                                      String title,
                                      String message, 
                                      String okCaption,
                                      String cancelCaption, 
                                      Listener listener,
                                      int width,
                                      int height) {
    	final ConfirmDialog confirm = new ConfirmDialog(ui, title, message, okCaption, cancelCaption, listener, width, height);
    	
    	confirm.addCloseListener(new CloseListener(){
			public void windowClose (CloseEvent e) {
				confirm.getListener().onClose(confirm);
			}
		});
    	
    	confirm.okButton.addClickListener(new ClickListener(){
			
			public void buttonClick (ClickEvent event) {
				confirm.setConfirmed(true);
				confirm.close();
			}
		});

    	confirm.cancelButton.addClickListener(new ClickListener(){
    		
    		public void buttonClick (ClickEvent event) {
    			confirm.close();
    		}
    	});
    	
    	UI.getCurrent().addWindow(confirm);
    	confirm.focus();
    	
    	return confirm;
    }
    
    public UI getUi () {
	    return ui;
    }
    
    public String getTitle () {
	    return title;
    }
    
    public String getMessage () {
	    return message;
    }
    
    public String getOkCaption () {
	    return okCaption;
    }
    
    public String getCancelCaption () {
	    return cancelCaption;
    }
    
	public Listener getListener () {
	    return listener;
    }
    
    protected void setConfirmed (boolean value) {
    	this.confirmed = value;
    }

    public boolean isConfirmed () {
	    return confirmed;
    }
    
}
