package com.reminder.persistence;


import java.sql.Date;
import java.util.List;

import com.reminder.entity.ReminderEntity;

public interface ReminderDao {
	
	public ReminderEntity getReminder(Long id);
	
	public List<ReminderEntity> getAllReminders();
	
	public List<ReminderEntity> getAllRemindersByDate(Date dueDate);
	
	public List<ReminderEntity> getAllReminders(String status,Date dueDate);
	
	public List<ReminderEntity> getAllRemindersByStatus(String status);

    public void update(ReminderEntity reminder);
	
	public void add(ReminderEntity reminder);
}
