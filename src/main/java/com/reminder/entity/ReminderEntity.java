package com.reminder.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "Reminder")
public class ReminderEntity implements Serializable {

	private static final long serialVersionUID = 7711505597348200997L;

	@Id
	@Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "due_date", nullable = false)
	private Date dueDate;
	
	@Column(name = "status", nullable = false)
	private String status;
	
	public ReminderEntity() {
		this.id = null;
		this.name = null;
		this.description = null;
		this.dueDate = null;
		this.status = null;
	}
	
	public ReminderEntity(Long id, String name, String description, Date dueDate,String status) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.dueDate = dueDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	// ======= Implements =======
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
}
