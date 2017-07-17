package com.reminder.api.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.ws.rs.WebApplicationException;

public class RestDateFormat {

	// Declare the date format for the parsing to be correct
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private java.sql.Date date;

	/**
	 * This is the default constructor which must take in one string parameter.
	 * The parameter is no other than the one passed in through the REST
	 * end-point. We'll see it later...
	 */
	public RestDateFormat(String dateStr) throws WebApplicationException {
		try {
			date = new java.sql.Date(df.parse(dateStr).getTime());
		} catch (final ParseException ex) {
			// Wrap up any expection as javax.ws.rs.WebApplicationException
			throw new WebApplicationException(ex);
		}
	}

	/**
	 * This is a getter method which returns the parsed date string value as
	 * java.sql.Date
	 *
	 */
	public java.sql.Date getDate() {
		return date;
	}

	/**
	 * For convenience of result checking
	 */
	@Override
	public String toString() {
		if (date != null) {
			return date.toString();
		} else {
			return "";
		}
	}
}
