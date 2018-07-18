package br.com.desafio.view;

import java.util.Date;

public class TaskBean {
	
	private int taskCode;
	private String taskTitle;
	private String taskDescription;
	private int taskStatus;
	private Date taskDateCreation;
	private Date taskDateEdit;
	
	
    public TaskBean(int taskCode,
                    String taskTitle,
                    String taskDescription,
                    int taskStatus,
                    Date taskDateCreation,
                    Date taskDateEdit) {
	    super();
	    this.taskCode = taskCode;
	    this.taskTitle = taskTitle;
	    this.taskDescription = taskDescription;
	    this.taskStatus = taskStatus;
	    this.taskDateCreation = taskDateCreation;
	    this.taskDateEdit = taskDateEdit;
    }

	public int getTaskCode() {
    	return taskCode;
    }
	
    public String getTaskTitle() {
    	return taskTitle;
    }
	
    public String getTaskDescription() {
    	return taskDescription;
    }
	
    public int getTaskStatus() {
    	return taskStatus;
    }
	
    public Date getTaskDateCreation() {
    	return taskDateCreation;
    }
	
    public Date getTaskDateEdit() {
    	return taskDateEdit;
    }
	
    public void setTaskCode(int taskCode) {
    	this.taskCode = taskCode;
    }
	
    public void setTaskTitle(String taskTitle) {
    	this.taskTitle = taskTitle;
    }
	
    public void setTaskDescription(String taskDescription) {
    	this.taskDescription = taskDescription;
    }
	
    public void setTaskStatus(int taskStatus) {
    	this.taskStatus = taskStatus;
    }
	
    public void setTaskDateCreation(Date taskDateCreation) {
    	this.taskDateCreation = taskDateCreation;
    }
	
    public void setTaskDateEdit(Date taskDateEdit) {
    	this.taskDateEdit = taskDateEdit;
    }
	
   
	
	
}
