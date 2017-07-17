package com.reminder.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.reminder.api.data.Reminder;
import com.reminder.api.data.RestDateFormat;
import com.reminder.api.data.Status;
import com.reminder.entity.ReminderEntity;
import com.reminder.persistence.ReminderDao;

@Singleton
public class ReminderServiceImpl implements ReminderService {

	@Inject
	private ReminderDao reminderDao;
	
	public Reminder getReminder(Long id) {
		return entityToReminder(reminderDao.getReminder(id));
	}
	
	public List<Reminder> getAllReminders(String status,String dueDate) throws Exception {
		List<Reminder> reminders = new ArrayList();
		Status input = null;
		if(status !=null && dueDate != null)
		{
			input = Status.valueOf(status);
			if(input.equals(null))
				throw new Exception("Invalid status:"+status);
			RestDateFormat format = new RestDateFormat(dueDate);
			for(ReminderEntity entity:reminderDao.getAllReminders(input.toString(),format.getDate()))
				reminders.add(entityToReminder(entity));
		}else if(status != null)
		{
			input = Status.valueOf(status);
			if(input.equals(null))
				throw new Exception("Invalid status:"+status);
			for(ReminderEntity entity:reminderDao.getAllRemindersByStatus(input.toString()))
				reminders.add(entityToReminder(entity));
		} else if(dueDate != null)
		{
			RestDateFormat format = new RestDateFormat(dueDate);
			for(ReminderEntity entity:reminderDao.getAllRemindersByDate(format.getDate()))
				reminders.add(entityToReminder(entity));
		}else
		{
			for(ReminderEntity entity:reminderDao.getAllReminders())
				reminders.add(entityToReminder(entity));
		}
		return reminders;
	}

	public void update(Reminder reminder) throws Exception {
		ReminderEntity entity = reminderDao.getReminder(reminder.getId());
		if(entity != null)
		{
			ReminderEntity updatedReminder = reminderToEntity(reminder);
			updatedReminder.setId(entity.getId());
			reminderDao.update(updatedReminder);
		}else
			throw new Exception("Reminder cannot be updated for:"+ reminder.getName());
	}

	public void add(Reminder reminder) {
		reminderDao.add(reminderToEntity(reminder));
	}

	// =========== Helpers ================

	private Reminder entityToReminder(ReminderEntity reminderEntity) {
		Reminder reminder = new Reminder();

		if (reminderEntity != null) {
			reminder.setId(reminderEntity.getId());
			reminder.setName(reminderEntity.getName());
			reminder.setDescription(reminderEntity.getDescription());
			reminder.setDueDate(reminderEntity.getDueDate().toString());
			reminder.setStatus(Status.valueOf(reminderEntity.getStatus()));
		}

		return reminder;
	}

	private ReminderEntity reminderToEntity(Reminder reminder) {
		ReminderEntity entity = new ReminderEntity();
		RestDateFormat format = new RestDateFormat(reminder.getDueDate());

		if (reminder != null) {
			entity.setId(reminder.getId());
			entity.setName(reminder.getName());
			entity.setDescription(reminder.getDescription());
			entity.setDueDate(format.getDate());
			entity.setStatus(reminder.getStatus().toString());
		}

		return entity;
	}

	
}
