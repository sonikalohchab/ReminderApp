package com.reminder.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.sql.Date;

import javax.inject.Singleton;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.reminder.api.ReminderResource;
import com.reminder.api.data.Reminder;
import com.reminder.api.data.Status;
import com.reminder.service.ReminderService;

public class ReminderResourceTest extends JerseyTest {

	@Mock
	private ReminderService serviceMock;

	/**
	 * This is executed only once, not before each test.
	 * 
	 * This will enable Mockito Annotations to be used.
	 * This will enable log traffic and message dumping.
	 * This will register the Injectable Provider to the ResourceConfiguration which will
	 * allow for the mock objects and jersey test to be linked.
	 */
	@Override
	protected Application configure() {
		MockitoAnnotations.initMocks(this);
		
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		
		ResourceConfig config = new ResourceConfig(ReminderResource.class);
		config.register(new InjectableProvider());

		return config;
	}

	/**
	 * Invoke the retrieve reminder and check the http response is 200.
	 */
	@Test
	public void testGetReminder() {
		
		when(serviceMock.getReminder(Mockito.anyLong())).thenReturn(getMockReminder());

		Response response = target("reminders/1").request().get();

		Reminder reminder = response.readEntity(Reminder.class);
		
		assertEquals(200, response.getStatus());
		assertEquals("1", reminder.getId().toString());
		assertEquals("John", reminder.getName());
		
	}

   	/**
	 * Invoke the save reminder and check the http response is 200.
	 */
	@Test
	public void testSaveReminder() {
		
	    Entity<Reminder> reminder = Entity.entity(getMockReminder(), MediaType.APPLICATION_JSON_TYPE);
	    
		doNothing().when(serviceMock).add(Mockito.any(Reminder.class));

		Response response = target("reminders").request().post(reminder);
		
		Reminder test = response.readEntity(Reminder.class);

		assertEquals(200, response.getStatus());
		
		assertEquals("1", test.getId().toString());
		assertEquals("John", test.getName());
	}

	// ======= Mocking ==========
	
	/**
	 * Mock object that will be returned
	 * 
	 * @return the customer object
	 */
	private Reminder getMockReminder() {
		Reminder reminder = new Reminder();
		reminder.setId(1L);
		reminder.setName("John");
		reminder.setDescription("testing reminder service");
		reminder.setStatus(Status.DONE);
		reminder.setDueDate(new Date(System.currentTimeMillis()).toString());
		
		return reminder;
	}

	/**
	 *Inject Reminder Service
	 *
	 */
	class InjectableProvider extends AbstractBinder implements Factory<ReminderService> {
		
		@Override
		protected void configure() {
			bindFactory(this).to(ReminderService.class).in(Singleton.class);
		}

		public ReminderService provide() {
			return serviceMock;
		}

		public void dispose(ReminderService service) {
			serviceMock = null;
		}
	}
}
