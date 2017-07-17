package com.reminder.api;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.reminder.api.data.Reminder;
import com.reminder.service.ReminderService;

import io.swagger.annotations.Api;

@Api
@Path(value = "reminders")
public class ReminderResource {

	private final ReminderService reminderService;

	@Inject
	public ReminderResource(ReminderService reminderService) {
		this.reminderService = reminderService;
	}

	@GET
	@Path(value = "/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Reminder getReminder(@PathParam("id") Long id) {
		return reminderService.getReminder(id);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Reminder> getReminders(@QueryParam("status") String status, @QueryParam("dueDate") String dueDate) throws Exception {
		return reminderService.getAllReminders(status,dueDate);
	}

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateReminder(Reminder reminder) {
    	try
    	{
    		reminderService.update(reminder);
    	}catch(Exception e){
    	  return Response.status(Status.BAD_REQUEST).entity("reminder cannot be updated").type(MediaType.APPLICATION_JSON).build();
    	}
        return Response.status(Status.OK).entity(reminder).type(MediaType.APPLICATION_JSON).build();
    }
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addReminder(Reminder reminder) {
		reminderService.add(reminder);
		return Response.status(Status.OK).entity(reminder).type(MediaType.APPLICATION_JSON).build();
	}
}
