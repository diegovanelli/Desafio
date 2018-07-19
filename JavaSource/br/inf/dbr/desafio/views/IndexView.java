package br.inf.dbr.desafio.views;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

import br.inf.dbr.commons.ConfirmDialog;
import br.inf.dbr.persistence.ConexaoMySQL;
import br.inf.dbr.persistence.entity.Task;
import br.inf.dbr.service.TaskService;

public class IndexView extends AbstractView {

    private static final long serialVersionUID = 1322474501256019370L;
    
    private Table taskTable;
    private TextField titleText;
    private TextArea descriptionText;
    private CheckBox statusCheck;
    private Button saveButton;
    private Button deleteButton;
    private Button newButton;
    private FormLayout form;
    
    private Connection conn;
    
    private Task task;
    
    public IndexView() {
        super();
    }

    @Override
    public void enter(ViewChangeEvent event) {
    	
    	new ConexaoMySQL();
		conn = ConexaoMySQL.getConexaoMySQL();
        
        createTaskTable();
        addComponent(taskTable);
        
        
        
        form = new FormLayout();
        form.setMargin(true);
        form.addStyleName("outlined");
        form.setSizeFull();
        
		titleText = new TextField("Título");
        titleText.setRequired(true);
        
        descriptionText = new TextArea("Descrição");
        statusCheck = new CheckBox("Concluído?");
        saveButton = new Button("Salvar");
        deleteButton = new Button("Deletar");
        newButton = new Button("Novo");
        
        form.addComponent(titleText);
        form.addComponent(descriptionText);
        form.addComponent(statusCheck);
        form.addComponent(saveButton);
        form.addComponent(deleteButton);
        form.addComponent(newButton);
        
        limpaComponentes();
        carregaTable();
        
        saveButton.addClickListener(new ClickListener(){
			
			
			@Override
			public void buttonClick(ClickEvent event) {
				if(titleText.isEmpty()) {
					Notification.show("Título é obrigatório!");
					titleText.focus();
					return;
				}
				
				
				TaskService service = new TaskService();
				try {
					if (saveButton.getCaption().equals("Salvar")) {
						task = new Task();
						selecionarValores();
						service.inserir(task, conn);
					} else {
						selecionarValores();
						service.alterar(task, conn);
					}
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				limpaComponentes();
				carregaTable();
			}
		});
        
        deleteButton.addClickListener(new ClickListener(){
			
			
			@Override
			public void buttonClick(ClickEvent event) {
				if (task != null) {
					ConfirmDialog.show(UI.getCurrent(),
					                   "Atenção",
					                   "Deseja realmente excluir?",
					                   "Sim",
					                   "Não",
					                   new ConfirmDialog.Listener(){

						@Override
						public void onClose (ConfirmDialog dialog) {
							if (dialog.isConfirmed()) {
								TaskService service = new TaskService();
								
								try {
									service.excluir(task, conn);
									limpaComponentes();
									carregaTable();
								} catch (SQLException e) {
									System.out.println(e.getMessage());
								}
							}
						}
					});
					
				}
				
			}
		});
        
        newButton.addClickListener(new ClickListener(){
			
			
			@Override
			public void buttonClick(ClickEvent event) {
				limpaComponentes();
				carregaTable();
				
			}
		});
        
        
        addComponent(form);
    }

	@Override
	protected void initUI() {
		// TODO Auto-generated method stub
		
	}
	
	
	private void createTaskTable() {
		taskTable = new Table("Tasks");
		taskTable.setSelectable(true);
		
		String codigo = "Cod.";
		String titulo = "Título";
		String descricao = "Descrição";
		String status = "Status";
		String dataCriacao = "Data Criação";
		String dataEdicao = "Data Edição";
		
		taskTable.addContainerProperty(codigo, Integer.class, null);
		taskTable.setColumnWidth(codigo, 25);
		taskTable.addContainerProperty(titulo, String.class, null);
		taskTable.setColumnWidth(titulo, 50);
		taskTable.addContainerProperty(descricao, String.class, null);
		taskTable.setColumnWidth(descricao, 150);
		taskTable.addContainerProperty(status, String.class, null);
		taskTable.setColumnWidth(status, 70);
		taskTable.addContainerProperty(dataCriacao, String.class, null);
		taskTable.setColumnWidth(dataCriacao, 100);
		taskTable.addContainerProperty(dataEdicao, String.class, null);
		taskTable.setColumnWidth(dataEdicao, 100);
		
		taskTable.addValueChangeListener(new ValueChangeListener(){
			
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				if (taskTable.getValue() != null) {
					task = (Task) taskTable.getValue();
					
					titleText.setValue(task.getTitle());
					descriptionText.setValue(task.getDescription());
					
					statusCheck.setVisible(true);
					statusCheck.setValue(task.getStatus() > 0);
					
					saveButton.setCaption("Alterar");
					deleteButton.setVisible(true);
					newButton.setVisible(true);
				} else {
					limpaComponentes();
				}
				
			}
		});
		
		
	}
	
	private void carregaTable() {
		taskTable.removeAllItems();
		List <Task> tasks;
		try {
			tasks = new TaskService().listar(conn);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			for (Task task : tasks) {
				taskTable.addItem(new Object[] { task.getCode(), 
				                                 task.getTitle(), 
				                                 task.getDescription(), 
				                                 task.getStatus() == 1 ? "Concluído" : "Pendente", 
		                                         sdf.format(task.getDateCreation()), 
		                                         task.getDateEdit() != null ? sdf.format(task.getDateEdit()) : ""}, task);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void selecionarValores() {
		task.setTitle(titleText.getValue());
		task.setDescription(descriptionText.getValue());
		task.setStatus(statusCheck.getValue() == true ? 1 : 0);
	}
	
	private void limpaComponentes() {
	
		task = new Task();
		titleText.setValue("");
        descriptionText.setValue("");
        statusCheck.setVisible(false);
        statusCheck.setValue(false);
        
        saveButton.setCaption("Salvar");
        deleteButton.setVisible(false);
        newButton.setVisible(false);
	}

}
