package com.reminder.persistence;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.reminder.api.data.Status;
import com.reminder.entity.ReminderEntity;

@Singleton
public class ReminderDaoImpl implements ReminderDao {

	private final String ALL_REMINDERS="Select r from ReminderEntity r";
	private final String ALL_REMINDERS_BY_DATE="Select r from ReminderEntity r where r.dueDate = :inputDate";
	private final String ALL_REMINDERS_BY_STATUS="SELECT r FROM ReminderEntity r where r.status like :statusName";
	private final String ALL_REMINDERS_BY_STATE_DATE="Select r from ReminderEntity r where r.status like :statusName and r.dueDate = :inputDate";
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("persist-unit");
	
	public ReminderEntity getReminder(Long id) {
		EntityManager em = emf.createEntityManager();

		ReminderEntity entity = null;

		try {
			entity = em.find(ReminderEntity.class, id);
		} finally {
			em.close();
		}
		
		return entity;
	}
	
	public List<ReminderEntity> getAllReminders() {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery(ALL_REMINDERS);
		
		ReminderEntity entity = null;

		try {
			return (List<ReminderEntity>) query.getResultList();
		} finally {
			em.close();
		}
	}
	
	public List<ReminderEntity> getAllRemindersByStatus(String input) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery(ALL_REMINDERS_BY_STATUS)
				.setParameter("statusName", input);
		
		ReminderEntity entity = null;

		try {
			return (List<ReminderEntity>) query.getResultList();
		} finally {
			em.close();
		}
	}
	
	public List<ReminderEntity> getAllRemindersByDate(Date input) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery(ALL_REMINDERS_BY_DATE)
				.setParameter("inputDate", input);
		
		ReminderEntity entity = null;

		try {
			return (List<ReminderEntity>) query.getResultList();
		} finally {
			em.close();
		}
	}
	
	public List<ReminderEntity> getAllReminders(String inputStatus,Date inputDate) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery(ALL_REMINDERS_BY_STATE_DATE)
						.setParameter("statusName", inputStatus)
						.setParameter("inputDate", inputDate);
		
		ReminderEntity entity = null;

		try {
			return (List<ReminderEntity>) query.getResultList();
		} finally {
			em.close();
		}
	}

	public void update(ReminderEntity reminder) {
		EntityManager em = emf.createEntityManager();

		EntityTransaction transaction = em.getTransaction();

		try {
			transaction.begin();

            em.merge(reminder);

			transaction.commit();
		} catch (Exception e) {
			System.out.println("Error Saving Reminder: " + e.getMessage());

			transaction.rollback();
		} finally {
			em.close();
		}
		
	}

	public void add(ReminderEntity reminder) {
		EntityManager em = emf.createEntityManager();

		EntityTransaction transaction = em.getTransaction();

		try {
			transaction.begin();

            em.merge(reminder);

			transaction.commit();
		} catch (Exception e) {
			System.out.println("Error Saving Reminder: " + e.getMessage());

			transaction.rollback();
		} finally {
			em.close();
		}
		
		
	}
	
}
