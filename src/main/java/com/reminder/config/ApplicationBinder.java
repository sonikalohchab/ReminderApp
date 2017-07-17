package com.reminder.config;

import javax.inject.Singleton;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import com.reminder.persistence.ReminderDao;
import com.reminder.persistence.ReminderDaoImpl;
import com.reminder.service.ReminderService;
import com.reminder.service.ReminderServiceImpl;

public class ApplicationBinder extends AbstractBinder {
	
    @Override
    protected void configure() {
    	
     // services
        bind(ReminderServiceImpl.class).to(ReminderService.class).in(Singleton.class);
        
        // dao
        bind(ReminderDaoImpl.class).to(ReminderDao.class).in(Singleton.class);
    }
}