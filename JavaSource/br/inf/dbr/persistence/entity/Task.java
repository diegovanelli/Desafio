package br.inf.dbr.persistence.entity;

import java.util.Date;

/**
 *
 *
 * @author Diego
 * @since 18 de jul de 2018 13:12:11
 */
public class Task {
	
	private int code;
	private String title;
	private String description;
	private int status;
	private Date dateCreation;
	private Date dateEdit;
	
	public Task() {
	    super();
	    // TODO Auto-generated constructor stub
    }
	public Task(int code,
                String title,
                String description,
                int status,
                Date dateCreation,
                Date dateEdit) {
	    super();
	    this.code = code;
	    this.title = title;
	    this.description = description;
	    this.status = status;
	    this.dateCreation = dateCreation;
	    this.dateEdit = dateEdit;
    }
	
    public int getCode() {
    	return code;
    }
	
    public String getTitle() {
    	return title;
    }
	
    public String getDescription() {
    	return description;
    }
	
    public int getStatus() {
    	return status;
    }
	
    public Date getDateCreation() {
    	return dateCreation;
    }
	
    public Date getDateEdit() {
    	return dateEdit;
    }
	
    public void setCode(int code) {
    	this.code = code;
    }
	
    public void setTitle(String title) {
    	this.title = title;
    }
	
    public void setDescription(String description) {
    	this.description = description;
    }
	
    public void setStatus(int status) {
    	this.status = status;
    }
	
    public void setDateCreation(Date dateCreation) {
    	this.dateCreation = dateCreation;
    }
	
    public void setDateEdit(Date dateEdit) {
    	this.dateEdit = dateEdit;
    }
	
	
}
