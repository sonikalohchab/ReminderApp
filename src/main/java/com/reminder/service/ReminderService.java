package com.reminder.service;

import java.util.List;

import com.reminder.api.data.Reminder;
import com.reminder.entity.ReminderEntity;

public interface ReminderService {

	public Reminder getReminder(Long id);

    public void update(Reminder reminder) throws Exception;
	
	public void add(Reminder reminder);
	
	public List<Reminder> getAllReminders(String status,String dueDate) throws Exception;
}
